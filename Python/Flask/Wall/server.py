from flask import Flask, request, redirect, render_template, session, flash
from mysqlconnection import MySQLConnector
import re, md5, os, binascii

app = Flask(__name__)
app.secret_key = 'secret'
mysql = MySQLConnector(app, 'wall')
EMAIL_REGEX = re.compile(r'^[a-zA-Z0-9.+_-]+@[a-zA-Z0-9._-]+\.[a-zA-Z]+$')

@app.route('/')
def index():
    if not session.get('logged_in'):
        session['logged_in'] = False
    return render_template('index.html')

@app.route('/check', methods=['POST'])
def check():
    email = request.form['email']
    pw = request.form['pw']
    salt = binascii.b2a_hex(os.urandom(15))
    hashed_pw = md5.new(pw + salt).hexdigest()

    # handle login form
    if request.form['action'] == 'login':
        query = "SELECT COUNT(id), id, first_name, last_name, email, hashed_pw, salt FROM users WHERE email = '" + email + "' GROUP BY id"
        query_res = mysql.query_db(query)
        fail = False
        if len(query_res) == 0:
            flash("your email is not registered")
            fail = True
        else:
            if query_res[0]['COUNT(id)'] == 0:
                flash("your email is not registered")
                fail = True
            encryptped_pw = md5.new(pw + query_res[0]['salt']).hexdigest()
            if encryptped_pw != query_res[0]['hashed_pw']:
                flash("incorrect password")
                fail = True
        if fail:
            return redirect('/')
        else:
            # update session
            session['logged_in'] = True
            session['uid'] = query_res[0]['id']
            return redirect('/wall')

    # handle registration form
    elif request.form['action'] == 'register':
        fail = False
        first = request.form['first']
        last = request.form['last']
        confirm = request.form['confirm']
        if len(first) < 2 or not first.isalpha():
            flash("invalid first name")
            fail = True
        if len(last) < 2 or not last.isalpha():
            flash("invalid last name")
            fail = True
        if len(email) < 4 or not EMAIL_REGEX.match(email):
            flash("invalid email")
            fail = True
        if len(pw) < 8:
            flash("password must be at least 8 characters long")
            fail = True
        if pw != confirm:
            flash("passwords do not match")
            fail = True
        if fail:
            return redirect('/')
        else:
            query1 = "SELECT COUNT(id), id, first_name, last_name FROM users WHERE email = '" + email + "' GROUP BY id"
            query_res1 = mysql.query_db(query1)
            count = 0
            if len(query_res1) > 0:
                count = query_res1[0]['COUNT(id)']
            if count > 0:
                flash("email already registered")
                return redirect('/')
            else:
                # update database
                insert_query = 'INSERT INTO users (first_name, last_name, email, hashed_pw, salt, created_at, updated_at) VALUES (:first_name, :last_name, :email, :hashed_pw, :salt, NOW(), NOW())'
                data = {
                    'first_name': first,
                    'last_name': last,
                    'email': email,
                    'hashed_pw': hashed_pw,
                    'salt': salt}
                mysql.query_db(insert_query, data)
                # update session
                session['logged_in'] = True
                query2 = "SELECT id FROM users WHERE email = '" + email + "' LIMIT 1"
                query_res2 = mysql.query_db(query2)
                session['uid'] = query_res2[0]['id']
                return redirect('/wall')

@app.route('/wall')
def wall():
    if not session.get('logged_in') or not session['logged_in']:
        return redirect('/')
    else:
        query = "SELECT messages.id, first_name, last_name, message, messages.created_at, GROUP_CONCAT(CASE WHEN comments.message_id = messages.id THEN comment END ORDER BY comments.created_at DESC) AS comment, GROUP_CONCAT(CASE WHEN comments.message_id = messages.id THEN first_name END ORDER BY comments.created_at DESC) AS comment_first_name, GROUP_CONCAT(CASE WHEN comments.message_id = messages.id THEN last_name END ORDER BY comments.created_at DESC) AS comment_last_name, GROUP_CONCAT(CASE WHEN comments.message_id = messages.id THEN comments.created_at END ORDER BY comments.created_at DESC) AS comment_created_at FROM users JOIN messages ON users.id = messages.user_id LEFT JOIN comments ON messages.id = comments.message_id GROUP BY messages.id, first_name , last_name , message , messages.created_at ORDER BY messages.created_at DESC"
        query_res = mysql.query_db(query)
        message_keys = ['id', 'first_name', 'last_name', 'message', 'created_at']
        comment_keys = ['comment_first_name', 'comment_last_name', 'comment', 'comment_created_at']
        message_info = []
        for d in query_res:
            info = []
            for key in message_keys:
                info.append(d[key])
            comment_info = []
            if d['comment_first_name']:
                for key1 in comment_keys:
                    comment_info.append(list(d[key1].split(',')))
                info.append(comment_info)
            message_info.append(info)
        return render_template('wall.html', message_info=message_info)

@app.route('/post', methods=['POST'])
def post():    
    if request.form['post_type'] == 'message':
        query = 'INSERT INTO messages (message, user_id, created_at, updated_at) VALUES (:message, :user_id, NOW(), NOW())'
        data = {
            'message': request.form['message'],
            'user_id': session['uid']}
        mysql.query_db(query, data)
    elif request.form['post_type'] == 'comment':
        query = 'INSERT INTO comments(comment, user_id, message_id, created_at, updated_at) VALUES (:comment, :user_id, :message_id, NOW(), NOW())'
        data = {
            'comment': request.form['comment'],
            'user_id': session['uid'],
            'message_id': request.form['message_id']}
        mysql.query_db(query, data)
    return redirect('/wall')

@app.route('/logout', methods=['POST'])
def logout():
    # reset 'logged_in' if user clears cache after logging in
    if not session.get('logged_in') or not session['logged_in']:
        return redirect('/')
    session['logged_in'] = False
    return redirect('/')

app.run(debug=True)

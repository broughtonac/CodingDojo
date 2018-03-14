from flask import Flask, request, redirect, render_template, session, flash
from mysqlconnection import MySQLConnector
import re, md5, os, binascii

app = Flask(__name__)
app.secret_key = 'secret'
mysql = MySQLConnector(app, 'loginregistration')
EMAIL_REGEX = re.compile(r'^[a-zA-Z0-9.+_-]+@[a-zA-Z0-9._-]+\.[a-zA-Z]+$')

@app.route('/')
def index():
    if not session.get('pos'):
        session['pos'] = 0
    if not session.get('logged_in'):
        session['logged_in'] = False
    return render_template('index.html')

@app.route('/process', methods=['POST'])
def check():
    email = request.form['email']
    pw = request.form['pw']
    salt = binascii.b2a_hex(os.urandom(15))
    hashed_pw = md5.new(pw + salt).hexdigest()

    # handle login form
    if request.form['action'] == 'login':
        query = "SELECT COUNT(id), id, first_name, last_name, email, hashed_pw, salt FROM members WHERE email = '" + email + "' GROUP BY id"
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
            uid = query_res[0]['id']
            session['pos'] -= 1
            session[session['pos']] = uid
            session[uid] = (
                query_res[0]['first_name'],
                query_res[0]['last_name'],
                query_res[0]['email'],
                session['pos'])
            session['logged_in'] = True
            return redirect('/success')

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
            query1 = "SELECT COUNT(id) FROM members WHERE email =  '" + email + "'"
            query_res1 = mysql.query_db(query1)
            count = 0
            if len(query_res1) > 0:
                count = query_res1[0]['COUNT(id)']
            if count > 0:
                flash("email already registered")
                return redirect('/')
            else:

                # update database
                insert_query = 'INSERT INTO members (first_name, last_name, email, hashed_pw, salt, created_at, updated_at) VALUES (:first_name, :last_name, :email, :hashed_pw, :salt, NOW(), NOW())'
                data = {
                    'first_name': first,
                    'last_name': last,
                    'email': email,
                    'hashed_pw': hashed_pw,
                    'salt': salt}
                mysql.query_db(insert_query, data)

                # update session
                query2 = "SELECT id FROM members WHERE email = '" + email + "'"
                query_res2 = mysql.query_db(query2)
                uid = query_res2[0]['id']
                session['pos'] -= 1
                session[session['pos']] = uid
                session[uid] = (first, last, email, session['pos'])
                session['logged_in'] = True
                return redirect('/success')

@app.route('/success')
def success():
    if len(session) == 0 or not session['logged_in']:
        flash("you must login or register")
        return redirect('/')
    else:
        return render_template('success_page.html')

@app.route('/logout', methods=['POST'])
def logout():
    this_user_pos = session['pos']
    uid = session[str(this_user_pos)]
    session.pop(str(uid))
    session.pop(str(this_user_pos))
    session['pos'] += 1
    session['logged_in'] = False
    return redirect('/')

app.run(debug=True)
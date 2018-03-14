from flask import Flask, request, redirect, render_template, session, flash
from mysqlconnection import MySQLConnector
import re
app = Flask(__name__)
app.secret_key = 'secret'
mysql = MySQLConnector(app, 'emailvalidation')
EMAIL_REGEX = re.compile(r'^[a-zA-Z0-9.+_-]+@[a-zA-Z0-9._-]+\.[a-zA-Z]+$')

@app.route('/')
def index():
    return render_template('index.html')

@app.route('/process', methods=['POST'])
def check():
    email = request.form['email']
    select_query = "SELECT COUNT(id) FROM emails WHERE address = '" + email + "'"
    count = mysql.query_db(select_query)[0]['COUNT(id)']
    if count > 0:
        flash("email already exists")
        return redirect('/')
    elif len(email) > 4 and EMAIL_REGEX.match(email):
        flash("{} is a valid email address".format(email))
        insert_query = 'INSERT INTO emails (address, entered_at) VALUES (:address, NOW())'
        data = {'address': email}
        mysql.query_db(insert_query, data)
        return redirect('/success')
    else:
        flash("invalid email address")
        return redirect('/')

@app.route('/success')
def success():
    query = 'SELECT * FROM emails'
    emails = mysql.query_db(query)
    return render_template('success.html', all_emails=emails)

app.run(debug=True)

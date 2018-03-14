from flask import Flask, render_template, request, redirect, session, flash
import random
import re
EMAIL_REGEX = re.compile(r'^[a-zA-Z0-9.+_-]+@[a-zA-Z0-9._-]+\.[a-zA-Z]+$')
app = Flask(__name__)
app.secret_key = "secret"

@app.route('/')
def index():
    return render_template("index.html")

@app.route('/process', methods=['POST'])
def display():
    errors = 0
    if (len(request.form['email']) < 1 or 
        len(request.form['first']) < 1 or 
        len(request.form['last']) < 1 or 
        len(request.form['pw']) < 1 or 
        len(request.form['confirm']) < 1):
        flash("all fields must be filled")
        errors += 1
    if (not request.form['first'].isalpha() or 
        not request.form['last'].isalpha()):
        flash("first and last names must only contain letters")
        errors += 1
    if not EMAIL_REGEX.match(request.form['email']):
        flash("invalid email address")
        errors += 1
    if not (request.form['pw'] == request.form['confirm']):
        flash("passwords don't match")
        errors += 1
    if errors == 0:
        return redirect('/thanks')
    else:
        return redirect('/')

@app.route('/thanks')
def confirmation():
    return render_template('confirmation.html')

app.run(debug=True)
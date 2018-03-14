from flask import Flask, request, redirect, render_template
from mysqlconnection import MySQLConnector
app = Flask(__name__)
mysql = MySQLConnector(app, 'fullfriends')

@app.route('/')
def index():
    query = 'SELECT * FROM friends'
    friends = mysql.query_db(query)
    return render_template('index.html', all_friends=friends)

@app.route('/process', methods=['POST'])
def add():
    query = "INSERT INTO friends (name, age, since) VALUES (:name, :age, DATE(NOW()))"
    data = {
        'name': request.form['name'],
        'age': request.form['age']
    }
    mysql.query_db(query, data)
    return redirect('/')

app.run(debug=True)

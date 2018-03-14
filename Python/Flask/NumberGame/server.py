from flask import Flask, render_template, request, redirect, session
import random
app = Flask(__name__)
app.secret_key = "secret"

@app.route('/')
def home():
    if len(session) == 0:
        session['ans'] = random.randrange(0, 101)
        session['guess'] = None
    return render_template('index.html')

@app.route('/process', methods=['POST'])
def check():
    session['guess'] = int(request.form['number'])
    return redirect('/')

@app.route('/reset', methods=['POST'])
def reset():
    session['ans'] = random.randrange(0, 101)
    session['guess'] = None
    return redirect('/')

@app.route('/again', methods=['POST'])
def replay():
    session.pop('ans')
    session.pop('guess')
    return redirect('/')

app.run(debug=True)
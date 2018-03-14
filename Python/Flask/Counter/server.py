from flask import Flask, render_template, request, redirect, session
app = Flask(__name__)
app.secret_key = "secret"

@app.route('/')
def count():
    if len(session) == 0:
        session['tally'] = 1
    else:
        session['tally'] += 1
    return render_template("index.html", tally=session['tally'])

@app.route('/zero')
def reset():
    session['tally'] = 0
    return redirect('/')

app.run(debug=True)
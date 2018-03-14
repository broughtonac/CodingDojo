from flask import Flask, render_template, request, redirect, session, flash
import random
app = Flask(__name__)
app.secret_key = "secret"

@app.route('/')
def index():
  return render_template("index.html")

@app.route('/process', methods=["POST"])
def display():
    name = request.form["name"]
    if len(name) < 1:
        flash("error: name empty")
    loc = request.form["locations"]
    lang = request.form["languages"]
    msg = request.form["comment"]
    if len(msg) < 1:
        flash("error: comment empty")
    if len(msg) > 120:
        flash("error: limit comment to 120 characters")
    print name, loc, lang, msg
    return render_template("info.html", name=name, loc=loc, lang=lang, msg=msg)

app.run(debug=True)
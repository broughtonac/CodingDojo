from flask import Flask, render_template, request, redirect

app = Flask(__name__)

@app.route('/')
def index():
  return render_template("index.html")

@app.route('/process', methods=["POST"])
def display():
    name = request.form["name"]
    loc = request.form["locations"]
    lang = request.form["languages"]
    msg = request.form["comment"]
    print name, loc, lang, msg
    return render_template("info.html", name=name, loc=loc, lang=lang, msg=msg)

app.run(debug=True)
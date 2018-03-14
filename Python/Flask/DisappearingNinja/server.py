from flask import Flask, render_template, request, redirect

app = Flask(__name__)

@app.route('/')
def index():
  return render_template("index.html")

@app.route('/ninja')
def all_ninjas():
    return render_template("ninja.html")

@app.route('/ninja/<ninja_color>')
def colored_ninja(ninja_color):
    return render_template('ninja_color.html', color=ninja_color)

app.run(debug=True)
from flask import Flask, render_template, request, redirect, session
import random
app = Flask(__name__)
app.secret_key = "secret"

@app.route("/")
def home():
    if len(session) == 0:
        session["gold"] = 0
        session["activity"] = []
    return render_template("index.html")

@app.route("/process_money", methods=["POST"])
def update():
    if request.form["building"] == "farm":
        amt = random.randrange(10, 21)
        session["gold"] += amt
        session["activity"].append("got " + str(amt) + " from farm")
    elif request.form["building"] == "cave":
        amt = random.randrange(5, 10)
        session["gold"] += amt
        session["activity"].append("got " + str(amt) + " from cave")
    elif request.form["building"] == "house":
        amt = random.randrange(2, 6)
        session["gold"] += amt
        session["activity"].append("got " + str(amt) + " from house")
    elif request.form["building"] == "casino":
        amt = random.randrange(-50, 51)
        session["gold"] += amt
        session["activity"].append("got " + str(amt) + " from casino")
    return redirect("/")

@app.route("/reset", methods=["POST"])
def reset():
    session["gold"] = 0
    session["activity"] = []
    return redirect("/")

app.run(debug=True)
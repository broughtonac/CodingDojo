var express = require("express")
var bodyParser = require("body-parser")
var session = require("express-session")
var app = express()
app.use(bodyParser.urlencoded({extended: true}))
app.use(session({secret: "secret"}))
app.use(express.static(__dirname + "/static"))
app.set("views", __dirname + "/views")
app.set("view engine", "ejs")

app.get("/", function(req, res) {
    if (req.session.counter) {
        req.session.counter++
    }
    else {
        req.session.counter = 1
    }
    res.render("index.ejs", {counter: req.session.counter})
})
app.get("/reset", function(req, res) {
    req.session.counter = 0
    res.redirect("/")
})

app.listen(8000)
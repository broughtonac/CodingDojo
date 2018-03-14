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
    res.render("index.ejs")
})
app.post("/process", function(req, res) {
    req.session.data = {
        "name": req.body.name,
        "location": req.body.location,
        "language": req.body.language,
        "comment": req.body.comment
    }
    res.redirect("/result")
})
app.get("/result", function(req, res) {
    res.render("result.ejs", {data: req.session.data})
})

app.listen(8000)
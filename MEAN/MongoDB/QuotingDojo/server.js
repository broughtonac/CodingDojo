var express = require("express")
var bodyParser = require("body-parser")
var mongoose = require('mongoose');
var app = express()
app.use(bodyParser.urlencoded({extended: true}))
app.use(express.static(__dirname + "/static"))
app.set("views", __dirname + "/views")
app.set("view engine", "ejs")

mongoose.connect('mongodb://localhost/quoting_dojo');
mongoose.Promise = global.Promise;
var QuoteSchema = new mongoose.Schema({
    name: String,
    quote: String,
    created_at: Date
})
mongoose.model("Quote", QuoteSchema)
var Quote = mongoose.model("Quote")

app.get("/", function(request, response) {
    response.render("index.ejs")
})
app.post("/quotes", function(request, response) {
    let quote = new Quote({
        name: request.body.name,
        quote: request.body.quote,
        created_at: new Date()
    })
    quote.save(function(errors) {
        if (errors) {
            console.log("error occurred")
        }
        else {
            console.log("successfully added a quote")
            response.redirect("/quotes")
        }
    })
})
app.get("/quotes", function(request, response) {
    Quote.find({}, function(errors, quotes) {
        if (errors) {
            console.log("error occurred")
        }
        else {
            response.render("quotes.ejs", {quotes: quotes})
        }
    })
})

app.listen(8000, function() {
    console.log("listening on port 8000")
})
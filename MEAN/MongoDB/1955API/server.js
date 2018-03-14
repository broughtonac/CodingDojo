let express = require("express")
let bodyParser = require("body-parser")
let mongoose = require("mongoose")
let app = express()
app.use(bodyParser.json())
app.listen(8000)

// databse setup
mongoose.connect("mongodb://localhost/1955_api")
mongoose.Promise = global.Promise

// model
let PersonSchema = new mongoose.Schema({
    name: String
}, {timestamps: true})
mongoose.model("Person", PersonSchema)
let Person = mongoose.model("Person")

//controller
app.get("/", function(request, response) {
    Person.find({}, function(errors, persons) {
        response.json({data: persons})
    })
})
app.get("/new/:name", function(request, response) {
    let person = new Person({
        name: request.params.name
    })
    person.save(function(errors) {
        response.redirect("/")
    })
})
app.get("/remove/:name", function(request, response) {
    Person.remove({name: request.params.name}, function(errors) {
        response.redirect("/")
    })
})
app.get("/:name", function(request, response) {
    Person.findOne({name: request.params.name}, function(errors, person) {
        response.json({data: person})
    })
})

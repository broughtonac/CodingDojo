var express = require("express")
var bodyParser = require("body-parser")
var mongoose = require("mongoose")
var app = express()
app.use(bodyParser.urlencoded({extended: true}))
app.use(express.static(__dirname + "/static"))
app.set("views", __dirname + "/views")
app.set("view engine", "ejs")

mongoose.connect('mongodb://localhost/mongoose_dashboard');
mongoose.Promise = global.Promise;
var MongooseSchema = new mongoose.Schema({
    name: String,
    age: String,
    weight: String,
    created_at: Date,
    updated_at: Date
})
mongoose.model("Mongoose", MongooseSchema)
var Mongoose = mongoose.model("Mongoose")

app.get("/", function(request, response) {
    Mongoose.find({}, function(errors, mongooses) {
        if (errors) {
            console.log("an error occurred")
        }
        else {
            response.render("index.ejs", {mongooses: mongooses})
        }
    })
})
app.get("/mongooses/new", function(request, response) {
    response.render("new.ejs")
})
app.post("/mongooses", function(request, response) {
    let mongoose = new Mongoose({
        name: request.body.name,
        age: request.body.age,
        weight: request.body.weight,
        created_at: new Date()
    })
    mongoose.save(function(errors) {
        if (errors) {
            console.log("an error occurred")
        }
        else {
            console.log("successfully added a mongoose")
            response.redirect("/mongooses/new")
        }
    })
})
app.get("/mongooses/:id", function(request, response) {
    Mongoose.find({_id: request.params.id}, function(errors, mongooses) {
        if (errors) {
            console.log("an error occurred")
        }
        else {
            response.render("show.ejs", {mongoose: mongooses[0]})
        }
    })
})
app.get("/mongooses/edit/:id", function(request, response) {
    Mongoose.find({_id: request.params.id}, function(errors, mongooses) {
        if (errors) {
            console.log("an error occurred")
        }
        else {
            response.render("edit.ejs", {mongoose: mongooses[0]})
        }
    })
})
app.post("/mongooses/:id", function(request, response) {
    Mongoose.update({_id: request.params.id},
        {$set:{
            "name": request.body.name,
            "age": request.body.age,
            "weight": request.body.weight,
            "updated_at": new Date()
        }
    }, function(errors) {
        if (errors) {
            console.log("an error occurred")
        }
        else {
            response.redirect("/")
        }
    })
})
app.post("/mongooses/destroy/:id", function(request, response) {
    Mongoose.remove({_id: request.params.id}, function(errors) {
        if (errors) {
            console.log("an error occurred")
        }
        else {
            response.redirect("/")
        }
    })
})

app.listen(8000)
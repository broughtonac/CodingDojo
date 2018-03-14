let express = require("express")
let bodyParser = require("body-parser")
let mongoose = require("mongoose")
let app = express()
app.use(bodyParser.json())
app.use(express.static( __dirname + '/helloWorldApp/dist'))
app.listen(8000)

// databse setup
mongoose.connect("mongodb://localhost/resful-task-api")
mongoose.Promise = global.Promise

// model
let TaskSchema = new mongoose.Schema({
    title: String,
    description: String,
    completed: Boolean
}, {timestamps: true})
mongoose.model("Task", TaskSchema)
let Task = mongoose.model("Task")

// controller
app.get("/tasks", function(request, response) {
    Task.find({}, function(errors, tasks) {
        response.json({data: tasks})
    })
})
app.get("/tasks/:id", function(request, response) {
    Task.findOne({_id: request.params.id}, function(errors, task) {
        response.json({data: task})
    })
})
app.post("/tasks", function(request, response) {
    let task = new Task(request.body)
    task.save(function(erors) {
        response.redirect("/tasks")
    })
})
app.put("/tasks/:id", function(request, response) {
    Task.findById(request.params.id, function(errors, task) {
        task.title = request.body.title
        task.description = request.body.description
        task.completed = request.body.completed
        task.save(function(errors) {
            response.redirect("/tasks/{}".format(request.params.id))
        })
    })
})
app.delete("/tasks/:id", function(request, response) {
    Task.remove({_id: request.params.id}, function(errors) {
        response.redirect("/")
    })
})

// helper
if (!String.prototype.format) {
    String.prototype.format = function() {
      var args = arguments;
      return this.replace(/{(\d+)}/g, function(match, number) { 
        return typeof args[number] != 'undefined'
            ? args[number]
            : match
        })
    }
}
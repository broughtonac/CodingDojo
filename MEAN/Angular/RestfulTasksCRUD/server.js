let bodyParser = require("body-parser")
let express = require("express")
let mongoose = require("mongoose")
let app = express()
app.use(bodyParser.json())
app.use(express.static( __dirname + '/AngularApp/dist'))
app.listen(8000)

// databse setup
mongoose.connect("mongodb://localhost/restful_tasks_crud")
mongoose.Promise = global.Promise

// model
let TaskSchema = new mongoose.Schema({
    title: {type: String, required: true},
    description: {type: String, required: true}
}, {timestamps: true})
mongoose.model("Task", TaskSchema)
let Task = mongoose.model("Task")

// controller
app.get("/tasks", function(request, response) {
    Task.find({}, function(error, tasks) {
        response.json({tasks: tasks})
    })
})
app.get("/tasks/:id", function(request, response) {
    Task.findById(request.params.id, function(error, task) {
        response.json({task: task})
    })
})
app.post("/tasks", function(request, response) {
    let task = new Task(request.body)
    task.save(function(error) {
        response.json({task: task})
    })
})
app.put("/tasks/:id", function(request, response) {
    Task.findById(request.params.id, function(error, task) {
        task.title = request.body.title
        task.description = request.body.description
        task.save(function(error) {
            console.log(task)
            response.json({task: task})
        })
    })
})
app.delete("/tasks/:id", function(request, response) {
    Task.remove({_id: request.params.id}, function(error) {
        response.json({})
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
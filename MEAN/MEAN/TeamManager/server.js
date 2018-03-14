let bodyParser = require("body-parser")
let express = require("express")
let mongoose = require("mongoose")
let path = require("path")
let app = express()
app.use(bodyParser.json())
app.use(express.static( __dirname + '/angular-app/dist'))
app.listen(8000)

// databse setup
mongoose.connect("mongodb://localhost/team_manager")
mongoose.Promise = global.Promise

// model
let PlayerSchema = new mongoose.Schema({
    name: {type: String, required: true, minlength: 2, maxlength: 255},
    position: {type: String},
    gameOne: {type: String},
    gameTwo: {type: String},
    gameThree: {type: String}
}, {timestamps: true})
mongoose.model("Player", PlayerSchema)
let Player = mongoose.model("Player")

// controller
app.get("/playersdb", function(request, response) {
    Player.find({}, function(error, players) {
        response.json({players: players})
    })
})
app.post("/playersdb", function(request, response) {
    let player = new Player(request.body)
    player.save(function(error) {
        response.redirect("/playersdb")
    })
})
app.get("/playersdb/:id", function(request, response) {
    Player.findById(request.params.id, function(error, player) {
        response.json({player: player})
    })
})
app.put("/playersdb/:id", function(request, response) {
    Player.findById(request.params.id, function(error, player) {
        player[request.body.status[0]] = request.body.status[1]
        player.save(function(error) {
            response.json({player: player})
        })
    })
})
app.delete("/playersdb/:id", function(request, response) {
    Player.remove({_id: request.params.id}, function(error) {
        response.json({})
    })
})

// express routes exhausted, now try angular routes
app.all(
    "*", (request, response, next) => {
    response.sendFile(path.resolve("./angular-app/dist/index.html"))
  }
)
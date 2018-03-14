let bodyParser = require("body-parser")
let express = require("express")
let session = require("express-session")
let mongoose = require("mongoose")
let path = require("path")
let app = express()
app.use(bodyParser.json())
app.use(express.json())
app.use(express.static( __dirname + '/angular-app/dist'))
app.use(session({secret: "secret"}))
app.listen(8000)

require("./server/config/mongoose.js")
require("./server/config/routes.js")(app)
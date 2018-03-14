let bodyParser = require("body-parser")
let express = require("express")
let mongoose = require("mongoose")
let path = require("path")
let app = express()
app.use(bodyParser.json())
app.use(express.static( __dirname + '/AngularApp/dist'))
app.listen(8000)

app.all(
    "*", (request, response, next) => {
    response.sendFile(path.resolve("./AngularApp/dist/index.html"))
  }
)
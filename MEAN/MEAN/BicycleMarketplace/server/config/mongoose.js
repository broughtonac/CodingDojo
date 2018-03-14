let mongoose = require("mongoose")
let path = require("path")
let models = path.join(__dirname, "../models")

mongoose.connect("mongodb://localhost/bicycle_marketplace")
mongoose.Promise = global.Promise

require("../models/User.js")
require("../models/Bicycle.js")

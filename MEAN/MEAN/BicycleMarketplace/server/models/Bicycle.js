let mongoose = require("mongoose")
let User = require("./User")

mongoose.model("Bicycle", new mongoose.Schema({
    title: {type: String, required: true},
    description: {type: String, required: true},
    price: {type: String, required: true},
    location: {type: String, required: true},
    image: {data: Buffer, contentType: String},
    _user: {type: mongoose.Schema.Types.ObjectId, ref: "User"}
}, {timestamps: true}))

let Bicycle = mongoose.model("Bicycle")
module.exports = Bicycle

let mongoose = require("mongoose")
let Bicycle = require("./Bicycle")

mongoose.model("User", new mongoose.Schema({
    first: {type: String, required: true},
    last: {type: String, required: true},
    email: {type: String, required: true},
    password: {type: String, required: true},
    attempts: {type: Number},
    countdown: {type: Date},
    bicycles: [{type: mongoose.Schema.Types.ObjectId, ref: "Bicycle"}]
}, {timestamps: true}))

let User = mongoose.model("User")
module.exports = User
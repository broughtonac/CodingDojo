let express = require("express")
let bodyParser = require("body-parser")
let mongoose = require("mongoose")
let validate = require("mongoose-validator")
let app = express()
app.use(bodyParser.urlencoded({extended: true}))
app.use(express.static(__dirname + "/static"))
app.set("views", __dirname + "/views")
app.set("view engine", "ejs")
app.listen(8000)

// database setup
mongoose.connect("mongodb://localhost/message_board")
mongoose.Promise = global.Promise
let Schema = mongoose.Schema

// model
let PostSchema = new mongoose.Schema({
    name: {type: String, required: true, minlength: 4, maxlength: 255},
    content: {type: String, required: true},
    comments: [{type: Schema.Types.ObjectId, ref: "Comment"}]
}, {timestamps: true})
let CommentSchema = new mongoose.Schema({
    _post: {type: Schema.Types.ObjectId, ref: "Post"},
    name: {type: String, required: true, minlength: 4, maxlength: 255},
    content: {type: String, required: true}
}, {timestamps: true})
mongoose.model("Post", PostSchema)
let Post = mongoose.model("Post")
mongoose.model("Comment", CommentSchema)
let Comment = mongoose.model("Comment")

// controller
app.get("/", function(request, response) {
     Post.find()
        .populate("comments")
        .exec(function(errors, posts) {
            return response.render("index.ejs", {posts: posts})
        })
})
app.post("/post", function(request, response) {
    let post = new Post({
        name: request.body.name,
        content: request.body.content
    })
    post.save(function(errors) {
        response.redirect("/")
    })
})
app.post("/comment/:id", function(request, response) {
    Post.findOne({_id: request.params.id}, function(errors, post) {
        console.log(request.body.content)
        let comment = new Comment({
            name: request.body.name,
            content: request.body.content
        })
        comment._post = post._id
        comment.save(function(errors) {
            post.comments.push(comment)
            post.save(function(errors) {
                response.redirect("/")
            })
        })
    })
})
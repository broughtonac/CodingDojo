let bodyParser = require("body-parser")
let express = require("express")
let mongoose = require("mongoose")
let app = express()
app.use(bodyParser.json())
app.use(express.static( __dirname + '/booksapiapp/dist'))
app.listen(8000)

// database setup
mongoose.connect("mongodb://localhost/booksapi")
mongoose.Promise = global.Promise
let Schema = mongoose.Schema

// model
let AuthorSchema = new mongoose.Schema({
    firstName: {type: String, required: true},
    lastName: {type: String, required: true},
    country: {type: String, required: true},
    birthdate: {type: Date, required: true},
    books: [{type: Schema.Types.ObjectId, ref: "Book"}]
}, {timestamps: true})
let BookSchema = new mongoose.Schema({
    _author: {type: Schema.Types.ObjectId, ref: "Author"},
    title: {type: String, required: true},
    year: {type: String, required: true}
}, {timestamps: true})
mongoose.model("Author", AuthorSchema)
let Author = mongoose.model("Author")
mongoose.model("Book", BookSchema)
let Book = mongoose.model("Book")

// controller
app.get("/authors", function(request, response) {
    Author.find({}, function(error, authors) {
        response.json({data: authors})
    })
})
app.get("/books", function(request, response) {
    Book.find({}, function(error, books) {
        response.json({data: books})
    })
})
app.post("/authors", function(request, response) {
    let author = new Author({
        firstName: request.body.firstName,
        lastName: request.body.lastName,
        country: request.body.country,
        birthdate: request.body.birthdate
    })
    author.save(function(error) {
        response.redirect("/authors")
    })
})
app.post("/books", function(request, response) {
    let book = new Book({
        title: request.body.title,
        year: request.body.year
    })
    book.save(function(error) {
        response.redirect("/books")
    })
})
app.post("/books/:authorid", function(request, response) {
    Author.findById(request.body.params.id, function(error, author) {
        let book = new Book({
            title: request.body.title,
            year: request.body.year,
            _author: author
        })
        book.save(function(errors) {
            author.books.push(book)
            author.save(function(errors) {
                response.redirect("/books")
            })
        })
    })
})
var express = require("express") // 
var app = express()
// module that has methods we can use to parse request body,
// such as handling post data and retrieving url parameters
var bodyParser = require("body-parser")
var session = require('express-session') // lets us use session
app.use(session({secret: 'secret'})) // for encryption

app.use(bodyParser.urlencoded({extended: true}))
app.use(express.static(__dirname + "/static"))
app.set('views', __dirname + '/views')
app.set('view engine', 'ejs')

app.get("/", function(request, response) {
    response.send("<h1>hello express</h1>")
})

app.get("/users", function (request, response){
    var users_array = [
        {name: "Michael", email: "michael@codingdojo.com"}, 
        {name: "Jay", email: "jay@codingdojo.com"}, 
        {name: "Brendan", email: "brendan@codingdojo.com"}, 
        {name: "Andrew", email: "andrew@codingdojo.com"}
    ];
    response.render('users', {users: users_array});
})

// npm install body-parser in project directory
app.post('/users', function (req, res){
    // post data demo
    console.log("POST DATA: " + req.body)
    console.log("name: " + req.body.name)
    console.log("email: " + req.body.email)
    // set the name property of session.  
    req.session.name = req.body.name; // session.name = name entered by user on the formd
    console.log(req.session.name);
    // code to add user to db goes here
    // redirect the user back to the root route.  
    res.redirect('/')
})

app.get("/users/:id", function (req, res){
    console.log("The user id requested is:", req.params.id);
    // just to illustrate that req.params is usable here:
    res.send("You requested the user with id: " + req.params.id);
    // code to get user from db goes here
})

app.post('/users', function (req, res){
    // set the name property of session.  
    req.session.name = req.body.name;
    console.log(req.session.name);
    //code to add user to db goes here!
    // redirect the user back to the root route. 
    res.redirect('/');
})

app.listen(8000, function() {
    console.log("listenting on port 8000")
})
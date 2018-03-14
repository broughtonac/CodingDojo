let express = require("express")
let bodyParser = require("body-parser")
let mongoose = require("mongoose")
let validate = require("mongoose-validator")
let session = require("express-session")
let bcrypt = require("bcrypt")
let app = express()
app.use(bodyParser.urlencoded({extended: true}))
app.use(session({secret: "secret"}))
app.set("views", __dirname + "/views")
app.set("view engine", "ejs")
app.listen(8000)

// DATABSE SETUP
mongoose.connect("mongodb://localhost/login_and_registration")
mongoose.Promise = global.Promise

// MODEL
let UserSchema = new mongoose.Schema({
    firstName: {
        type: String,
        required: [true, "first name is required"]
    },
    lastName: {
        type: String,
        required: [true, "last name is required"]
    },
    dob: {
        type: Date,
        required: [true, "date of birth is required"]
    },
    email: {
        type: String,
        unique: true,
        required: [true, "email is required"],
        validate: {
            validator: function(value) {
                let regex = /[a-zA-Z0-9.+_-]+@[a-zA-Z0-9._-]+\.[a-zA-Z]+$/g
                return regex.test(value)
            },
            message: "{VALUE} is not a valid email address"
        }
    },
    password: {
        type: String,
        required: [true, "password is required"],
        minlength: [8, "password must be at least 8 characters"]
    },
    confirm: {
        type: String,
        required: [true, "password confirmation required"],
        minlength: [8, "password confirmation must be at least 8 characters"],
        validate: {
            validator: function(value) {
                return value == this.password
            },
            message: "password confirmation failed"
        }
    }
}, {timestamps: true})
UserSchema.methods.hash = function(password) {
    return bcrypt.hashSync(password, bcrypt.genSaltSync(8))
}
UserSchema.methods.match = function(password, hashed) {
    return bcrypt.compareSync(password, hashed)
}
UserSchema.pre("save", function(done) {
    this.password = this.hash(this.password)
    console.log(this.password)
    this.password_confirm = undefined
    done()
})
mongoose.model("User", UserSchema)
let User = mongoose.model("User")

// CONTROLLER
app.get("/", function(request, response) {
    console.log(request.session.loginErrors)
    request.session.uid = null
    if (request.session.loginErrors == undefined) {
        request.session.loginErrors = []
    }
    if (request.session.registrationErrors == undefined) {
        request.session.registrationErrors = []
    }
    // loginErrors = request.session.loginErrors
    // registrationErrors = request.session.registrationErrors
    console.log("LOGIN ERRORS", request.session.loginErrors)
    console.log("REGISTRATION ERRORS", request.session.registrationErrors)
    response.render("index.ejs", {
        loginErrors: request.session.loginErrors,
        registrationErrors: request.session.registrationErrors
    })
})
app.post("/login", function(request, response) {
    request.session.loginErrors = []
    User.findOne({email: request.body.email}, function(errors, user) {
        if (request.body.email.length < 1) {
            request.session.loginErrors.push("email is required")
            response.redirect("/")
        }
        else if (!user) {
            request.session.loginErrors.push("email address not registered")
            response.redirect("/")
        }
        else {
            if (request.body.password.length < 1) {
                request.session.loginErrors.push("password is required")
                response.redirect("/")
            }
            else if (request.body.password.length < 8) {
                request.session.loginErrors.push("password must be at least 8 characters")
                response.redirect("/")
            }
            else if (!User.schema.methods.match(request.body.password, user.password)) {
                request.session.loginErrors.push("incorrect password")
                response.redirect("/")
            }
            else {
                request.session.uid = user._id
                response.redirect("/success")
            }
        }
    })
})
app.post("/register", function(request, response) {
    request.session.registrationErrors = []
    let user = new User(request.body)
    User.findOne({email: user.email}, function(errors, existingUser) {
        if (existingUser) {
            request.session.registrationErrors = ["email address already registered"]
            response.redirect("/")
        }
        else {
            user.save(function(errors) {
                if (errors) {
                    for (let key in user.errors) {
                        request.session.registrationErrors.push(user.errors[key].message)
                    }
                    response.redirect("/")
                }
                else {
                    request.session.uid = user._id
                    response.redirect("/success")
                }
            })
        }
    })
})
app.get("/success", function(request, response) {
    if (request.session.uid) {
        response.render("success.ejs")
    }
    else {
        response.redirect("/")
    }
})
app.get("/logout", function(request, response) {
    request.session.destroy
    response.redirect("/")
})
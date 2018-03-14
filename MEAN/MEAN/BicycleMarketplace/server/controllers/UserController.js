let User = require("mongoose").model("User")
let Bicycle = require("mongoose").model("Bicycle")

class UserController {
    register(request, response) {
        let backendErrors = []
        User.findOne({email: request.body.email}, (error, user) => {
            if (user) {
                backendErrors.push("email already registered")
                response.json({backendErrors: backendErrors})
            }
            else {
                let user = new User(request.body)
                user.save(error => {
                    request.session.uid = user.id
                    response.json(user)
                })
            }
        })
    }
    login(request, response) {
        let backendErrors = []
        User.findOne({email: request.body.email}, (error, user) => {
            if (user) {
                if (user.password == request.body.password) {
                    request.session.uid = user._id
                    response.json(user)
                }
                else {
                    backendErrors.push("incorrect password")
                    response.json({backendErrors: backendErrors})
                }
            }
            else {
                backendErrors.push("email not registered")
                response.json({backendErrors: backendErrors})
            }
        })
    }
    session(request, response) { // retrieve user if user's _id is in session
        if (request.session.uid) {
            User.findOne({_id: request.session.uid}, (error, user) => {
                response.json({user: user})
            })
        }
        else {
            response.json()
        }
    }
    logout(request, response) {
        request.session.destroy()
        response.json(false)
    }
    getOne(request, response) {
        User.findOne({_id: request.params.uid}, (error, user) => {
            response.json({user: user})
        })
    }
}

module.exports = new UserController()
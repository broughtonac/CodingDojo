let User = require("mongoose").model("User")

class UserController {
    register(request, response) {
        User.findOne({email: request.body.email}, (error, user) => {
            if (user) {
                return response.json({errors: ["email already registered"]})
            }
            else {
                let user = new User(request.body)
                user.save(error => {
                    if (error) {
                        return response.json({errors: user.errors})
                    }
                    else {
                        request.session.uid = user.id
                        return response.json(user)
                    }
                })
            }
        })
    }
    login(request, response) {
        User.findOne({email: request.body.email}, (error, user) => {
            if (user) {
                if (user.password == request.body.password) {
                    request.session.uid = user._id
                    return response.json(user)
                }
                else {
                    return response.json({errors: "invalid credentials"})
                }
            }
            else {
                return response.json({errors: "email not registered"})
            }
        })
    }
    session(request, response) {
        if (request.session.uid) {
            User.findOne({_id: request.session.uid}, (error, user) => {
                if (error) {
                    return response.json({errors: "invalid session"})
                }
                else {
                    return response.json({user})
                }
            })
        }
        else {
            return response.json({errors: "invalid session"})
        }
    }
    logout(request, response) {
        request.session.destroy()
        return response.json(false)
    }
}

module.exports = new UserController()
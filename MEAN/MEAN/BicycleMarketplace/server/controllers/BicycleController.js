let Bicycle = require("mongoose").model("Bicycle")
let User = require("mongoose").model("User")

class BicycleController {
    getAll(request, response) {
        Bicycle.find({}, (error, bicycles) => {
            response.json({bicycles: bicycles})
        })
    }
    getByUser(request, response) {
        Bicycle.find({_user: request.params.uid}, (error, userBicycles) => {
            response.json({userBicycles: userBicycles})
        })
    }
    getOne(request, response) {
        Bicycle.findById(request.params.uid, (error, bicycle) => {
            response.json({bicycle: bicycle})
        })
    }
    create(request, response) {
        User.findById(request.params.uid, (error, user) => {
            let bicycle = new Bicycle(request.body)
            bicycle._user = user.id
            bicycle.save(error => {
                user.bicycles.push(bicycle)
                user.save(error => {
                    response.json({})
                })
            })
        })
    }
    update(request, response) {
        Bicycle.findById(request.params.bid, (error, bicycle) => {
            bicycle.title = request.body.title
            bicycle.description = request.body.description
            bicycle.price = request.body.price
            bicycle.location = request.body.location
            bicycle.save(error => {
                response.json({})
            })
        })
    }
    destroy(request, response) {
        Bicycle.remove({_id: request.params.bid}, (error) => {
            response.json({})
        })
    }
    search(request, response) {
        let query = request.params.query
        Bicycle.find({title: {$regex: new RegExp("^" + query, "i")}}, (error, bicycles) => {
            response.json({bicycles: bicycles})
        })
    }
}

module.exports = new BicycleController()
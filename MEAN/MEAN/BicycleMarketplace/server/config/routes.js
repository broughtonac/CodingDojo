let UserController = require("../controllers/UserController.js")
let BicycleController = require("../controllers/BicycleController.js")
let path = require("path")

module.exports = (app) => {
    // user routes
    app.post("/register", UserController.register)
    app.post("/login", UserController.login)
    app.get("/session", UserController.session)
    app.get("/logout", UserController.logout)
    app.get("/users/:uid", UserController.getOne)

    // bicycle routes
    app.get("/bicycles", BicycleController.getAll)
    app.get("/bicycles/:bid", BicycleController.getOne)
    app.get("/bicycles/user/:uid", BicycleController.getByUser)
    app.post("/bicycles/:uid", BicycleController.create)
    app.put("/bicycles/:bid", BicycleController.update)
    app.delete("/bicycles/:bid", BicycleController.destroy)
    app.get("/bicycles/search/:query", BicycleController.search)

    // express routes exhausted, now check angular routes
    app.all("*", (request, result, next) => {
        result.sendFile(path.resolve("./angular-app/dist/index.html"))
    })
}
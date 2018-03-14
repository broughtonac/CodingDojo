let UserController = require("../controllers/UserController.js")
let path = require("path")

module.exports = (app) => {
    app.post("/register", UserController.register)
    app.post("/login", UserController.login)
    app.get("/session", UserController.session)
    app.get("/logout", UserController.logout)

    // express routes exhausted, now check angular routes
    app.all("*", (request, result, next) => {
        result.sendFile(path.resolve("./angular-app/dist/index.html"))
    })
}
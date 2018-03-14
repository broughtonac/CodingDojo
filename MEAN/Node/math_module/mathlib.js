module.exports = function() {
    return {
        myAdd: function(x, y) {
            return x + y
        },
        myMultiply: function(x, y) {
            return x * y
        },
        mySquare: function(x) {
            return x * x
        },
        myRandom: function(i, j) {
            return Math.floor(Math.random() * (j - i)) + i
        }
    }
}
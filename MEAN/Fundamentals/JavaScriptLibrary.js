let _ = {
    map: function(xs, f) {
        for (let i = 0; i < xs.length; i++) {
            xs[i] = f(xs[i])
        }
        return xs;
    },
    reduce: function(xs, f) {
        if (xs.length == 1) {
            return xs[0];
        }
        else if (xs.length > 1) {
        let z = xs[0]
            for (let i = 1; i < xs.length; i++) {
                z = f(z, xs[i])
            }
            return z;
        }
    },
    find: function(xs, p) {
        for (let i = 0; i < xs.length; i++) {
            if (p(xs[i])) {
                return xs[i]
            }
        }
    },
    filter: function(xs, p) {
        let ys = []
        for (let i = 0; i < xs.length; i++) {
            if (p(xs[i])) {
                ys.push(xs[i])
            }
        }
        return ys;
    },
    reject: function(xs, p) {
        let ys = []
        for (let i = 0; i < xs.length; i++) {
            if (!p(xs[i])) {
                ys.push(xs[i])
            }
        }
        return ys;
    }
}

let res1 = _.map([1,2,3,4,5,6,7,8,9,10], function(x) {return x * x})
console.log(res1)
let res2 = _.reduce([1,2,3,4,5,6,7,8,9,10], function(z, x) {return z + x})
console.log(res2)
let res3 = _.find([1,2,3,4,5,6,7,8,9,10], function(x) {return x % 2 == 0})
console.log(res3)
let res4 = _.filter([1,2,3,4,5,6,7,8,9,10], function(x) {return x % 2 == 0})
console.log(res4)
let res5 = _.reject([1,2,3,4,5,6,7,8,9,10], function(x) {return x % 2 == 0})
console.log(res5)
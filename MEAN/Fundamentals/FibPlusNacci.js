function fib() {
    let xs = []
    let i = 0;
    function nacci() {
        if (!xs.length) {
            xs.push(0)
        }
        else if (xs.length == 1) {
            xs.push(1)
        }
        else {
            xs.push(xs[i - 1] + xs[i - 2])
        }
        i += 1
        console.log(xs[i - 1])
    }
    return nacci
}

let n = fib()
for (let i = 0; i < 10; i++) {
    n()
}
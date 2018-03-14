class Bike {
    public miles: number
    constructor(public price: number, public max_speed: number) {
        this.price = price
        this.max_speed = max_speed
        this.miles = 0
    }
    displayInfo() {
        console.log(this.price, this.max_speed, this.miles)
        return this
    }
    ride() {
        console.log("riding")
        this.miles += 10
        return this
    }
    reverse() {
        if (this.miles - 5 > -1) {
            console.log("reversing")
            this.miles -= 5
        }
        return this
    }
}

let bike1 = new Bike(10, 20)
let bike2 = new Bike(100, 50)
let bike3 = new Bike(150, 60)

bike1.ride().ride().ride().reverse().displayInfo()

bike2.ride().ride().reverse().reverse().displayInfo()

bike3.reverse().reverse().reverse().displayInfo()
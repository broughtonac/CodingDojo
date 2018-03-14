function Ninja(name) {
    const speed = 3;
    const strength = 3;
    this.name = name;
    this.health = 100;
    this.sayName = function() {
        console.log("name: " + this.name)
    }
    this.showStats = function() {
        console.log("name: " + this.name + ", health: " + this.health + ", speed: " + speed + ", strength: " + strength)
    }
    this.drinkSake = function() {
        this.health += 10
    }
    this.punch = function(other_ninja) {
        if (other_ninja instanceof Ninja) {
            other_ninja.health -= 5
            console.log(this.name + " punched " + other_ninja.name + "; " + other_ninja.name + "'s health: " + other_ninja.health)
        }
    }
    this.kick = function(other_ninja) {
        if (other_ninja instanceof Ninja) {
            points = strength * 15
            other_ninja.health -= points
            console.log(this.name + " kicked " + other_ninja.name + "; " + other_ninja.name + "'s health: " + other_ninja.health)
        }
    }
}

const ninja1 = new Ninja("Hyabusa")
ninja1.sayName();
ninja1.showStats();
ninja1.drinkSake();
ninja1.showStats();

const ninja2 = new Ninja("Benihana")
ninja1.punch(ninja2)
ninja2.kick(ninja1)
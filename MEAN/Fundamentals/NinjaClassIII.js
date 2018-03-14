class Ninja {
    constructor(name) {
        this.speed = 3;
        this.strength = 3;
        this.name = name;
        this.health = 100;
    }
    sayName() {
        console.log("name: " + this.name)
    }
    showStats() {
        console.log("name: " + this.name + ", health: " + this.health + ", this.: " + this.speed + ", strength: " + this.strength)
    }
    drinkSake() {
        this.health += 10
    }
    punch(other_ninja) {
        if (other_ninja instanceof Ninja) {
            other_ninja.health -= 5
            console.log(this.name + " punched " + other_ninja.name + "; " + other_ninja.name + "'s health: " + other_ninja.health)
        }
    }
    kick(other_ninja) {
        if (other_ninja instanceof Ninja) {
            let points = this.strength * 15
            other_ninja.health -= points
            console.log(this.name + " kicked " + other_ninja.name + "; " + other_ninja.name + "'s health: " + other_ninja.health)
        }
    }
}

class Sensei extends Ninja {
    constructor(name) {
        super(name);
        this.speed = 10;
        this.strength = 10;
        this.name = name;
        this.health = 200;
        this.wisdom = 10;
    }
    speakWisdom() {
        super.drinkSake()
        console.log("I'm so wise")
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

const sensei1 = new Sensei("Confucius")
sensei1.speakWisdom();
sensei1.showStats();
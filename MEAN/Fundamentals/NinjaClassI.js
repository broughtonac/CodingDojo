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
}

const ninja = new Ninja("Hyabusa")
ninja.sayName();
ninja.showStats();
ninja.drinkSake();
ninja.showStats();
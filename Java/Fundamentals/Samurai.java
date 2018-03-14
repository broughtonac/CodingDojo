public class Samurai extends Human {
    public static int population = 0;
    public Samurai() {
        super();
        this.health = 200;
        population += 1;
    }
    public void deathBlow(Human human) {
        human.health = 0;
        this.health = this.health / 2;
    }
    public void meditate() {
        this.health += (this.health / 2);
    }
    public static int howMany() {
        return population;
    }
}
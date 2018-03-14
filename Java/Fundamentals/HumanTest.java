public class HumanTest {
    public static void main(String[] args) {
        // object master one
        Human h1 = new Human();
        Human h2 = new Human();
        h1.attack(h2);
        System.out.println(h2.health);
        // object master two
        Samurai s1 = new Samurai();
        Samurai s2 = new Samurai();
        Samurai s3 = new Samurai();
        System.out.println(Samurai.howMany());
        Wizard w1 = new Wizard();
        Ninja n1 = new Ninja();
        System.out.println(w1.health);
        w1.fireball(n1);
        n1.steal(w1);
        s1.deathBlow(s2);
        n1.attack(w1);
        w1.heal(w1);
        System.out.println(w1.health);
        System.out.println(n1.health);
        System.out.println(s2.health);
        System.out.println(s1.health);
        s1.meditate();
        System.out.println(s1.health);
    }
}
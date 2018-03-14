public class Gorilla extends Mammal {
    public void throwSomething() {
        System.out.println("gorilla threw something");
        super.energyLevel -= 5;
    }
    public void eatBananas() {
        System.out.println("gorilla ate bananas");
        super.energyLevel += 10;
    }
    public void climb() {
        System.out.println("gorilla climbed");
        super.energyLevel -= 10;
    }
}
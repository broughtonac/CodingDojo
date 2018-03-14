public class Dragon extends Mammal {
    public Dragon() {
        super(300);
    }
    public void fly() {
        System.out.println("dragon flew");
        this.energyLevel -= 50;
    }
    public void eatHumans() {
        System.out.println("dragon ate humans");
        this.energyLevel += 25;
    }
    public void attackTown() {
        System.out.println("dragon attacked town");
        this.energyLevel -= 100;
    }
}
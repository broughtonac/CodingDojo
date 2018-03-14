public class Mammal {
    int energyLevel = 100;
    public Mammal() {}
    public Mammal(int energy) {
        this.energyLevel = energy;
    }
    public int displayEnergy() {
        System.out.println(energyLevel);
        return energyLevel;
    }
}
public class Bacteria {
    protected int lifeTime;
    protected int power;
    protected int mortality;
    protected int averageTime;
    protected Strategy strategy;
    protected int[] humidityModifier;
    protected int[] temperatureModifier;

    public Bacteria(int lifeTime, int power, int mortality, int averageTime, Strategy strategy, int[] humidityModifier, int[] temperatureModifier) {
        this.lifeTime = lifeTime;
        this.power = power;
        this.mortality = mortality;
        this.averageTime = averageTime;
        this.strategy = strategy;
        this.humidityModifier = humidityModifier;
        this.temperatureModifier = temperatureModifier;
    }

    /*public update() {

    }

    protected attemptDie() {

    }

    protected attemptFight(int[] germs) {

    }*/
}

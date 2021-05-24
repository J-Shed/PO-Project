import java.util.ArrayList;

public class Bacteria {
    protected int lifeTime = 0;
    protected int power;
    protected int mortality;
    protected int averageTime;
    protected Strategy strategy;
    protected int[] humidityModifier;
    protected int[] temperatureModifier;
    protected Field field;

    public Bacteria(int power, int mortality, int averageTime, Strategy strategy, int[] humidityModifier, int[] temperatureModifier) {
        this.power = power;
        this.mortality = mortality;
        this.averageTime = averageTime;
        this.strategy = strategy;
        this.humidityModifier = humidityModifier;
        this.temperatureModifier = temperatureModifier;
    }
    public void setField (Field field) {
        this.field = field;
    }

    public boolean update() {
        attemptFight(SimulationManager.bacteria);
        return attemptDie();
    }

    protected boolean attemptDie() {
        //todo
        return lifeTime == averageTime;
    }

    protected void attemptFight(ArrayList<Bacteria> bacteria) {

    }
}

import java.util.ArrayList;
import java.util.Random;

public class Bacteria implements Cloneable {
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

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }


    public void setField(Field field) {
        this.field = field;
    }

    public boolean update() {
        return attemptDie();
    }

    protected boolean attemptDie() {
        Random random = new Random();
        int number = random.nextInt(100);
        float humidity = 0;
        float temperature = 0;
        if (this.field.temperature == Level.HIGH) {
            temperature = this.temperatureModifier[2] / 100;
        }
        if (this.field.temperature == Level.MODERATE) {
            temperature = this.temperatureModifier[1] / 100;
        }
        if (this.field.temperature == Level.LOW) {
            temperature = this.temperatureModifier[0] / 100;
        }
        if (this.field.humidity == Level.HIGH) {
            humidity = this.humidityModifier[2] / 100;
        }
        if (this.field.humidity == Level.MODERATE) {
            humidity = this.humidityModifier[1] / 100;
        }
        if (this.field.humidity == Level.LOW) {
            humidity = this.humidityModifier[0] / 100;
        }
        if (number < (this.averageTime * humidity * temperature)) return true;
        return false;
    }

    public int fight(Bacteria bacteria) {
        Random random = new Random();
        int number = random.nextInt(100);
        if (number > 10) return 1;
        else {
            int n = random.nextInt(100);
            float a = (n + this.power) * Strategy.strategy(this.strategy);
            int m = random.nextInt(100);
            float b = (m + bacteria.power) * Strategy.strategy(bacteria.strategy);
            if (a > b) return 2;
            else if (a < b) return 3;
            else return 1;
        }
    }
}

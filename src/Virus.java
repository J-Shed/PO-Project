import java.util.Random;

public class Virus extends Bacteria {
    private int mutationChance;

    public Virus(int power, int mortality, int averageTime, Strategy strategy, int[] humidityModifier, int[] temperatureModifier, int mutationChance) {
        super(power, mortality, averageTime, strategy, humidityModifier, temperatureModifier);
        this.mutationChance = mutationChance;
    }

    public void mutate(int mutationChance) {
        Random random = new Random();
        int number = random.nextInt(100);
        if (mutationChance >= number) {
            power += 5;
            mortality += 5;
        }
    }

    public boolean update() {
        mutate(mutationChance);
        return attemptDie();
    }
}

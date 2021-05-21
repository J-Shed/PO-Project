public class Virus extends Bacteria {
    private int mutationChance;

    public Virus(int power, int mortality, int averageTime, Strategy strategy, int[] humidityModifier, int[] temperatureModifier, int mutationChance) {
        super(power, mortality, averageTime, strategy, humidityModifier, temperatureModifier);
        this.mutationChance = mutationChance;
    }

        public void mutate() {

    }
}

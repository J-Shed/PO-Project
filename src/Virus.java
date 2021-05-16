public class Virus extends Bacteria {
    private int mutationChance;

    public Virus(int lifeTime, int power, int mortality, int averageTime, Strategy strategy, int[] humidityModifier, int[] temperatureModifier, int mutationChance) {
        super(lifeTime, power, mortality, averageTime, strategy, humidityModifier, temperatureModifier);
        this.mutationChance = mutationChance;
    }

//        public mutate() {
//
//    }
}

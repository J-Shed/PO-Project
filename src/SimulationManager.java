import java.io.File;
import java.io.FileWriter;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Random;
import java.util.Scanner;

public class SimulationManager {
    private Bacteria[] bacteria;
    private Human[] human;
    private Field[] field;

//    public static void doIteration() {
//
//    }

    public static int prepareSimulation() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ile dni ma trwać symulacja?");
        int days = scanner.nextInt();
        System.out.println("Ile bakterii chcesz dodać?");
        int numberBacteria = scanner.nextInt();
        for (int i = 0; i < numberBacteria; i++) {
            createBacteria();
        }
        return days;
    }

    private static void runSimulation(int Days) {

    }

    private static void saveResults() {
        File f = new File("results.csv");
        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            System.out.printf("Stworzono plik results.csv\n");
            if (f.canWrite()) {
                try {
                    FileWriter fw = new FileWriter(f, true);
                    Formatter fm = new Formatter(fw);
                    fm.format("kolumna1;kolumna2;kolumna3\r\n");
                    fm.format("wartosc1;wartosc2;wartosc3\r\n");
                    fm.close();
                    fw.close();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                System.out.printf("Zapisane wyniki symulacji w pliku results.csv\n");
            }
        } else {
            if (f.canWrite()) {
                try {
                    FileWriter fw = new FileWriter(f, true);
                    Formatter fm = new Formatter(fw);
                    fm.format("wartosc1;wartosc2;wartosc3\r\n");
                    fm.close();
                    fw.close();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                System.out.printf("Zapisano wyniki symulacji w pliku results.csv");
            }
        }
    }
//
    private static void createBacteria() {
        ArrayList<Bacteria> bacteria = new ArrayList<>();
        ArrayList<Virus> viruses = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj siłę bakterii");
        int power = scanner.nextInt();
        System.out.println("Podaj śmiertelność bakterii");
        int mortality = scanner.nextInt();
        System.out.println("Podaj średnią długość życia bakterii");
        int averageTime = scanner.nextInt();
        System.out.println("Wybierz strategię bakterii");
        for (Strategy strategy : Strategy.values()) {
            System.out.println(strategy);
        }
        scanner.nextLine();
        Strategy strategy = Strategy.valueOf(scanner.nextLine());

        System.out.println("Jaka wilgotność jest najbardziej preferowana dla tej bakterii?");
        System.out.println("1. Wysoka");
        System.out.println("2. Umarkowana");
        System.out.println("3. Niska");

        int userChoice = scanner.nextInt();
        int[] humidityModifier = null;
        boolean doIteration = true;

        while (doIteration) switch (userChoice) {
            case 1 -> {
                humidityModifier = new int[]{60, 80, 100};
                doIteration = false;
            }
            case 2 -> {
                humidityModifier = new int[]{80, 100, 80};
                doIteration = false;
            }
            case 3 -> {
                humidityModifier = new int[]{100, 80, 60};
                doIteration = false;
            }
            default -> System.out.println("Nie ma takiej opcji. Wybierz jeszcze raz.");
        }

        System.out.println("Jaka temperatura jest najbardziej preferowana dla tej bakterii?");
        System.out.println("1. Wysoka");
        System.out.println("2. Umarkowana");
        System.out.println("3. Niska");

        int userChoice1 = scanner.nextInt();
        int[] temperatureModifier = null;
        boolean doIteration1 = true;

        while (doIteration1) switch (userChoice1) {
            case 1 -> {
                temperatureModifier = new int[]{60, 80, 100};
                doIteration1 = false;
            }
            case 2 -> {
                temperatureModifier = new int[]{80, 100, 80};
                doIteration1 = false;
            }
            case 3 -> {
                temperatureModifier = new int[]{100, 80, 60};
                doIteration1 = false;
            }
            default -> System.out.println("Nie ma takiej opcji. Wybierz jeszcze raz.");
        }

        System.out.println("Czy bakteria może się mutować? (W naszej symulacji oznacza to, że jest wirusem.) (Jeśli tak wybierz 1, jeśli nie wybierz np. 0.");
        int userChoice3 = scanner.nextInt();
        if (userChoice3 == 1) {
            System.out.println("Podaj szansę na mutację.");
            int mutationChance = scanner.nextInt();
            Virus virus = new Virus(0, power, mortality, averageTime, strategy, humidityModifier, temperatureModifier, mutationChance);
            viruses.add(virus);
        } else {
            Bacteria bacteria1 = new Bacteria(0, power, mortality, averageTime, strategy, humidityModifier, temperatureModifier);
            bacteria.add(bacteria1);
        }
    }
    private static void createField() {
        Random random = new Random();
        int levelt = random.nextInt(3);
        Level temperature;
        Level humidity;
        if (levelt==0){
            temperature = Level.HIGH;
        }
        else if(levelt==1){
            temperature = Level.MODERATE;
        }
        else {
            temperature = Level.LOW;
        }
        int levelh = random.nextInt(3);
        if (levelh==0){
            humidity = Level.HIGH;
        }
        else if(levelh==1){
            humidity = Level.MODERATE;
        }
        else {
            humidity = Level.LOW;
        }
        Field field = new Field(temperature,humidity);

    }

    public static void main(String[] args) {
        int days = prepareSimulation();
        runSimulation(days);
        saveResults();
    }
}



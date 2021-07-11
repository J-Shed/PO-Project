import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.util.*;

public class SimulationManager {
    public static ArrayList<Bacteria> bacteria = new ArrayList<>();
    private static ArrayList<Human> humans = new ArrayList<>();
    public static ArrayList<Field> fields = new ArrayList<>();
    private static JFrame f;
    private static JPanel p;
    public static int days;
    public static int nr;
    public static int FIELD_COUNT = 25;
    public static int HUMANS_COUNT = 1000;
    public static int CHILD_COUNT = 100;

    /*public static int prepareSimulation() {
        int FIELD_COUNT = 25;
        int HUMANS_COUNT = 1000;
        int CHILD_COUNT = 100;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ile dni ma trwać symulacja?");
        int days = scanner.nextInt();
        System.out.println("Ile bakterii chcesz dodać?");
        int numberBacteria = scanner.nextInt();
        for (int i = 0; i < numberBacteria; i++) {
            createBacteria();
        }
        for (int i = 0; i < FIELD_COUNT; i++) {
            createField();
        }
        for (int i = 0; i < HUMANS_COUNT; i++) {
            createHuman();
        }
        for (int i = 0; i < CHILD_COUNT; i++) {
            createChild();
        }
        for (Bacteria i : bacteria) {
            for (int j = 0; j < 10; j++) {
                Random random = new Random();
                int number = random.nextInt(HUMANS_COUNT + CHILD_COUNT);
                SimulationManager.humans.get(number).addBacteria(i);
            }
        }
        return days;
    }*/

    private static void runSimulation(int days) {
        Iterator<Human> it = humans.iterator();
        for (int i = 0; i < days; i++) {
            while (it.hasNext()) {
                Human s = it.next();
                if (s.update()) {
                    s.field.human.remove(s);
                    it.remove();
                }
            }
        }
    }

    private static void saveResults(int days) {
        File f = new File("results.csv");
        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            System.out.println("Stworzono plik results.csv\n");
            if (f.canWrite()) {
                try {
                    FileWriter fw = new FileWriter(f, true);
                    Formatter fm = new Formatter(fw);
                    fm.format("Ilosc dni w symulacji;Ilosc ludzi na poczatku symulacji;Ilosc ktora przezyla do konca symulacji\r\n");
                    fm.format(String.valueOf(days));
                    fm.format(";1100;");
                    fm.format(String.valueOf(humans.size()));
                    fm.format("\r\n");
                    fm.close();
                    fw.close();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                System.out.println("Zapisane wyniki symulacji w pliku results.csv\n");
            }
        } else {
            if (f.canWrite()) {
                try {
                    FileWriter fw = new FileWriter(f, true);
                    Formatter fm = new Formatter(fw);
                    fm.format(String.valueOf(days));
                    fm.format(";1100;");
                    fm.format(String.valueOf(humans.size()));
                    fm.format("\r\n");
                    fm.close();
                    fw.close();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                System.out.println("Zapisano wyniki symulacji w pliku results.csv");
            }
        }
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int i = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    private static JFrame creatFrame() {
        JFrame frame = new JFrame();
        frame.setTitle("Prejekt Symulacja");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 800);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        ImageIcon image = new ImageIcon("PORN.png");
        frame.setIconImage(image.getImage());
        frame.getContentPane().setBackground(new Color(123, 50, 250));
        frame.setResizable(false);
        return frame;
    }

    private static JPanel prepareSimulation() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(0x123456));
        panel.setBounds(0, 0, 600, 800);
        panel.setLayout(null);
        JLabel label1 = new JLabel();
        label1.setText("Podaj ile dni ma trwać Symulacja:");
        label1.setBounds(5, 0, 200, 30);
        label1.setForeground(Color.white);
        panel.add(label1);
        JTextField text = new JTextField();
        text.setBounds(5, 25, 200, 25);
        text.setBackground(Color.white);
        panel.add(text);
        panel.setVisible(true);
        JLabel label2 = new JLabel();
        label2.setText("Ile wirusow chcesz dodac:");
        label2.setBounds(5, 55, 200, 30);
        label2.setForeground(Color.white);
        panel.add(label2);
        JLabel label3 = new JLabel();
        label3.setText("Należy wpisać liczbe calkowita!!!");
        label3.setBounds(220, 25, 200, 30);
        label3.setVisible(false);
        panel.add(label3);
        label3.setForeground(Color.red);
        JTextField text2 = new JTextField();
        text2.setBounds(5, 85, 200, 25);
        text2.setBackground(Color.white);
        panel.add(text2);
        JLabel label4 = new JLabel();
        label4.setText("Należy wpisać liczbe calkowita!!!");
        label4.setBounds(220, 85, 200, 30);
        label4.setForeground(Color.red);
        label4.setVisible(false);
        panel.add(label4);
        JButton button = new JButton("Potwierdz");
        button.setBounds(5, 130, 100, 25);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isNumeric(text.getText())) {
                    if (isNumeric(text2.getText())) {
                        days = Integer.parseInt(text.getText());
                        nr = Integer.parseInt(text2.getText());
                        panel.setVisible(false);
                        p = creatBacteria();
                        f.add(p);
                        for (int i = 0; i < FIELD_COUNT; i++) {
                            createField();
                        }
                        for (int i = 0; i < HUMANS_COUNT; i++) {
                            createHuman();
                        }
                        for (int i = 0; i < CHILD_COUNT; i++) {
                            createChild();
                        }
                    } else {
                        label4.setVisible(true);
                    }
                } else {
                    label3.setVisible(true);
                }
            }
        });
        panel.add(button);
        return panel;
    }

    private static JPanel creatBacteria() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(0x123456));
        panel.setBounds(0, 0, 600, 800);
        panel.setLayout(null);
        panel.setVisible(true);
        JLabel label1 = new JLabel();
        label1.setText("Ustaw parametry bakteri.");
        label1.setBounds(5, 0, 200, 30);
        label1.setFont(new Font("Dialog", Font.BOLD, 16));
        label1.setForeground(Color.white);
        panel.add(label1);
        JLabel label2 = new JLabel();
        label2.setText("Sila: ");
        label2.setBounds(5, 25, 200, 30);
        label2.setFont(new Font("Dialog", Font.BOLD, 16));
        label2.setForeground(Color.white);
        panel.add(label2);
        JSlider slider1 = new JSlider(0, 100, 50);
        slider1.setBounds(5, 55, 400, 50);
        slider1.setBackground(null);
        slider1.setPaintTicks(true);
        slider1.setMinorTickSpacing(1);
        slider1.setMajorTickSpacing(10);
        slider1.setPaintLabels(true);
        slider1.setForeground(Color.white);
        panel.add(slider1);
        JLabel label3 = new JLabel();
        label3.setText("Smiertelnosc: ");
        label3.setBounds(5, 100, 200, 30);
        label3.setFont(new Font("Dialog", Font.BOLD, 16));
        label3.setForeground(Color.white);
        label3.setVisible(true);
        panel.add(label3);
        JSlider slider2 = new JSlider(0, 100, 50);
        slider2.setBounds(5, 130, 400, 50);
        slider2.setBackground(null);
        slider2.setPaintTicks(true);
        slider2.setMinorTickSpacing(1);
        slider2.setMajorTickSpacing(10);
        slider2.setPaintLabels(true);
        slider2.setForeground(Color.white);
        panel.add(slider2);
        JLabel label4 = new JLabel();
        label4.setText("Srednia dlugosc zycia: ");
        label4.setBounds(5, 180, 200, 30);
        label4.setFont(new Font("Dialog", Font.BOLD, 16));
        label4.setForeground(Color.white);
        label4.setVisible(true);
        panel.add(label4);
        JTextField text = new JTextField();
        text.setBounds(5, 210, 200, 25);
        text.setBackground(Color.white);
        panel.add(text);
        JLabel label5 = new JLabel();
        label5.setText("Wybierz strategie bakterii: ");
        label5.setBounds(5, 235, 250, 30);
        label5.setFont(new Font("Dialog", Font.BOLD, 16));
        label5.setForeground(Color.white);
        label5.setVisible(true);
        panel.add(label5);
        JRadioButton BAGG = new JRadioButton("AGGRESIVE");
        BAGG.setBounds(15, 265, 90, 20);
        BAGG.setBackground(null);
        BAGG.setForeground(Color.white);
        BAGG.setFont(new Font("Dialog", Font.BOLD, 12));
        JRadioButton BMIX = new JRadioButton("MIXED");
        BMIX.setBounds(100, 265, 65, 20);
        BMIX.setBackground(null);
        BMIX.setForeground(Color.white);
        BMIX.setFont(new Font("Dialog", Font.BOLD, 12));
        JRadioButton BPAS = new JRadioButton("PASSIVE");
        BPAS.setBounds(160, 265, 80, 20);
        BPAS.setBackground(null);
        BPAS.setForeground(Color.white);
        BPAS.setFont(new Font("Dialog", Font.BOLD, 12));
        ButtonGroup BGstrat = new ButtonGroup();
        BGstrat.add(BAGG);
        BGstrat.add(BMIX);
        BGstrat.add(BPAS);
        panel.add(BAGG);
        panel.add(BMIX);
        panel.add(BPAS);
        JLabel label6 = new JLabel();
        label6.setText("Jaka jest preferowana wilgotnosc tej bakterii: ");
        label6.setBounds(5, 285, 400, 30);
        label6.setFont(new Font("Dialog", Font.BOLD, 16));
        label6.setForeground(Color.white);
        label6.setVisible(true);
        panel.add(label6);
        JRadioButton WW = new JRadioButton("Wysoka");
        WW.setBounds(15, 315, 75, 20);
        WW.setBackground(null);
        WW.setForeground(Color.white);
        WW.setFont(new Font("Dialog", Font.BOLD, 12));
        JRadioButton WS = new JRadioButton("Srednia");
        WS.setBounds(85, 315, 75, 20);
        WS.setBackground(null);
        WS.setForeground(Color.white);
        WS.setFont(new Font("Dialog", Font.BOLD, 12));
        JRadioButton WN = new JRadioButton("Niska");
        WN.setBounds(155, 315, 80, 20);
        WN.setBackground(null);
        WN.setForeground(Color.white);
        WN.setFont(new Font("Dialog", Font.BOLD, 12));
        ButtonGroup BGW = new ButtonGroup();
        BGW.add(WW);
        BGW.add(WS);
        BGW.add(WN);
        panel.add(WW);
        panel.add(WS);
        panel.add(WN);
        JLabel label7 = new JLabel();
        label7.setText("Jaka jest preferowana temperatura tej bakterii: ");
        label7.setBounds(5, 335, 400, 30);
        label7.setFont(new Font("Dialog", Font.BOLD, 16));
        label7.setForeground(Color.white);
        label7.setVisible(true);
        panel.add(label7);
        JRadioButton TW = new JRadioButton("Wysoka");
        TW.setBounds(15, 365, 75, 20);
        TW.setBackground(null);
        TW.setForeground(Color.white);
        TW.setFont(new Font("Dialog", Font.BOLD, 12));
        JRadioButton TS = new JRadioButton("Srednia");
        TS.setBounds(85, 365, 75, 20);
        TS.setBackground(null);
        TS.setForeground(Color.white);
        TS.setFont(new Font("Dialog", Font.BOLD, 12));
        JRadioButton TN = new JRadioButton("Niska");
        TN.setBounds(155, 365, 80, 20);
        TN.setBackground(null);
        TN.setForeground(Color.white);
        TN.setFont(new Font("Dialog", Font.BOLD, 12));
        ButtonGroup BGT = new ButtonGroup();
        BGT.add(TW);
        BGT.add(TS);
        BGT.add(TN);
        panel.add(TW);
        panel.add(TS);
        panel.add(TN);
        JLabel label8 = new JLabel();
        label8.setText("Czy bakteria moze sie mutowac: ");
        label8.setBounds(5, 385, 400, 30);
        label8.setFont(new Font("Dialog", Font.BOLD, 16));
        label8.setForeground(Color.white);
        label8.setVisible(true);
        panel.add(label8);
        JRadioButton MY = new JRadioButton("Tak");
        MY.setBounds(15, 415, 75, 20);
        MY.setBackground(null);
        MY.setForeground(Color.white);
        MY.setFont(new Font("Dialog", Font.BOLD, 12));
        JRadioButton MN = new JRadioButton("Nie");
        MN.setBounds(85, 415, 75, 20);
        MN.setBackground(null);
        MN.setForeground(Color.white);
        MN.setFont(new Font("Dialog", Font.BOLD, 12));
        ButtonGroup BGM = new ButtonGroup();
        BGM.add(MY);
        BGM.add(MN);
        panel.add(MY);
        panel.add(MN);
        JLabel label9 = new JLabel();
        label9.setText("Szansa na mutacje: ");
        label9.setBounds(5, 435, 400, 30);
        label9.setFont(new Font("Dialog", Font.BOLD, 16));
        label9.setForeground(Color.white);
        label9.setVisible(false);
        panel.add(label9);
        JSlider slider3 = new JSlider(0, 100, 50);
        slider3.setBounds(5, 470, 400, 50);
        slider3.setBackground(null);
        slider3.setPaintTicks(true);
        slider3.setMinorTickSpacing(1);
        slider3.setMajorTickSpacing(10);
        slider3.setPaintLabels(true);
        slider3.setForeground(Color.white);
        slider3.setEnabled(false);
        slider3.setVisible(false);
        panel.add(slider3);
        MY.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (MY.isSelected()) {
                    slider3.setVisible(true);
                    slider3.setEnabled(true);
                    label9.setVisible(true);
                }
            }
        });
        MN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (MN.isSelected()) {
                    slider3.setVisible(false);
                    slider3.setEnabled(false);
                    label9.setVisible(false);
                }
            }
        });
        JButton creat = new JButton("Creat Bacteria");
        creat.setBounds(5, 530, 120, 25);
        panel.add(creat);
        JLabel warning1 = new JLabel();
        warning1.setText("Należy wpisać liczbe calkowita!!!");
        warning1.setBounds(380, 210, 200, 30);
        warning1.setForeground(Color.red);
        warning1.setVisible(false);
        panel.add(warning1);
        JLabel warning2 = new JLabel();
        warning2.setText("Wybierz jedną opcje!!!");
        warning2.setBounds(380, 260, 200, 30);
        warning2.setForeground(Color.red);
        warning2.setVisible(false);
        panel.add(warning2);
        JLabel warning3 = new JLabel();
        warning3.setText("Wybierz jedną opcje!!!");
        warning3.setBounds(380, 310, 200, 30);
        warning3.setForeground(Color.red);
        warning3.setVisible(false);
        panel.add(warning3);
        JLabel warning4 = new JLabel();
        warning4.setText("Wybierz jedną opcje!!!");
        warning4.setBounds(380, 360, 200, 30);
        warning4.setForeground(Color.red);
        warning4.setVisible(false);
        panel.add(warning4);
        JLabel warning5 = new JLabel();
        warning5.setText("Wybierz jedną opcje!!!");
        warning5.setBounds(380, 415, 200, 30);
        warning5.setForeground(Color.red);
        warning5.setVisible(false);
        panel.add(warning5);
        JLabel count = new JLabel("Nalezy wprowadzic nastepujaca liczbe wirusow: " + nr);
        count.setBounds(150, 530, 400, 25);
        count.setFont(new Font("Dialog", Font.BOLD, 14));
        count.setForeground(Color.white);
        count.setVisible(true);
        panel.add(count);
        creat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nr > 1) {
                    if (isNumeric(text.getText())) {
                        if (BAGG.isSelected() || BMIX.isSelected() || BPAS.isSelected()) {
                            if (WW.isSelected() || WS.isSelected() || WN.isSelected()) {
                                if (TW.isSelected() || TS.isSelected() || TN.isSelected()) {
                                    if (MY.isSelected() || MN.isSelected()) {
                                        String strat = "";
                                        if (BAGG.isSelected()) {
                                            strat = "AGGRESSIVE";
                                        } else if (BMIX.isSelected()) {
                                            strat = "MIXED";
                                        } else if (BPAS.isSelected()) {
                                            strat = "PASSIVE";
                                        }
                                        Strategy strategy = Strategy.valueOf(strat);
                                        int[] humidityModifier = null;
                                        if (WW.isSelected()) {
                                            humidityModifier = new int[]{60, 80, 100};
                                        } else if (WS.isSelected()) {
                                            humidityModifier = new int[]{80, 100, 80};
                                        } else if (WN.isSelected()) {
                                            humidityModifier = new int[]{100, 80, 60};
                                        }
                                        int[] temperatureModifier = null;
                                        if (TW.isSelected()) {
                                            temperatureModifier = new int[]{60, 80, 100};
                                        } else if (TS.isSelected()) {
                                            temperatureModifier = new int[]{80, 100, 80};
                                        } else if (TN.isSelected()) {
                                            temperatureModifier = new int[]{100, 80, 60};
                                        }
                                        if (MY.isSelected()) {
                                            Virus virus = new Virus(slider1.getValue(), slider2.getValue(), Integer.parseInt(text.getText()), strategy, humidityModifier, temperatureModifier, slider3.getValue());
                                            SimulationManager.bacteria.add(virus);
                                        } else if (MN.isSelected()) {
                                            Bacteria bacteria = new Bacteria(slider1.getValue(), slider2.getValue(), Integer.parseInt(text.getText()), strategy, humidityModifier, temperatureModifier);
                                            SimulationManager.bacteria.add(bacteria);
                                        }
                                        nr--;
                                        count.setText("Nalezy wprowadzic nastepujaca liczbe wirusow: " + nr);
                                    } else {
                                        warning5.setVisible(true);
                                    }
                                } else {
                                    warning4.setVisible(true);
                                }
                            } else {
                                warning3.setVisible(true);
                            }
                        } else {
                            warning2.setVisible(true);
                        }
                    } else {
                        warning1.setVisible(true);
                    }
                } else {
                    for (Bacteria i : bacteria) {
                        for (int j = 0; j < 10; j++) {
                            Random random = new Random();
                            int number = random.nextInt(HUMANS_COUNT + CHILD_COUNT);
                            SimulationManager.humans.get(number).addBacteria(i);
                        }
                    }
                    runSimulation(days);
                    saveResults(days);
                    panel.setVisible(false);
                    p = showresults();
                    f.add(p);
                }
            }
        });
        return panel;
    }

    private static JPanel showresults() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(0x123456));
        panel.setBounds(0, 0, 600, 800);
        panel.setLayout(null);
        JLabel label1 = new JLabel();
        label1.setText("Ilosc dni w symulacji:" + days);
        label1.setBounds(5, 0, 500, 30);
        label1.setForeground(Color.white);
        panel.add(label1);
        JLabel label2 = new JLabel();
        label2.setText("Ilosc ludzi w symulacji: 1100");
        label2.setBounds(5, 30, 500, 30);
        label2.setForeground(Color.white);
        panel.add(label2);
        JLabel label3 = new JLabel();
        label3.setText("Ilosc ludzi ktora przezyla dokonca symulacje:" + humans.size());
        label3.setBounds(5, 60, 500, 30);
        label3.setForeground(Color.white);
        panel.add(label3);
        return panel;
    }
    /*private static void createBacteria() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj parametry bakteri.");
        System.out.println("Podaj siłę bakterii. (Liczbę od 1 do 100)");
        int power = scanner.nextInt();
        System.out.println("Podaj śmiertelność bakterii (Liczbę od 1 do 100)");
        int mortality = scanner.nextInt();
        System.out.println("Podaj średnią długość życia bakterii. (Ilość dni)");
        int averageTime = scanner.nextInt();
        System.out.println("Wybierz strategię bakterii");
        for (Strategy strategy : Strategy.values()) {
            System.out.println(strategy);
        }
        scanner.nextLine();
        Strategy strategy = Strategy.valueOf(scanner.nextLine());

        System.out.println("Jaka wilgotność jest najbardziej preferowana dla tej bakterii?");
        System.out.println("1. Wysoka");
        System.out.println("2. Umiarkowana");
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
        System.out.println("2. Umiarkowana");
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
            System.out.println("Podaj szansę na mutację. (Liczbę od 1 do 100)");
            int mutationChance = scanner.nextInt();
            Virus virus = new Virus(power, mortality, averageTime, strategy, humidityModifier, temperatureModifier, mutationChance);
            SimulationManager.bacteria.add(virus);
        } else {
            Bacteria bacteria = new Bacteria(power, mortality, averageTime, strategy, humidityModifier, temperatureModifier);
            SimulationManager.bacteria.add(bacteria);
        }
    }*/

    private static void createField() {
        Random random = new Random();
        int levelt = random.nextInt(3);
        Level temperature;
        Level humidity;
        if (levelt == 0) {
            temperature = Level.HIGH;
        } else if (levelt == 1) {
            temperature = Level.MODERATE;
        } else {
            temperature = Level.LOW;
        }
        int levelh = random.nextInt(3);
        if (levelh == 0) {
            humidity = Level.HIGH;
        } else if (levelh == 1) {
            humidity = Level.MODERATE;
        } else {
            humidity = Level.LOW;
        }
        Field field = new Field(temperature, humidity);
        SimulationManager.fields.add(field);
    }

    private static void createHuman() {
        Random random = new Random();
        int level = random.nextInt(25);
        Human human = new Human(SimulationManager.fields.get(level));
        SimulationManager.humans.add(human);
        human.field.human.add(human);
    }

    private static void createChild() {
        Random random = new Random();
        int level = random.nextInt(25);
        int immunity = random.nextInt(100);
        Child child = new Child(SimulationManager.fields.get(level), immunity);
        SimulationManager.humans.add(child);
        child.field.human.add(child);
    }

    public static void main(String[] args) {

        /*int days = prepareSimulation();
        runSimulation(days);
        System.out.println("Ilosc dni w symulacji:");
        System.out.println(days);
        System.out.println("\nIlosc ludzi na poczatku symulacji");
        System.out.println("1100");
        System.out.println("\nIlosc ktora przezyla do konca symulacji");
        System.out.println(humans.size());
        saveResults(days);*/
        f = creatFrame();
        p = prepareSimulation();
        f.add(p);
        f.setVisible(true);
    }
}



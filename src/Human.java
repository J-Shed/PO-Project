import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Human implements Cloneable {
    protected Field field;
    protected ArrayList<Bacteria> bacteria = new ArrayList<>();


    public Human(Field field) {
        this.field = field;
    }

    public void addBacteria(Bacteria bacteria) {
        try {
            Bacteria bacteriac = (Bacteria) bacteria.clone();
            bacteriac.setField(this.field);
            bacteriac.lifeTime = 0;
            this.bacteria.add(bacteriac);
        } catch (CloneNotSupportedException c) {
            System.out.println("CloneNotSupportedException");
        }
    }

    public boolean attemptFight(ArrayList<Bacteria> bacteria) {
        if (bacteria.isEmpty() || bacteria.size() == 1) return false;
        else return true;
    }

    public boolean update() {
        if (attemptMove()) {
            this.field.human.remove(this);
            move(SimulationManager.fields);
            for (Bacteria k : bacteria) {
                k.setField(field);
            }
        }
        Random random = new Random();
        for (Human j : this.field.human) {
            if (!this.equals(j)) {
                int number = random.nextInt(10);
                if (number == 1) {
                    attemptInfect(j);
                }
            }
        }
        if (attemptFight(bacteria)) {
            for (int m = 0; m < bacteria.size() - 1; m++) {
                for (int n = 1; n < bacteria.size(); n++) {
                    if (bacteria.get(m).fight(bacteria.get(n)) == 2) bacteria.remove(bacteria.indexOf(n));
                    else if (bacteria.get(m).fight(bacteria.get(n)) == 3) bacteria.remove(bacteria.indexOf(m));
                }
            }
        }

        Iterator<Bacteria> i = bacteria.iterator();
        while (i.hasNext()) {
            Bacteria s = i.next();
            if (s.update()) i.remove();
        }
        return attemptDie();
    }


    public void attemptInfect(Human human) {
        Random random = new Random();
        for (Bacteria l : human.bacteria) {
            int number = random.nextInt(100);
            if (number < l.power) {
                try {
                    Bacteria bacteriac = (Bacteria) l.clone();
                    bacteriac.setField(l.field);
                    bacteriac.lifeTime = 0;
                    this.bacteria.add(bacteriac);
                } catch (CloneNotSupportedException c) {
                    System.out.println("CloneNotSupportedException");
                }
            }
        }

    }

    public boolean attemptDie() {
        Random random = new Random();
        for (Bacteria k : bacteria) {
            int number = random.nextInt(100);
            if (number < (k.mortality)) {
                return true;
            }
            ;
        }
        return false;
    }

    public void move(ArrayList<Field> fields) {
        Random random = new Random();
        int level = random.nextInt(25);
        field = fields.get(level);
        this.field.human.add(this);
    }

    public boolean attemptMove() {
        Random random = new Random();
        int level = random.nextInt(2);
        if (level == 1) return true;
        else return false;
    }
}

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Human {
    protected Field field;
    protected ArrayList<Bacteria> bacteria = new ArrayList<>();
    protected Iterator<Bacteria> i = bacteria.iterator();

    public Human(Field field) {
        this.field = field;
    }

    public Field getField() {
        return field;
    }

    public void addBacteria(Bacteria bacteria) {
        try {
            Bacteria bacteriac = (Bacteria) bacteria.clone();
            bacteriac.setField(field);
            this.bacteria.add(bacteriac);
        } catch (CloneNotSupportedException c) {
        }
    }


    public boolean update() {
        if (attemptMove()) {
            move(SimulationManager.fields);
            for (Bacteria k : bacteria) {
                k.setField(field);
            }
        }
        Random random = new Random();
        for (Human j : this.field.human) {
            int number = random.nextInt(10);
            if (number == 1) {
                attemptInfect(j);
            }

        }
        while (i.hasNext()) {
            Bacteria s = i.next();
            if (s.update()) i.remove();
        }
        for (Human j : this.field.human) {
            int number = random.nextInt(10);
            if (number == 1) {
                attemptInfect(j);
            }
        }
        return attemptDie();

    }


    public void attemptInfect(Human human) {
        Random random = new Random();
        for (Bacteria l : human.bacteria) {
            int number = random.nextInt(100);
            if (number > l.power) {
                try {
                    Bacteria bacteriac = (Bacteria) l.clone();
                    bacteriac.setField(field);
                    bacteriac.lifeTime = 0;
                    this.bacteria.add(bacteriac);
                } catch (CloneNotSupportedException c) {
                }
            }
        }

    }

    public boolean attemptDie() {
        Random random = new Random();
        for (Bacteria k : bacteria) {
            int number = random.nextInt(100);
            if (number < (k.mortality)) return true;
        }
        return false;
    }

    public void move(ArrayList<Field> fields) {
        Random random = new Random();
        int level = random.nextInt(25);
        field = fields.get(level);
    }

    public boolean attemptMove() {
        Random random = new Random();
        int level = random.nextInt(2);
        if (level == 1) return true;
        else return false;
    }
}
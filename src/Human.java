import java.util.ArrayList;
import java.util.Random;

public class Human {
    protected Field field;
    protected ArrayList<Bacteria> bacteria = new ArrayList<>();

    public Human(Field field) {
        this.field = field;
    }

    public Field getField() {
        return field;
    }

    public void addBacteria(Bacteria bacteria) {
        this.bacteria.add(bacteria);
    }

    public void update() {
        boolean t = attemptMove();
        if (t == true) move(SimulationManager.fields);
        attemptInfect();
        attemptDie();
    }

    public void attemptInfect() {

    }

    public void attemptDie() {

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

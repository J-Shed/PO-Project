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

    }

    public void attemptInfect() {

    }

    public void attemptDie() {

    }

    public void attemptMove(ArrayList<Field> fields) {

    }
}

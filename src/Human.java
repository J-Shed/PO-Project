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
//
//    public update(Field[] fields, Human[] humans) {
//
//    }
//
//    public attemptInfect() {
//
//    }
//
//    public attemptDie() {
//
//    }
//
//    public attemptMove(Field[] fields) {
//
//    }
}

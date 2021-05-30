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
        this.bacteria.add(bacteria);
        bacteria.setField(field);

    }

    public boolean update() {
        boolean t = attemptMove();
        if (attemptMove()) {
            move(SimulationManager.fields);
            for (Bacteria k : bacteria) {
                k.setField(field);
            }
        }
        attemptInfect();
        while (i.hasNext()){
            Bacteria s = i.next();
            if(s.update()) i.remove();
        }
      return  attemptDie();
    }

    public void attemptInfect() {

    }

    public boolean attemptDie() {
        Random random = new Random();
        for(Bacteria k : bacteria){
            int number = random.nextInt(100);
            if(number<(k.mortality)) return true;
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
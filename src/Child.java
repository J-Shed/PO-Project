import java.util.Random;

public class Child extends Human {
    private int immunity;

    public Child(Field field, int immunity) {
        super(field);
        this.immunity = immunity;
    }
    public boolean attemptDie() {
        Random random = new Random();
        for(Bacteria k : bacteria){
            int number = random.nextInt(100);
            if(number<(k.mortality)*immunity) return true;
        }
        return false;
    }
}

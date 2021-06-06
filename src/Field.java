import java.util.ArrayList;

public class Field {
    public Level temperature;
    public Level humidity;
    protected ArrayList<Human> human = new ArrayList<>();

    public Field(Level temperature, Level humidity) {
        this.temperature = temperature;
        this.humidity = humidity;
    }
}

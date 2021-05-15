public class Field {
    private Level temperature;
    private Level humidity;

    public Field(Level temperature, Level humidity) {
        this.temperature = temperature;
        this.humidity = humidity;
    }

    public Level getTemperature() {
        return temperature;
    }

    public Level getHumidity() {
        return humidity;
    }
}

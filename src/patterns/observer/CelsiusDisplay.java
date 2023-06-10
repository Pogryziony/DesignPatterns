package patterns.observer;

public class CelsiusDisplay implements TemperatureObserver {
    @Override
    public void update(double temperature) {
        System.out.println("Temperature in Celsius: " + temperature + " °C");
    }
}

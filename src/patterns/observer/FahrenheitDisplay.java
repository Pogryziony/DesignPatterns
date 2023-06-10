package patterns.observer;

public class FahrenheitDisplay implements TemperatureObserver {
    @Override
    public void update(double temperature) {
        double fahrenheit = (temperature * 9/5) + 32;
        System.out.println("Temperature in Fahrenheit: " + fahrenheit + " °F");
    }
}

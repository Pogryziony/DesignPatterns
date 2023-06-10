package patterns.observer;

public class KelvinDisplay implements TemperatureObserver {
    @Override
    public void update(double temperature) {
        double kelvin = temperature + 273.15;
        System.out.println("Temperature in Kelvin: " + kelvin + " K");
    }
}

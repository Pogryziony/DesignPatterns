package patterns.observer;

import java.util.ArrayList;
import java.util.List;

public class TemperatureSensor {
    private double temperature;
    private List<TemperatureObserver> observers;

    public TemperatureSensor() {
        observers = new ArrayList<>();
    }

    public void addObserver(TemperatureObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(TemperatureObserver observer) {
        observers.remove(observer);
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
        notifyObservers();
    }

    private void notifyObservers() {
        for (TemperatureObserver observer : observers) {
            observer.update(temperature);
        }
    }
}

package alarmsystem;

import java.util.Random;

public class SmokeSensor implements Sensor {
    private Random random = new Random();
    private double batteryPercentage = 100.0;

    @Override
    public boolean isTriggered() {
        drainBattery();

        // 10% of the time it is called, it raises an alarm
        return (random.nextFloat() <= 0.10f);
    }

    @Override
    public String getLocation() {
        return "In the auditorium";
    }

    @Override
    public String getSensorType() {
        return "Smoke sensor";
    }

    private void drainBattery() {
        if (batteryPercentage > 0) {
            batteryPercentage -= (batteryPercentage * 0.2);
        }
    }

    @Override
    public double getBatteryPercentage() {
        return batteryPercentage;
    }
}

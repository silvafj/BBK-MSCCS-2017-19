package alarmsystem;

import java.util.Random;

public class FireSensor implements Sensor, Battery {

    private Random random = new Random();
    private double batteryPercentage = 100.0;

    @Override
    public boolean isTriggered() {
        drainBattery();

        // 5% of the time it is called, it raises an alarm
        return (random.nextFloat() <= 0.05f);
    }

    @Override
    public String getLocation() {
        return "Lobby 1st floor";
    }

    @Override
    public String getSensorType() {
        return "Fire";
    }

    private void drainBattery() {
        if (batteryPercentage > 0) {
            batteryPercentage -= (batteryPercentage * 0.1);
        }
    }

    @Override
    public double getBatteryPercentage() {
        return batteryPercentage;
    }

}

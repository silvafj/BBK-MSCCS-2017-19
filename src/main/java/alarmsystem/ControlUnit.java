package alarmsystem;

import java.util.ArrayList;
import java.util.List;

public class ControlUnit {

    public void pollSensors() {
        List<Sensor> sensors = new ArrayList<>();
        sensors.add(new FireSensor());
        sensors.add(new SmokeSensor());

        for (Sensor sensor : sensors) {
            if (sensor.isTriggered()) {
                System.out.println(sensor.getSensorType() + " sensor located in " + sensor.getLocation() + " has fired!");
            }
        }
    }
}

package alarmsystem;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class ControlUnit {

    private List<Sensor> sensors;

    @Autowired
    public ControlUnit(List<Sensor> sensors) {
        this.sensors = sensors;
    }

    public void pollSensors() {
        for (Sensor sensor : sensors) {
            if (sensor.isTriggered()) {
                System.out.println(sensor.getSensorType() + " sensor located in " + sensor.getLocation() + " has fired!");
            }
        }
    }
}

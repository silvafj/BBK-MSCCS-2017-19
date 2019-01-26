package alarmsystem;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SmokeSensorTest {

    @Test
    void getBatteryPercentage() {
        SmokeSensor sensor = new SmokeSensor();
        assertEquals(100.0, sensor.getBatteryPercentage());
        sensor.isTriggered();
        assertEquals(80.0, sensor.getBatteryPercentage());
        sensor.isTriggered();
        assertEquals(64.0, sensor.getBatteryPercentage());
    }
}
package alarmsystem;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FireSensorsTest {
    @Test
    void getBatteryPercentage() {
        FireSensor sensor = new FireSensor();
        assertEquals(100.0, sensor.getBatteryPercentage());
        sensor.isTriggered();
        assertEquals(90.0, sensor.getBatteryPercentage());
        sensor.isTriggered();
        assertEquals(81.0, sensor.getBatteryPercentage());
    }
}
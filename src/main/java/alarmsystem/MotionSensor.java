package alarmsystem;

public class MotionSensor implements Sensor {

    @Override
    public boolean isTriggered() {
        return true;
    }

    @Override
    public String getLocation() {
        return "Lobby 1st floor";
    }

    @Override
    public String getSensorType() {
        return "Motion";
    }

    @Override
    public SensorCategory getSensorCategory() {
        return SensorCategory.SECURITY;
    }
}

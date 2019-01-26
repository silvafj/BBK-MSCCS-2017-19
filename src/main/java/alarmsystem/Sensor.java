package alarmsystem;

public interface Sensor {
    boolean isTriggered();

    String getLocation();

    String getSensorType();
}

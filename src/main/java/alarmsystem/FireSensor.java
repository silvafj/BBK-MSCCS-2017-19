package alarmsystem;

public class FireSensor implements Sensor {
  @Override
  public boolean isTriggered() {
    return false;
  }

  @Override
  public String getLocation() {
    return null;
  }

  @Override
  public String getSensorType() {
    return null;
  }

  @Override
  public double getBatteryPercentage() {
    return -1;
  }
}

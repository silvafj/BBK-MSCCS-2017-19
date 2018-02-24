package three;

public class FreezingAndBoilingPoints {

    private double temperature;

    public FreezingAndBoilingPoints(double t) {
        temperature = t;
    }

    public double getTemperature() {
        return temperature;
    }

    /**
     * Method should check if the temperature is freezing
     *
     * @return true if is freezing
     */
    public boolean isFreezing(Element.Type element) {
        return getTemperature() <= Element.TEMPERATURES.get(element)[0];
    }

    /**
     * Method should check if the temperature is boiling
     *
     * @return true if is boiling
     */
    public boolean isBoiling(Element.Type element) {
        return getTemperature() >= Element.TEMPERATURES.get(element)[1];
    }
}
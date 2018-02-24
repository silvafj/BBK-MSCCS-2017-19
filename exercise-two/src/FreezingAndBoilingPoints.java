public class FreezingAndBoilingPoints {

    private static final double ETHYL_FREEZING = -173;
    private static final double ETHYL_BOILING = 172;
    private static final double OXYGEN_FREEZING = -362;
    private static final double OXYGEN_BOILING = -306;
    private static final double WATER_FREEZING = 32;
    private static final double WATER_BOILING = 212;

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
     * @return true if Ethyl is freezing
     */
    public boolean isEthylAlchoolFreezing() {
        return getTemperature() <= ETHYL_FREEZING;
    }

    /**
     * Method should check if the temperature is boiling
     *
     * @return true if Ethyl is boiling
     */
    public boolean isEthylAlchoolBoiling() {
        return getTemperature() >= ETHYL_BOILING;
    }

    /**
     * Method should check if the temperature is freezing
     *
     * @return true if Oxygen is freezing
     */
    public boolean isOxygenFreezing() {
        return getTemperature() <= OXYGEN_FREEZING;
    }

    /**
     * Method should check if the temperature is boiling
     *
     * @return true if Oxygen is boiling
     */
    public boolean isOxygenBoiling() {
        return getTemperature() >= OXYGEN_BOILING;
    }

    /**
     * Method should check if the temperature is freezing
     *
     * @return true if Water is freezing
     */
    public boolean isWaterFreezing() {
        return getTemperature() <= WATER_FREEZING;
    }

    /**
     * Method should check if the temperature is boiling
     *
     * @return true if Water is boiling
     */
    public boolean isWaterBoiling() {
        return getTemperature() >= WATER_BOILING;
    }
}
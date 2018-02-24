/**
 * This class represents a car travelling along the x-axis.
 * <p>
 * It is not immutable, because it must allow the state of private members variables to be changed after instantiation.
 */
public class Car {

    private double distance;
    private double efficiency;
    private double fuel;


    /**
     * Constructs a car with no fuel and no distance travelled.
     *
     * @param efficiency the fuel efficiency (in km/litres).
     */
    public Car(double efficiency) {
        if (efficiency <= 0) {
            throw new IllegalArgumentException("Efficiency must be positive.");
        }
        this.efficiency = efficiency;
    }

    /**
     * Returns the distance travelled from the origin.
     *
     * @return the current distance from the origin.
     */
    public double getDistance() {
        return distance;
    }

    /**
     * Returns the fuel efficiency (in km/litres).
     *
     * @return the fuel efficiency (in km/litres).
     */
    public double getEfficiency() {
        return efficiency;
    }

    /**
     * Returns the available fuel in litres.
     *
     * @return the fuel in litres.
     */
    public double getFuel() {
        return fuel;
    }

    /**
     * Drives a certain distance in kilometres. If there is not enough fuel, it will travel the maximum allowed by the
     * available fuel.
     *
     * @param km the distance to be travelled.
     */
    public void drive(double km) {
        double fuel_to_spend = km / getEfficiency();
        if (fuel_to_spend > getFuel()) {
            fuel_to_spend = getFuel();
        }

        distance += getEfficiency() * fuel_to_spend;
        fuel -= fuel_to_spend;
    }

    /**
     * Fills up the tank with fuel.
     *
     * @param litres the fuel to fill up the tank.
     */
    public void fillUp(double litres) {
        if (litres < 0) {
            throw new IllegalArgumentException("Litres must be positive.");
        }
        this.fuel += litres;
    }

}



/*
(+) Implement a class Car that models a car traveling along the x-axis, consuming fuel as it moves.

 Provide methods to drive by a given number of kilometres, to add a given number of litres to the fuel tank, and to get

 the current distance from the origin and fuel level. Specify the fuel efficiency (in km/litres) in the constructor.


 Should this be an immutable class? Why or why not?
 */
package fraction;

/**
 * Representation of a rational number, using Euclid's algorithm.
 *
 * @author Fernando Silva (fdealm02>)
 */
public class FractionImpl implements Fraction {

    private int numerator, denominator;

    /**
     * Create a fraction given the numerator and denominator. The fraction is reduced to lowest terms.
     *
     * @param numerator the numerator.
     * @param denominator the denominator.
     */
    public FractionImpl(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    /**
     * Create a fraction from an int. The fraction is wholeNumber / 1.
     *
     * @param wholeNumber the numerator.
     */
    public FractionImpl(int wholeNumber) {

    }

    /**
     * Create a fraction from a String, with the format numerator/denominator.
     *
     * @param fraction the fraction as a String.
     */
    public FractionImpl(String fraction) {

    }

    /**
     * Adds the value of another fraction to this one.
     *
     * @param f the fraction to add.
     * @return a Fraction instance.
     */
    public Fraction add(Fraction f) {
        return null;
    }

    /**
     * Subtracts the value of another fraction from the value of this one.
     *
     * @param f the fraction to subtract.
     * @return a Fraction instance.
     */
    public Fraction subtract(Fraction f) {
        return null;
    }

    /**
     * Multiplies the value of another fraction with this one.
     *
     * @param f the fraction to multiply by.
     * @return a Fraction instance.
     */
    public Fraction multiply(Fraction f) {
        return null;
    }

    /**
     * Divides the value of this fraction by another.
     *
     * @param f the fraction to divide by.
     * @return a Fraction instance.
     */
    public Fraction divide(Fraction f) {
        return null;
    }

    /**
     * Returns the absolute value of this fraction.
     *
     * @return a Fraction instance.
     */
    public Fraction abs() {
        return null;
    }

    /**
     * Returns a fraction that has same numeric value of this fraction, but the opposite sign.
     *
     * @return a Fraction instance.
     */
    public Fraction negate() {
        return null;
    }

    /**
     * Returns the inverse of this fraction.
     *
     * @return a Fraction instance.
     */
    public Fraction inverse() {
        return null;
    }

    /**
     * Gets a hashCode for the fraction.
     *
     * @return a hash code value for this object
     */
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    /**
     * Test for the equality of two fractions. If the lowest term numerator and denominators are the same for both
     * fractions, the two fractions are considered to be equal.
     *
     * @param obj fraction to test for equality to this fraction.
     * @return true if two fractions are equal, false if object is null, not an instance of Fraction,
     *         or not equal to this fraction instance.
     */
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    /**
     * @return
     * @throws CloneNotSupportedException
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    /**
     * Compares this Fraction to another object.
     *
     * @param o the fraction to compare to.
     * @return -1 if this is less than object, +1 if this is greater than object, 0 if they are equal.
     */
    @Override
    public int compareTo(Fraction o) {
        return 0;
    }

    /**
     * Returns the String representing this fraction, e.g, "num / dem" or just "num" if the denominator is one.
     *
     * @return a string representation of the fraction.
     */
    @Override
    public String toString() {
        return null;
    }
}

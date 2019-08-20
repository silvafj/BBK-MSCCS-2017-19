package fraction;

/**
 * Representation of a rational number.
 *
 * @author Fernando Silva (fdealm02)
 */
public interface Fraction extends Comparable<Fraction> {

    /**
     * Adds the value of another fraction to this one.
     *
     * @param f the fraction to add.
     * @return a <code>Fraction</code> instance.
     */
    Fraction add(Fraction f);

    /**
     * Subtracts the value of another fraction from the value of this one.
     *
     * @param f the fraction to subtract.
     * @return a <code>Fraction</code> instance.
     */
    Fraction subtract(Fraction f);

    /**
     * Multiplies the value of another fraction with this one.
     *
     * @param f the fraction to multiply by.
     * @return a <code>Fraction</code> instance.
     */
    Fraction multiply(Fraction f);

    /**
     * Divides the value of this fraction by another.
     *
     * @param f the fraction to divide by.
     * @return a <code>Fraction</code> instance.
     */
    Fraction divide(Fraction f);

    /**
     * Returns the absolute value of this fraction.
     *
     * @return a <code>Fraction</code> instance.
     */
    Fraction abs();

    /**
     * Returns a fraction that has same numeric value of this fraction, but the opposite sign.
     *
     * @return a <code>Fraction</code> instance.
     */
    Fraction negate();

    /**
     * Returns the inverse of this fraction.
     *
     * @return a <code>Fraction</code> instance.
     */
    Fraction inverse();

    /**
     * Test for the equality of two fractions. If the lowest term numerator and denominators are the same for both
     * fractions, the two fractions are considered to be equal.
     *
     * @param o fraction to test for equality to this fraction.
     * @return <code>true</code> if two fractions are equal, <code>false</code> if object is <code>null</code>,
     * not an instance of <code>Fraction</code>, or not equal to this fraction instance.
     */
    @Override
    boolean equals(Object o);

    /**
     * Compares this <code>Fraction</code> to another object.
     *
     * @param f the fraction to compare to.
     * @return negative <code>int</code> if this is less than <code>f</code>;
     * positive <code>int</code> if this is greater than <code>f</code>
     * zero if they are equal.
     */
    @Override
    int compareTo(Fraction f);

    /**
     * Returns the <code>String</code> representing this fraction, e.g, "num / dem" or just "num" if the denominator is
     * one.
     *
     * @return a string representation of the fraction.
     */
    @Override
    String toString();

}
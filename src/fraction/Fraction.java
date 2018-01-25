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
     * @return a Fraction instance.
     */
    Fraction add(Fraction f);

    /**
     * Subtracts the value of another fraction from the value of this one.
     *
     * @param f the fraction to subtract.
     * @return a Fraction instance.
     */
    Fraction subtract(Fraction f);

    /**
     * Multiplies the value of another fraction with this one.
     *
     * @param f the fraction to multiply by.
     * @return a Fraction instance.
     */
    Fraction multiply(Fraction f);

    /**
     * Divides the value of this fraction by another.
     *
     * @param f the fraction to divide by.
     * @return a Fraction instance.
     */
    Fraction divide(Fraction f);

    /**
     * Returns the absolute value of this fraction.
     *
     * @return a Fraction instance.
     */
    Fraction abs();

    /**
     * Returns a fraction that has same numeric value of this fraction, but the opposite sign.
     *
     * @return a Fraction instance.
     */
    Fraction negate();

    /**
     * Returns the inverse of this fraction.
     *
     * @return a Fraction instance.
     */
    Fraction inverse();
}
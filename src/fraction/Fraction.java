package fraction;

/**
 *
 */
public interface Fraction extends Comparable<Fraction> {
    /**
     *
     * @param f
     * @return
     */
    Fraction add(Fraction f);

    /**
     *
     * @param f
     * @return
     */
    Fraction subtract(Fraction f);

    /**
     *
     * @param f
     * @return
     */
    Fraction multiply(Fraction f);

    /**
     *
     * @param f
     * @return
     */
    Fraction divide(Fraction f);

    /**
     *
     * @return
     */
    Fraction abs();

    /**
     *
     * @return
     */
    Fraction negate();

    /**
     *
     * @return
     */
    Fraction inverse();

    /**
     *
     * @param o
     * @return
     */
    @Override
    boolean equals(Object o);
}
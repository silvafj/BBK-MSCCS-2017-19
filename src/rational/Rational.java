package fraction;

import java.math.BigInteger;
import java.util.Objects;

public class Fraction implements Comparable {
    private BigInteger numerator;
    private BigInteger denominator;

    {
        numerator = BigInteger.ZERO;
        denominator = BigInteger.ONE;
    }

    /**
     * Default constructor creates a Fraction of "1"
     */
    public Fraction() {
        this(BigInteger.ONE);
    }

    /**
     * The parameter is the numerator and 1 is the implicit denominator.
     *
     * @param wholeNumber of the fraction
     */
    public Fraction(BigInteger wholeNumber) {
        this(wholeNumber, BigInteger.ONE);
    }

    /**
     * Normalize the fraction as you create it.
     * For instance, if the parameters are (8, -12), create a Fraction with numerator -2 and denominator 3.
     * The constructor should throw an ArithmeticException if the denominator is zero.
     *
     * @param num   numerator of the fraction
     * @param denom denominator of the fraction
     */
    public Fraction(BigInteger num, BigInteger denom) {
        BigInteger gcd = num.gcd(denom);
        setNumerator(num.divide(gcd));
        setDenominator(denom.divide(gcd));

        if (getDenominator().compareTo(BigInteger.ZERO) < 0) {
            setNumerator(getNumerator().negate());
            setDenominator(getDenominator().negate());
        }
    }

    /**
     * Allow blanks around (but not within) integers.
     * The constructor should throw an ArithmeticException if given a string representing
     * a fraction whose denominator is zero.
     *
     * @param fraction a String containing either a whole number, such as "5" or " -3", or a fraction, such as "8/ -12".
     */
    public Fraction(String fraction) throws ArithmeticException {
        // TODO replace with your code
    }

    private BigInteger getNumerator() {
        return numerator;
    }

    private void setNumerator(BigInteger numerator) {
        this.numerator = numerator;
    }

    private BigInteger getDenominator() {
        return denominator;
    }

    private void setDenominator(BigInteger denominator) {
        this.denominator = denominator;
    }

    /**
     * Returns a new Fraction that is the sum of this and that:
     * a/b + c/d is (ad + bc)/bd
     *
     * @param f the Fraction you wish to add to this fraction
     * @return a new Fraction resulting from the addition
     */
    public Fraction add(Fraction f) {
        BigInteger num = f.getNumerator().multiply(getDenominator()).add(getNumerator().multiply(f.getDenominator()));
        BigInteger den = getDenominator().multiply(f.getDenominator());
        return new Fraction(num, den);
    }

    /**
     * Returns a new Fraction that is the difference of this minus that:
     * a/b - c/d is (ad - bc)/bd
     *
     * @param f the Fraction you wish to subtract from this fraction
     * @return a new Fraction resulting from the subtraction
     */
    public Fraction subtract(Fraction f) {
        return add(f.negate());
    }

    /**
     * Returns a new Fraction that is the product of this and that:
     * (a/b) * (c/d) is (a*c)/(b*d)
     *
     * @param f the Fraction you wish to multiply this fraction with
     * @return a new Fraction resulting from the multiplication
     */
    public Fraction multiply(Fraction f) {
        BigInteger a = this.getNumerator();
        BigInteger b = this.getDenominator();
        BigInteger c = f.getNumerator();
        BigInteger d = f.getDenominator();
        return new Fraction(a.multiply(c), b.multiply(d));
    }

    /**
     * Returns a new Fraction that is the quotient of dividing this by that:
     * (a/b) / (c/d) is (a*d)/(b*c)
     *
     * @param f the fraction you wish to divide this fraction by
     * @return a new fraction resulting from the division
     */
    public Fraction divide(Fraction f) {
        return multiply(new Fraction(f.getDenominator(), f.getNumerator()));
    }

    /**
     * @return a new Fraction that is the absolute value of this fraction
     */
    public Fraction abs() {
        // TODO replace with your code
        return new Fraction(BigInteger.ONE);
    }

    /**
     * @return a new Fraction that has the same numeric value of this fraction, but the opposite sign.
     */
    public Fraction negate() {
        return new Fraction(getNumerator().negate(), getDenominator());
    }

    /**
     * @return The inverse of a/b is b/a.
     */
    public Fraction inverse() {
        // TODO replace with your code
        return new Fraction(BigInteger.ONE);
    }

    /**
     * @param o the object to compare this object to
     * @return If o is a Fraction this method returns:
     * A negative int if this is less than o.
     * Zero if this is equal to o.
     * A positive int if this is greater than o.
     * If o is not a Fraction, this method throws a ClassCastException.
     */
    @Override
    public int compareTo(Object o) throws ClassCastException {
        if (!(o instanceof Fraction)) {
            throw new ClassCastException();
        }
        Fraction value = (Fraction) o;

        return (this.subtract(value)).getNumerator().signum();
    }

    /**
     * @return a String of the form n/d, where n is the numerator and d is the denominator.
     * However, if d is 1, just return n (as a String).
     * The returned String should not contain any blanks.
     * If the fraction represents a negative number, a minus sign should precede the n.
     */
    @Override
    public String toString() {
        return (getDenominator().equals(BigInteger.ONE)) ? "" + getNumerator()
            : getNumerator() + "/" + getDenominator();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumerator(), getDenominator());
    }

    /**
     * @param o the Fraction to compare
     * @return true if o is a Fraction equal to this, and false in all other cases.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Fraction)) return false;
        Fraction fraction = (Fraction) o;
        return getNumerator().equals(fraction.getNumerator()) &&
            getDenominator().equals(fraction.getDenominator());
    }
}
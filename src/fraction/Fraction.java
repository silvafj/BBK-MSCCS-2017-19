package fraction;

import java.util.Objects;

public class Fraction implements Comparable {
    private int numerator;
    private int denominator;

    /**
     * Normalize the fraction as you create it.
     * For instance, if the parameters are (8, -12), create a Fraction with numerator -2 and denominator 3.
     * The constructor should throw an ArithmeticException if the denominator is zero.
     *
     * @param numerator   of the fraction
     * @param denominator of the fraction
     */
    public Fraction(int numerator, int denominator) {

    }

    /**
     * The parameter is the numerator and 1 is the implicit denominator.
     *
     * @param wholeNumber of the fraction
     */
    public Fraction(int wholeNumber) {

    }

    /**
     * Allow blanks around (but not within) integers.
     * The constructor should throw an ArithmeticException if given a string representing
     * a fraction whose denominator is zero.
     *
     * @param fraction a String containing either a whole number, such as "5" or " -3", or a fraction, such as "8/ -12".
     */
    public Fraction(String fraction) {

    }

    private int getNumerator() {
        return numerator;
    }

    private void setNumerator(int numerator) {
        this.numerator = numerator;
    }

    private int getDenominator() {
        return denominator;
    }

    private void setDenominator(int denominator) {
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
        // TODO replace with your code
        return new Fraction(1);
    }

    /**
     * Returns a new Fraction that is the difference of this minus that:
     * a/b - c/d is (ad - bc)/bd
     *
     * @param f the Fraction you wish to subtract from this fraction
     * @return a new Fraction resulting from the subtraction
     */
    public Fraction subtract(Fraction f) {
        // TODO replace with your code
        return new Fraction(1);
    }

    /**
     * Returns a new Fraction that is the product of this and that:
     * (a/b) * (c/d) is (a*c)/(b*d)
     *
     * @param f the Fraction you wish to multiply this fraction with
     * @return a new Fraction resulting from the multiplication
     */
    public Fraction multiply(Fraction f) {
        // TODO replace with your code
        return new Fraction(1);
    }

    /**
     * Returns a new Fraction that is the quotient of dividing this by that:
     * (a/b) / (c/d) is (a*d)/(b*c)
     *
     * @param f the fraction you wish to divide this fraction by
     * @return a new fraction resulting from the division
     */
    public Fraction divide(Fraction f) {
        // TODO replace with your code
        return new Fraction(1);
    }

    /**
     * @return a new Fraction that is the absolute value of this fraction
     */
    public Fraction abs() {
        // TODO replace with your code
        return new Fraction(1);
    }

    /**
     * @return a new Fraction that has the same numeric value of this fraction, but the opposite sign.
     */
    public Fraction negate() {
        // TODO replace with your code
        return new Fraction(1);
    }

    /**
     * @return The inverse of a/b is b/a.
     */
    public Fraction inverse() {
        // TODO replace with your code
        return new Fraction(1);
    }

    /**
     * @param o the Fraction to compare
     * @return true if o is a Fraction equal to this, and false in all other cases.
     */
    @Override
    public boolean equals(Object o) {
        // TODO replace with your code
        return true;
    }

    /**
     * @param o the object to compare this object to
     * @return If o is a Fraction or an int, this method returns:
     * A negative int if this is less than o.
     * Zero if this is equal to o.
     * A positive int if this is greater than o.
     * If o is neither a Fraction nor an int, this method throws a ClassCastException.
     */
    @Override
    public int compareTo(Object o) {
        // TODO replace with your code
        return 1;
    }

    /**
     * @return a String of the form n/d, where n is the numerator and d is the denominator.
     * However, if d is 1, just return n (as a String).
     * The returned String should not contain any blanks.
     * If the fraction represents a negative number, a minus sign should precede the n.
     */
    @Override
    public String toString() {
        // TODO replace with your code
        return "";
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumerator(), getDenominator());
    }
}
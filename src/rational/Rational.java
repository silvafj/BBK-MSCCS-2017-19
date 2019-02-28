package rational;

import java.util.Objects;

public class Rational implements Comparable {
    private long numerator;
    private long denominator;

    {
        numerator = 0L;
        denominator = 1L;
    }

    /**
     * Default constructor creates a Rational of "1"
     */
    public Rational() {
        this(1L);
    }

    /**
     * The parameter is the numerator and 1 is the implicit denominator.
     *
     * @param wholeNumber of the fraction
     */
    public Rational(long wholeNumber) {
        initialiseNumDenom(wholeNumber, 1);
    }

    /**
     * Normalize the fraction as you create it.
     * For instance, if the parameters are (8, -12), create a Rational with numerator -2 and denominator 3.
     * The constructor should throw an ArithmeticException if the denominator is zero.
     *
     * @param num   numerator of the fraction
     * @param denom denominator of the fraction
     */
    public Rational(long num, long denom) {
        initialiseNumDenom(num, denom);
    }

    /**
     * Allow blanks around (but not within) integers.
     * The constructor should throw an ArithmeticException if given a string representing
     * a fraction whose denominator is zero.
     *
     * @param fraction a String containing either a whole number, such as "5" or " -3", or a fraction, such as "8/ -12".
     */
    public Rational(String fraction) throws ArithmeticException {
        if (fraction.isEmpty()) {
            throw new ArithmeticException("Fraction is empty.");
        }
        if (fraction.indexOf("/")==-1){
            initialiseNumDenom(Long.valueOf(fraction), 1);
            return;
        }
        String[] parts = fraction.split("/");
        if (parts.length !=2 || parts[0].isEmpty()) {
            throw new ArithmeticException ("Fraction format is wrong.");
        }

        initialiseNumDenom(Long.valueOf(parts[0]), Long.valueOf(parts[1]));
    }

    private void initialiseNumDenom(long num, long denom) {
        if (denom == 0) {
            throw new ArithmeticException("Cannot divide by zero!");
        }
        long gcd = gcd(num, denom);
        setNumerator(num / gcd);
        setDenominator(denom / gcd);


        if (getDenominator() < 0) { // CHECK
            setNumerator(-1L * getNumerator());
            setDenominator(-1L * getDenominator());
        }
    }

    /**
     * Find the Greatest Common Divisor of two numbers
     * <p>
     * Uses Euclid's algorithm
     *
     * @param num   the first number
     * @param denom the second number
     * @return the gcd of <tt>num</tt> and <tt>denom</tt>
     */
    private long gcd(long num, long denom) {
        while (denom != 0) {
            long t = denom;
            denom = num % denom;
            num = t;
        }

        return num;
    }

    // DO NOT CHANGE THE VISIBILITY OF THIS METHOD

    private long getNumerator() {
        return numerator;
    }

    // DO NOT CHANGE THE VISIBILITY OF THIS METHOD

    private void setNumerator(long numerator) {
        this.numerator = numerator;
    }

    // DO NOT CHANGE THE VISIBILITY OF THIS METHOD

    private long getDenominator() {
        return denominator;
    }

    // DO NOT CHANGE THE VISIBILITY OF THIS METHOD

    private void setDenominator(long denominator) {
        this.denominator = denominator;
    }

    /**
     * Returns a new Rational that is the sum of this and that:
     * a/b + c/d is (ad + bc)/bd
     *
     * @param f the Rational you wish to add to this fraction
     * @return a new Rational resulting from the addition
     */
    public Rational add(Rational f) {
        long num = f.getNumerator() * getDenominator() + getNumerator() * f.getDenominator();
        long den = getDenominator() * f.getDenominator();
        return new Rational(num, den);
    }

    /**
     * Returns a new Rational that is the difference of this minus that:
     * a/b - c/d is (ad - bc)/bd
     *
     * @param f the Rational you wish to subtract from this fraction
     * @return a new Rational resulting from the subtraction
     */
    public Rational subtract(Rational f) {
        return add(f.negate());
    }

    /**
     * Returns a new Rational that is the product of this and that:
     * (a/b) * (c/d) is (a*c)/(b*d)
     *
     * @param f the Rational you wish to multiply this fraction with
     * @return a new Rational resulting from the multiplication
     */
    public Rational multiply(Rational f) {
        long a = this.getNumerator();
        long b = this.getDenominator();
        long c = f.getNumerator();
        long d = f.getDenominator();
        return new Rational(a * c, b * d);
    }

    /**
     * Returns a new Rational that is the quotient of dividing this by that:
     * (a/b) / (c/d) is (a*d)/(b*c)
     *
     * @param f the fraction you wish to divide this fraction by
     * @return a new fraction resulting from the division
     */
    public Rational divide(Rational f) {

        return multiply(new Rational(f.getDenominator(), f.getNumerator()));
    }

    /**
     * @return a new Rational that is the absolute value of this fraction
     */
    public Rational abs() {
        return new Rational(Math.abs(this.getNumerator()), Math.abs(this.getDenominator()));
    }

    /**
     * @return a new Rational that has the same numeric value of this fraction, but the opposite sign.
     */
    public Rational negate() {
        return new Rational(-1L * getNumerator(), getDenominator());
    }

    /**
     * @return The inverse of a/b is b/a.
     */
    public Rational inverse() {
        return new Rational(this.getDenominator(), this.getNumerator());
    }

    /**
     * @param o the object to compare this object to
     * @return If o is a Rational this method returns:
     * A negative int if this is less than o.
     * Zero if this is equal to o.
     * A positive int if this is greater than o.
     * If o is not a Rational, this method throws a ClassCastException.
     */
    @Override
    public int compareTo(Object o) throws ClassCastException {
        if (!(o instanceof Rational)) {
            throw new ClassCastException();
        }
        Rational value = (Rational) o;

        return (int) (this.subtract(value)).getNumerator(); // FIX --- this is WRONG!!!
    }

    /**
     * @return a String of the form n/d, where n is the numerator and d is the denominator.
     * However, if d is 1, just return n (as a String).
     * The returned String should not contain any blanks.
     * If the fraction represents a negative number, a minus sign should precede the n.
     */
    @Override
    public String toString() {
        return (getDenominator() == 1L) ? "" + getNumerator()
                : getNumerator() + "/" + getDenominator();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumerator(), getDenominator());
    }

    /**
     * @param o the Rational to compare
     * @return true if o is a Rational equal to this, and false in all other cases.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rational)) return false;
        Rational rational = (Rational) o;
        return getNumerator() == rational.getNumerator() &&
                getDenominator() == rational.getDenominator();
    }
}
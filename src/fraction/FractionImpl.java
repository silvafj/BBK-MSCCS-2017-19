package fraction;

import java.util.Objects;

/**
 * Representation of a rational number, using Euclid's algorithm.
 *
 * @author Fernando Silva (fdealm02)
 */
public class FractionImpl implements Fraction {

    private int numerator, denominator;

    /**
     * Creates a fraction from an <code>int</code>. The fraction is <code>wholeNumber / 1</code>.
     *
     * @param wholeNumber the numerator.
     */
    public FractionImpl(int wholeNumber) {
        this.numerator = wholeNumber;
        this.denominator = 1;
    }

    /**
     * Creates a fraction given the numerator and denominator. <code>Fraction</code> will be normalised to lowest terms
     * with Euclid's algorithm.
     *
     * @param numerator   the numerator.
     * @param denominator the denominator.
     * @throws ArithmeticException if denominator is zero.
     */
    public FractionImpl(int numerator, int denominator) throws ArithmeticException {
        validateAndNormalize(numerator, denominator);
    }

    /**
     * Creates a fraction from a <code>String</code>, with the format "numerator/denominator" or is a whole number.
     * <code>Fraction</code> will be normalised to lowest terms with Euclid's algorithm.
     *
     * @param fraction the fraction (or whole number) as a <code>String</code>.
     * @throws ArithmeticException   if denominator is zero.
     * @throws NumberFormatException if fraction is malformed.
     */
    public FractionImpl(String fraction) throws ArithmeticException, NumberFormatException {
        String[] parts = fraction.split("/");

        if (parts.length == 0 || parts.length > 2) {
            throw new NumberFormatException("Fraction is malformed.");
        } else {
            int numerator = Integer.parseInt(parts[0].trim());
            if (parts.length == 1) {
                this.numerator = numerator;
                this.denominator = 1;
            } else {
                int denominator = Integer.parseInt(parts[1].trim());
                validateAndNormalize(numerator, denominator);
            }
        }
    }

    /**
     * Calculates the Greatest Common Divisor for two numbers using Euclid's algorithm.
     *
     * @param number1 the first number.
     * @param number2 the second number.
     * @return the Greatest Common Divisor.
     * @throws ArithmeticException if number1 or number2 are zero.
     */
    private int gcd(int number1, int number2) throws ArithmeticException {
        if (number1 == 0 || number2 == 0) throw new ArithmeticException("Numbers must be non-zero.");

        if (number1 == number2) {
            return number1;
        } else if (number2 > number1) {
            // swap numbers, as we will be dividing by the lowest one
            int temp = number1;
            number1 = number2;
            number2 = temp;
        }

        while (number2 != 0) {
            int temp = number2;
            number2 = number1 % number2;
            number1 = temp;
        }

        return number1;
    }

    /**
     * Validates the numerator and denominator.
     * 1. If the fraction is negative, the sign is applied to the numerator
     * 2. The denominator is never negative
     * 3. Zero is always represented with numerator 0 and denominator 1
     *
     * @param numerator   the numerator.
     * @param denominator the denominator.
     * @throws ArithmeticException if denominator is zero.
     */
    private void validateAndNormalize(int numerator, int denominator) throws ArithmeticException {
        if (denominator == 0) {
            throw new ArithmeticException("Divide by zero");
        } else if (denominator < 0) {
            numerator *= -1;
            denominator *= -1;
        }

        if (numerator == 0) {
            this.numerator = 0;
            this.denominator = 1;
        } else {
            int common = gcd(Math.abs(numerator), denominator);
            this.numerator = numerator / common;
            this.denominator = denominator / common;
        }
    }

    /**
     * {@inheritDoc}
     */
    public Fraction add(Fraction f) {
        FractionImpl fi = (FractionImpl) f;

        // a/b + c/d = (ad + bc) / bd
        return new FractionImpl(
                this.numerator * fi.denominator + this.denominator * fi.numerator,
                this.denominator * fi.denominator
        );
    }

    /**
     * {@inheritDoc}
     */
    public Fraction subtract(Fraction f) {
        FractionImpl fi = (FractionImpl) f;

        // a/b - c/d = (ad - bc) / bd
        return new FractionImpl(
                this.numerator * fi.denominator - this.denominator * fi.numerator,
                this.denominator * fi.denominator
        );
    }

    /**
     * {@inheritDoc}
     */
    public Fraction multiply(Fraction f) {
        FractionImpl fi = (FractionImpl) f;

        // (a/b) * (c/d) = (a*c) / (b*d)
        return new FractionImpl(
                this.numerator * fi.numerator,
                this.denominator * fi.denominator
        );
    }

    /**
     * {@inheritDoc}
     */
    public Fraction divide(Fraction f) {
        FractionImpl fi = (FractionImpl) f;

        // (a/b) / (c/d) = (a*d) / (b*c)
        return new FractionImpl(
                this.numerator * fi.denominator,
                this.denominator * fi.numerator
        );
    }

    /**
     * {@inheritDoc}
     */
    public Fraction abs() {
        return new FractionImpl(Math.abs(this.numerator), this.denominator);
    }

    /**
     * {@inheritDoc}
     */
    public Fraction negate() {
        return new FractionImpl(this.numerator * -1, this.denominator);
    }

    /**
     * {@inheritDoc}
     */
    public Fraction inverse() {
        return new FractionImpl(this.denominator, this.numerator);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        // According to item 9 in Josh Bloch's Effective Java
        return Objects.hash(this.numerator, this.denominator);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof FractionImpl) {
            FractionImpl f = (FractionImpl) o;
            return this.numerator == f.numerator && this.denominator == f.denominator;
        }

        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int compareTo(Fraction f) {
        // To compare two fractions we have to normalize them to have a common denominator
        FractionImpl fi = (FractionImpl) f;
        return (this.numerator * fi.denominator) - (fi.numerator * this.denominator);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return denominator == 1 ? String.valueOf(numerator) : numerator + "/" + denominator;
    }

}

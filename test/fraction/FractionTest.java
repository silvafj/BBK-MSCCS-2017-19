package fraction;

import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.*;

/**
 * This is a junit based test class for the Fraction coursework
 * <p>
 * This is a poor piece of include - augment and improve as you see fit
 */

public class FractionTest {
    private final Fraction def;
    private final Fraction one;
    private final Fraction two;
    private final Fraction twoOverFour;
    private final Fraction aQuarter;
    private final Fraction aHalf;
    private final Fraction oneFifth;
    private final Fraction minusOneFifth;
    private final Fraction minusOneFifthBottomSign;
    private final Fraction minusHalfSignBottomAndTop;
    private final Fraction twelveOverSeventeen;
    private final static String ONE = "1";
    private final static String TWO = "2";
    private final static String FOUR = "4";

    {
        def = new Fraction();
        one = new Fraction(BigInteger.ONE);
        two = new Fraction(BigInteger.TWO);
        twoOverFour = new Fraction(BigInteger.TWO, new BigInteger("4"));
        aQuarter = new Fraction(BigInteger.ONE, new BigInteger("4"));
        aHalf = new Fraction(BigInteger.ONE, BigInteger.TWO);
        oneFifth = new Fraction(BigInteger.ONE, new BigInteger("5"));
        minusOneFifth = new Fraction(BigInteger.ONE.negate(), new BigInteger("5"));
        minusOneFifthBottomSign = new Fraction(BigInteger.ONE, new BigInteger("5").negate());
        minusHalfSignBottomAndTop = new Fraction(BigInteger.ONE.negate(), BigInteger.TWO.negate());
        twelveOverSeventeen = new Fraction(new BigInteger("12"), new BigInteger("17"));
    }

    /**
     * Test for default constructor
     */
    @Test
    public void testToString() {
        assertEquals(ONE, def.toString());
        assertEquals(TWO, two.toString());
        assertEquals("1/2", twoOverFour.toString());
        assertEquals("12/17", new Fraction(new BigInteger("12"), new BigInteger("17")).toString());
    }

    /**
     * Test for construction from String
     */
    @Test
    public void testConsWithString() {
        // TODO
        fail();
    }

    /**
     * Test for correct handling of negatives
     */
    @Test
    public void testNegative() {
        assertEquals("-1/5", minusOneFifthBottomSign.toString());
        assertEquals("1/2", minusHalfSignBottomAndTop.toString());
    }

    @Test
    public void add() {
        assertEquals(new Fraction(new BigInteger("41"), new BigInteger("34")),
            twelveOverSeventeen.add(twoOverFour));
        assertEquals(aHalf, aQuarter.add(aQuarter));
        assertNotEquals(aQuarter, aQuarter.add(aQuarter));
        assertEquals(one, aHalf.add(aHalf));
    }

    @Test
    public void subtract() {
        assertEquals(aQuarter, aHalf.subtract(aQuarter));
    }

    @Test
    public void multiply() {
        assertEquals(aQuarter, aHalf.multiply(aHalf));
    }

    @Test
    public void divide() {
        assertEquals(aHalf, new Fraction(new BigInteger("12")).divide(new Fraction(new BigInteger("24"))));
    }

    @Test
    public void divideByZero() {
        // TODO
        fail();
    }

    @Test
    public void abs() {
        assertEquals(oneFifth, minusOneFifth.abs());
        assertNotEquals(minusOneFifth, minusOneFifth.abs());
    }

    @Test
    public void negate() {
        assertEquals(minusOneFifth, oneFifth.negate());
    }

    @Test
    public void inverse() {
        assertEquals(two, aHalf.inverse());
        assertEquals(aHalf, two.inverse());
        assertNotEquals(aHalf, aHalf.inverse());
        assertEquals(minusOneFifth, new Fraction(new BigInteger("-5"), BigInteger.ONE).inverse());
    }

    @Test
    public void testEquals() {
        assertEquals(new Fraction(new BigInteger("12"), new BigInteger("17")), twelveOverSeventeen);
    }

    @Test
    public void testCompareTo() {
        assertTrue(aQuarter.compareTo(aHalf) < 0);
        assertFalse(aQuarter.compareTo(aHalf) > 0);
        assertEquals(0, aQuarter.compareTo(aQuarter));
        assertNotEquals(1, aQuarter.compareTo(aQuarter));
    }

    @Test
    public void testHashCode() {
        Fraction half = new Fraction(BigInteger.ONE, BigInteger.TWO);
        Fraction otherHalf = new Fraction(BigInteger.ONE, BigInteger.TWO);
        assertEquals(half.hashCode(), otherHalf.hashCode());
    }

    // Any other tests you feel are appropriate and aren't covered by the above
}
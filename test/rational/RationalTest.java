package rational;

import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.*;

/**
 * This is a junit based test class for the Rational coursework
 * <p>
 * This is a poor piece of include - augment and improve as you see fit
 */

public class RationalTest {
    private final static String ONE = "1";
    private final static String TWO = "2";
    private final Rational def;
    private final Rational one;
    private final Rational twoOverFour;
    private final Rational aQuarter;
    private final Rational aHalf;
    private final Rational oneFifth;
    private final Rational minusOneFifth;
    private final Rational minusOneFifthBottomSign;
    private final Rational minusHalfSignBottomAndTop;
    private final Rational twelveOverSeventeen;

    {
        def = new Rational();
        one = new Rational(1L);
        twoOverFour = new Rational(2L, 4L);
        aQuarter = new Rational(1L, 4L);
        aHalf = new Rational(1L, 2L);
        oneFifth = new Rational(1L, 5L);
        minusOneFifth = new Rational(-1L, 5L);
        minusOneFifthBottomSign = new Rational(1L, -5L);
        minusHalfSignBottomAndTop = new Rational(-1L, -2L);
        twelveOverSeventeen = new Rational(12L, 17L);
    }

    /**
     * Test for default constructor
     */
    @Test
    public void testToString() {
        assertEquals(ONE, def.toString());
        assertEquals("1/2", twoOverFour.toString());
        assertEquals("12/17", new Rational(12L, 17L).toString());
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
        assertEquals(new Rational(41L, 34L),
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
        assertEquals(aHalf, new Rational(12L).divide(new Rational(24L)));
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
        Rational two = new Rational(2L);
        assertEquals(two, aHalf.inverse());
        assertEquals(aHalf, two.inverse());
        assertNotEquals(aHalf, aHalf.inverse());
        assertEquals(minusOneFifth, new Rational(-5L, 1L).inverse());
    }

    @Test
    public void testEquals() {
        assertEquals(new Rational(12L, 17L), twelveOverSeventeen);
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
        Rational half = new Rational(1L, 2L);
        Rational otherHalf = new Rational(1L, 2L);
        assertEquals(half.hashCode(), otherHalf.hashCode());
    }

    // Any other tests you feel are appropriate and aren't covered by the above
}
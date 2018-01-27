package fraction;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;


public class FractionTest {

    @Test
    public void testValidFractions() {
        assertEquals("1/1", (new FractionImpl(1)).toString());
        assertEquals("0/1", (new FractionImpl(0)).toString());
        assertEquals("-1/1", (new FractionImpl(-1)).toString());

        assertEquals("1/1", (new FractionImpl(1, 1)).toString());
        assertEquals("0/1", (new FractionImpl(0, 1)).toString());
        assertEquals("-1/1", (new FractionImpl(-1, 1)).toString());
        assertEquals("-2/3", (new FractionImpl(8, -12)).toString());

        assertEquals("1/1", (new FractionImpl(" 1 /1 ")).toString());
        assertEquals("0/1", (new FractionImpl("0/1")).toString());
        assertEquals("0/1", (new FractionImpl("0")).toString());
        assertEquals("-1/1", (new FractionImpl("-1/1")).toString());
        assertEquals("-2/3", (new FractionImpl("8/-12")).toString());
    }

    @Test
    public void testInvalidFractions() {
        /*
        1/0, 1/-1

        Fraction fraction = new FractionImpl(1,1);
        assertEquals("1/1", fraction.toString());

        fraction = new FractionImpl(1);
        assertEquals("1/1", fraction.toString());

        fraction = new FractionImpl("1/1");
        assertEquals("1/1", fraction.toString());
        */
    }

    @Test
    public void testAdd() {
        // Tests that are expected to succeed
    }

    @Test(expected = ArithmeticException.class)
    public void testDivideByZero() {
        // test that should throw an ArithmeticException
    }

}
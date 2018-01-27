package fraction;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import java.util.concurrent.Callable;

import static org.junit.Assert.*;


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

    /**
     * Implement my own assertion because ExpectedException rules are very limited with multiple calls within a
     * single test.
     */
    private void assertThrown(Callable<Fraction> assertable, Class<? extends Throwable> expected) {
        try {
            assertable.call();
            fail("Expected exception: " + expected);
        } catch (Throwable caught) {
            assertThat(caught, CoreMatchers.isA((Class<Throwable>) expected));
        }
    }

    @Test
    public void testInvalidFractions() {
        assertThrown(() -> new FractionImpl(1, 0), ArithmeticException.class);

        assertThrown(() -> new FractionImpl("1/0"), ArithmeticException.class);
        assertThrown(() -> new FractionImpl("1/1 1"), NumberFormatException.class);
        assertThrown(() -> new FractionImpl("1/1/1"), NumberFormatException.class);
    }

    @Test
    public void testAdd() {
        String[][] rows = {
                {"0", "-1", "-1/1"},
                {"0", "0", "0/1"},
                {"0", "1", "1/1"},

                {"1", "-1", "0/1"},
                {"1", "0", "1/1"},
                {"1", "1", "2/1"},

                {"1/2", "1/2", "1/1"},
                {"1/4", "1/4", "1/2"},

                {"23/11", "12/5", "247/55"},
                {"-23/11", "12/5", "17/55"},
                {"23/11", "12/-5", "-17/55"},
        };

        for (String[] row: rows) {
            assertEquals(row[2], new FractionImpl(row[0]).add(new FractionImpl(row[1])).toString());
        }
    }

    @Test
    public void testSubtract() {
        String[][] rows = {
                {"0", "-1", "1/1"},
                {"0", "0", "0/1"},
                {"0", "1", "-1/1"},

                {"1", "-1", "2/1"},
                {"1", "0", "1/1"},
                {"1", "1", "0/1"},

                {"1/2", "1/2", "0/1"},

                {"23/11", "12/5", "-17/55"},
                {"-23/11", "12/5", "-247/55"},
                {"23/11", "12/-5", "247/55"},
        };

        for (String[] row: rows) {
            assertEquals(row[2], new FractionImpl(row[0]).subtract(new FractionImpl(row[1])).toString());
        }
    }

    @Test
    public void testMultiply() {
        String[][] rows = {
                {"0", "-1", "0/1"},
                {"0", "0", "0/1"},
                {"0", "1", "0/1"},

                {"1", "-1", "-1/1"},
                {"1", "0", "0/1"},
                {"1", "1", "1/1"},

                {"1/2", "1/2", "1/4"},
                {"1/4", "1/4", "1/16"},

                {"23/11", "12/5", "276/55"},
                {"-23/11", "12/5", "-276/55"},
                {"23/11", "12/-5", "-276/55"},
        };

        for (String[] row: rows) {
            assertEquals(row[2], new FractionImpl(row[0]).multiply(new FractionImpl(row[1])).toString());
        }
    }

    @Test
    public void testDivide() {
        String[][] rows = {
                {"0", "-1", "0/1"},
                {"0", "1", "0/1"},

                {"1", "-1", "-1/1"},
                {"1", "1", "1/1"},

                {"1/2", "1/2", "1/1"},

                {"23/11", "12/5", "115/132"},
                {"-23/11", "12/5", "-115/132"},
                {"23/11", "12/-5", "-115/132"},
        };

        for (String[] row: rows) {
            assertEquals(row[2], new FractionImpl(row[0]).divide(new FractionImpl(row[1])).toString());
        }
    }

    @Test
    public void testDivideByZero() {
        assertThrown(() -> (new FractionImpl(1)).divide(new FractionImpl(0)), ArithmeticException.class);
    }


    @Test
    public void testNegate() {
        assertEquals("1/1", (new FractionImpl("-1")).negate().toString());
        assertEquals("0/1", (new FractionImpl("0")).negate().toString());
        assertEquals("-1/1", (new FractionImpl("1")).negate().toString());
    }

    @Test
    public void testInverse() {
        assertEquals("-1/1", (new FractionImpl("-1")).inverse().toString());
        assertEquals("0/1", (new FractionImpl("0")).inverse().toString());
        assertEquals("1/1", (new FractionImpl("1")).inverse().toString());
        assertEquals("4/1", (new FractionImpl("1/4")).inverse().toString());
    }

    @Test
    public void testInverseZero() {
        assertThrown(() -> (new FractionImpl(0)).inverse(), ArithmeticException.class);
    }

}
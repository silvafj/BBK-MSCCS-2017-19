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

}
package fraction;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import java.util.concurrent.Callable;

import static org.junit.Assert.*;


public class FractionTest {

    @Test
    public void validFractions() {
        assertEquals(new FractionImpl("1/1"), new FractionImpl(1));
        assertEquals(new FractionImpl("0/1"), new FractionImpl(0));
        assertEquals(new FractionImpl("-1/1"), new FractionImpl(-1));
        assertEquals(new FractionImpl("-1/-1"), new FractionImpl(1));

        assertEquals(new FractionImpl("1/1"), new FractionImpl(1, 1));
        assertEquals(new FractionImpl("0/1"), new FractionImpl(0, 1));
        assertEquals(new FractionImpl("-1/1"), new FractionImpl(-1, 1));
        assertEquals(new FractionImpl("-2/3"), new FractionImpl(8, -12));

        assertEquals(new FractionImpl("1/1"), new FractionImpl(" 1 /1 "));
        assertEquals(new FractionImpl("0/1"), new FractionImpl("0/1"));
        assertEquals(new FractionImpl("0/1"), new FractionImpl("0"));
        assertEquals(new FractionImpl("-2/3"), new FractionImpl("8/-12"));
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
    public void invalidFractions() {
        assertThrown(() -> new FractionImpl(1, 0), ArithmeticException.class);

        assertThrown(() -> new FractionImpl("1/0"), ArithmeticException.class);
        assertThrown(() -> new FractionImpl("1/1 1"), NumberFormatException.class);
        assertThrown(() -> new FractionImpl("1/1/1"), NumberFormatException.class);
    }

    @Test
    public void fractionToString() {
        assertEquals("-1", (new FractionImpl(-1)).toString());
        assertEquals("0", (new FractionImpl(0)).toString());
        assertEquals("1", (new FractionImpl(1)).toString());
        assertEquals("1/2", (new FractionImpl(1, 2)).toString());
    }

    @Test
    public void add() {
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

        for (String[] row : rows) {
            assertEquals(new FractionImpl(row[2]), new FractionImpl(row[0]).add(new FractionImpl(row[1])));
        }
    }

    @Test
    public void subtract() {
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

        for (String[] row : rows) {
            assertEquals(new FractionImpl(row[2]), new FractionImpl(row[0]).subtract(new FractionImpl(row[1])));
        }
    }

    @Test
    public void multiply() {
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

        for (String[] row : rows) {
            assertEquals(new FractionImpl(row[2]), new FractionImpl(row[0]).multiply(new FractionImpl(row[1])));
        }
    }

    @Test
    public void divide() {
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

        for (String[] row : rows) {
            assertEquals(new FractionImpl(row[2]), new FractionImpl(row[0]).divide(new FractionImpl(row[1])));
        }
    }

    @Test
    public void divideByZero() {
        assertThrown(() -> (new FractionImpl(1)).divide(new FractionImpl(0)), ArithmeticException.class);
    }


    @Test
    public void abs() {
        assertEquals(new FractionImpl(1), (new FractionImpl(-1)).abs());
        assertEquals(new FractionImpl(0), (new FractionImpl(0)).abs());
        assertEquals(new FractionImpl("3/2"), (new FractionImpl("-3/2")).abs());
    }

    @Test
    public void negate() {
        assertEquals(new FractionImpl("1/1"), (new FractionImpl("-1")).negate());
        assertEquals(new FractionImpl("0/1"), (new FractionImpl("0")).negate());
        assertEquals(new FractionImpl("-1/1"), (new FractionImpl("1")).negate());
    }

    @Test
    public void inverse() {
        assertEquals(new FractionImpl("-1"), (new FractionImpl("-1")).inverse());
        assertEquals(new FractionImpl("4"), (new FractionImpl("1/4")).inverse());
    }

    @Test
    public void inverseZero() {
        assertThrown(() -> (new FractionImpl(0)).inverse(), ArithmeticException.class);
    }

    @Test
    public void hashcode() {
        assertEquals((new FractionImpl("-1")).hashCode(), (new FractionImpl("-1/1")).hashCode());
        assertEquals((new FractionImpl("1")).hashCode(), (new FractionImpl("1/1")).hashCode());
    }

    @Test
    public void equals() {
        assertTrue((new FractionImpl("-1")).equals(new FractionImpl("-1/1")));
        assertTrue((new FractionImpl("1")).equals(new FractionImpl("1/1")));
        assertFalse((new FractionImpl("1")).equals(new FractionImpl("2")));
    }

    @Test
    public void compareTo() {
        assertEquals(-1, (new FractionImpl("1/2")).compareTo(new FractionImpl(1)));
        assertEquals(0, (new FractionImpl("10/6")).compareTo(new FractionImpl("5/3")));
        assertEquals(1, (new FractionImpl(1)).compareTo(new FractionImpl("1/2")));
    }

}
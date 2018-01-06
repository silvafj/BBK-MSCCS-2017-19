import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class EvenOddCounterTest {

    @Test
    public void test_isEven_even() {

        assertTrue(EvenOddCounter.isEven(10));
    }

    @Test
    public void test_isEven_odd() {

        assertFalse(EvenOddCounter.isEven(9));
    }
}
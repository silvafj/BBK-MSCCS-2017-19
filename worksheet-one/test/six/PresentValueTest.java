package six;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PresentValueTest {

    @Test
    public void test_calculatePresentValue() {

        double value = PresentValue.calculatePresentValue(1000, .10, 10);

        assertEquals(385.5432894295314, value, 0);
    }
}
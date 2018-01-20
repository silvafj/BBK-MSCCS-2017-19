import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class BoilingPointsTest {
    @Parameterized.Parameters
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {Element.Type.ETHYL, 173, true},
                {Element.Type.ETHYL, 172, true},
                {Element.Type.ETHYL, 171, false},
                {Element.Type.OXYGEN, -305, true},
                {Element.Type.OXYGEN, -306, true},
                {Element.Type.OXYGEN, -307, false},
                {Element.Type.WATER, 213, true},
                {Element.Type.WATER, 212, true},
                {Element.Type.WATER, 211, false},
        });
    }

    @Parameterized.Parameter(0)
    public Element.Type element;

    @Parameterized.Parameter(1)
    public double temperature;

    @Parameterized.Parameter(2)
    public boolean expected;

    @Test
    public void test() {
        boolean isBoiling = (new FreezingAndBoilingPoints(temperature)).isBoiling(element);
        if (expected) {
            assertTrue(isBoiling);
        } else {
            assertFalse(isBoiling);
        }
    }
}
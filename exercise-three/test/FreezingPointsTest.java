import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class FreezingPointsTest {
    @Parameterized.Parameters
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {Element.Type.ETHYL, -174, true},
                {Element.Type.ETHYL, -173, true},
                {Element.Type.ETHYL, -172, false},
                {Element.Type.OXYGEN, -363, true},
                {Element.Type.OXYGEN, -362, true},
                {Element.Type.OXYGEN, -361, false},
                {Element.Type.WATER, 31, true},
                {Element.Type.WATER, 32, true},
                {Element.Type.WATER, 33, false},
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
        boolean isFreezing = (new FreezingAndBoilingPoints(temperature)).isFreezing(element);
        if (expected) {
            assertTrue(isFreezing);
        } else {
            assertFalse(isFreezing);
        }
    }
}
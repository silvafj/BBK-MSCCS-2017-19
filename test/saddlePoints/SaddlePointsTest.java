package saddlePoints;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import saddlePoints.SaddlePoints;

/**
 * @author Fernando Silva <fdealm02>
 */
public class SaddlePointsTest {

    private static final int[] EMPTY_ARRAY = {};

    SaddlePoints sp = new SaddlePoints(); // create an instance variable

    // If you use the same variables in multiple tests,
    //  declare them here

    @Before
    public void setUp() throws Exception {
        // If you use the same variables in multiple tests,
        //  assign values to them here
    }

    @Test
    public void testLargest() {
        assertEquals(Integer.MIN_VALUE, sp.largest(EMPTY_ARRAY));

        int[] a1 = {-1, 0, 1};
        assertEquals(1, sp.largest(a1));

        int[] a2 = {-1, 3, 3};
        assertEquals(3, sp.largest(a2));

        int[] a3 = {-5, -3, -1};
        assertEquals(-1, sp.largest(a3));
    }

}
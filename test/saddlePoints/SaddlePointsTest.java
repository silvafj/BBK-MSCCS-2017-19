package saddlePoints;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * @author Fernando Silva <fdealm02>
 */
public class SaddlePointsTest {

    private static final int[] EMPTY_ARRAY = {};
    private static final int[][] EMPTY_ARRAY_2D = {};

    private SaddlePoints sp = new SaddlePoints(); // create an instance variable

    // If you use the same variables in multiple tests,
    // declare them here

    /*
    @Before
    public void setUp() throws Exception {
        // If you use the same variables in multiple tests,
        //  assign values to them here
    }
    */

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

    @Test
    public void testSmallest() {
        assertEquals(Integer.MAX_VALUE, sp.smallest(EMPTY_ARRAY));

        int[] a1 = {-1, 0, 1};
        assertEquals(-1, sp.smallest(a1));

        int[] a2 = {-1, -1, 3};
        assertEquals(-1, sp.smallest(a2));

        int[] a3 = {-5, -3, -1};
        assertEquals(-5, sp.smallest(a3));
    }

    @Test
    public void testLargestValues() {
        assertArrayEquals(EMPTY_ARRAY, sp.largestValues(EMPTY_ARRAY_2D));

        int[][] a1 = {{-1, 0, 1}, {5, 4, 9}, {-5, 10, -2}};
        assertArrayEquals(new int[]{5, 10, 9}, sp.largestValues(a1));

        int[][] a2 = {{-1, -1, 3}, {3, -10, 5}, {-1, -1, 6}};
        assertArrayEquals(new int[]{3, -1, 6}, sp.largestValues(a2));

        int[][] a3 = {{-5, -3, -1}, {0, 0, 0}, {5, 3, -1}};
        assertArrayEquals(new int[]{5, 3, 0}, sp.largestValues(a3));
    }

    @Test
    public void testSmallestValues() {
        assertArrayEquals(EMPTY_ARRAY, sp.smallestValues(EMPTY_ARRAY_2D));

        int[][] a1 = {{-1, 0, 1}, {5, 4, 9}, {-5, 10, -2}};
        assertArrayEquals(new int[]{-1, 4, -5}, sp.smallestValues(a1));

        int[][] a2 = {{-1, -1, 3}, {3, -10, 5}, {-1, -1, 6}};
        assertArrayEquals(new int[]{-1, -10, -1}, sp.smallestValues(a2));

        int[][] a3 = {{-5, -3, -1}, {0, 0, 0}, {5, 3, -1}};
        assertArrayEquals(new int[]{-5, 0, -1}, sp.smallestValues(a3));
    }
}
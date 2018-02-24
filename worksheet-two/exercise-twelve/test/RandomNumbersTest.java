import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class RandomNumbersTest {

    @Test
    public void nextInt() {
        assertEquals(0, RandomNumbers.nextInt(0, 0));
        assertEquals(1, RandomNumbers.nextInt(1, 1));
    }

    @Test
    public void randomFromArray() {
        assertEquals(0, RandomNumbers.randomElement(new int[]{}));
        assertEquals(1, RandomNumbers.randomElement(new int[]{1}));
    }

    @Test
    public void randomFromArrayList() {
        assertEquals(0, RandomNumbers.randomElement(new ArrayList<>()));
        assertEquals(1, RandomNumbers.randomElement(new ArrayList<>(Arrays.asList(1))));
    }
}
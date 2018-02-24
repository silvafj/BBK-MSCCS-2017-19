package one;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class AverageRainfallTest {
    private List<Double> listToSum;

    @Before
    public void setup() {
        listToSum = new ArrayList<>();
    }

    @Test
    public void calculateTotalRainFall() {
        listToSum.add(10.0);
        listToSum.add(10.0);
        listToSum.add(10.0);
        listToSum.add(10.0);
        listToSum.add(10.0);

        Double sum = AverageRainfall.calculateTotalRainFall(listToSum);

        assertEquals(50, sum, 0);

    }

    @Test
    public void calculateAverageRainFall() {
        listToSum.add(10.0);
        listToSum.add(10.0);
        listToSum.add(10.0);
        listToSum.add(10.0);
        listToSum.add(10.0);

        Double sum = AverageRainfall.calculateAverageRainFall(listToSum);

        assertEquals(10, sum, 0);
    }
}
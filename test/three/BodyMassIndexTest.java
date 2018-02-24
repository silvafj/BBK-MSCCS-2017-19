package three;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BodyMassIndexTest {

    @Test
    public void calculate_bmi() {
        assertTrue(BodyMassIndex.calculateBMI(73, 250) == 32.9799211859636);
    }

    @Test
    public void bmiDescription_underweight() {
        assertEquals("You are underweight.", BodyMassIndex.bmiDescription(16));
    }

    @Test
    public void bmiDescription_overweight() {
        assertEquals("You are overweight.", BodyMassIndex.bmiDescription(78));
    }

    @Test
    public void bmiDescription_optimal() {
        assertEquals("Your weight is optimal.", BodyMassIndex.bmiDescription(20));
    }
}
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CarTest {

    @Test(expected = IllegalArgumentException.class)
    public void efficiencyNegative() {
        new Car(0);
    }

    @Test
    public void drive() {
        Car car = new Car(1);
        assertEquals(0, car.getDistance(), 0);

        car.fillUp(10);
        car.drive(10);

        assertEquals(10, car.getDistance(), 0);
        assertEquals(0, car.getFuel(), 0);

        car.fillUp(10);
        car.drive(50);

        assertEquals(20, car.getDistance(), 0);
        assertEquals(0, car.getFuel(), 0);
    }

    @Test
    public void fill() {
        Car car = new Car(1);
        assertEquals(0, car.getFuel(), 0);

        car.fillUp(10);
        assertEquals(10, car.getFuel(), 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void fillNegative() {
        Car car = new Car(1);
        car.fillUp(-1);
    }
}

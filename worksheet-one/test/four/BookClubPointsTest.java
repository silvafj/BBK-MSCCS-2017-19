package four;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BookClubPointsTest {

    @Test
    public void test_getPointsEarned_0() {

        int points = BookClubPoints.getPointsEarned(0);
        assertEquals(0, points);
    }

    @Test
    public void test_getPointsEarned_1() {

        int points = BookClubPoints.getPointsEarned(1);
        assertEquals(5, points);
    }

    @Test
    public void test_getPointsEarned_2() {

        int points = BookClubPoints.getPointsEarned(2);
        assertEquals(15, points);
    }

    @Test
    public void test_getPointsEarned_3() {

        int points = BookClubPoints.getPointsEarned(3);
        assertEquals(30, points);
    }

    @Test
    public void test_getPointsEarned_4() {

        int points = BookClubPoints.getPointsEarned(4);
        assertEquals(60, points);
    }
}
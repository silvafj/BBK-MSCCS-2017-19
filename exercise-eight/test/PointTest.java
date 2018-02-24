import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PointTest {

    @Test
    public void newPoint() {
        Point p = new Point();
        assertEquals(0, p.getX(), 0);
        assertEquals(0, p.getY(), 0);

        p = new Point(1, 1);
        assertEquals(1, p.getX(), 0);
        assertEquals(1, p.getY(), 0);
    }

    @Test
    public void translatePoint() {
        Point p = new Point(3, 4);
        p.translate(5, 1.5);

        assertEquals(8, p.getX(), 0);
        assertEquals(5.5, p.getY(), 0);
    }

    @Test
    public void scalePoint() {
        Point p = new Point(3, 4);
        p.scale(0.5);

        assertEquals(1.5, p.getX(), 0);
        assertEquals(2, p.getY(), 0);
    }
}
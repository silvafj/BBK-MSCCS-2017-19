/**
 * A point describing a location in a plane, specified in double precision.
 */
public class Point {

    private double x;
    private double y;

    /**
     * Constructs a point at the origin (0, 0) of the plane.
     */
    public Point() {
        this.x = this.y = 0;
    }

    /**
     * Constructs a point at the specified (x, y) coordinates in the plane.
     *
     * @param x the X coordinate of the constructed Point.
     * @param y the Y coordinate of the constructed Point.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the X coordinate of this Point, specified in double precision.
     *
     * @return the X coordinate of this Point.
     */
    public double getX() {
        return x;
    }

    /**
     * Returns the Y coordinate of this Point, specified in double precision.
     *
     * @return the Y coordinate of this Point.
     */
    public double getY() {
        return y;
    }

    /**
     * Translates this point, moving it by dx along the X axis and dy along the Y axis, so that it now describes the
     * point (x + dx, y + dy).
     *
     * @param dx the distance to move this point along the X axis.
     * @param dy the distance to move this point along the Y axis.
     * @return a copy of this point, after the translation.
     */
    public Point translate(double dx, double dy) {
        return new Point(this.getX() + dx, this.getY() + dy);
    }

    /**
     * Scales this point, by a given factor, so that it now describes the point (x * factor, y * factor).
     *
     * @param factor the factor to apply this point along the X and Y axis.
     * @return a copy of this point, after the scaling.
     */
    public Point scale(double factor) {
        return new Point(this.getX() * factor, this.getY() * factor);
    }

}
public class Point {

    private double x;
    private double y;

    public Point() {
        this.x = this.y = 0;
    }

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Point translate(double x, double y) {
        return new Point(this.getX() + x, this.getY() + y);
    }

    public Point scale(double factor) {
        return new Point(this.getX() * factor, this.getY() * factor);
    }

}
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

    public void translate(double x, double y) {
        this.x = this.getX() + x;
        this.y = this.getY() + y;
    }

    public void scale(double factor) {
        this.x = this.getX() * factor;
        this.y = this.getY() * factor;
    }

}
package student;

/**
 * A route finder exposes a minimal set of functions.
 */
public interface RouteFinder {
    /**
     * Returns True if the target was reached.
     *
     * @return True if the target was reached.
     */
    boolean found();

    /**
     * Instructs the finder to move to the next location.
     */
    void move();
}

package game;

import java.util.Objects;

/**
 * A Pair (X,Y) represents an immutable ordered pair of two Objects of types X and Y respectively.
 */
public final class Pair<X, Y> {
    private X first;
    private Y second;

    /**
     * Constructor: an instance (x, y).
     */
    public Pair(X x, Y y) {
        first = x;
        second = y;
    }

    /**
     * Return the first object in this Pair.
     */
    public X getFirst() {
        return first;
    }

    /**
     * Return the second object in this Pair.
     */
    public Y getSecond() {
        return second;
    }

    /**
     * Return true iff ob is an instance of Pair with the same values.
     */
    @Override
    public boolean equals(Object ob) {
        if (!(ob instanceof Pair<?, ?>)) {
            return false;
        }
        Pair<?, ?> p = (Pair<?, ?>) ob;
        return first.equals(p.first) && second.equals(p.second);
    }

    /**
     * Return a hash code based on both values in this obbject.
     */
    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }

}

package game;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * An instance represents a node in the graph --the cavern.
 */
public class NodeImpl implements Node {
    /**
     * The least positive fresh identifier available
     */
    private static long FRESH_ID = 0;

    /**
     * The unique numerical identifier of this Node
     */
    private final long id;
    /**
     * Represents the edges outgoing from this Node
     */
    private final Set<Edge> edges;
    private final Set<Node> neighbors;

    private final Set<Edge> unmodifiableEdges;
    private final Set<Node> unmodifiableNeighbors;

    /**
     * Extra state that belongs to this node
     */
    private final Tile tile;

    /* package */ NodeImpl(Tile t) {
        this(FRESH_ID, t);
    }

    /**
     * Constructor: an instance with id givenId and tile t
     */
    /* package */ NodeImpl(long givenId, Tile t) {
        id = givenId;
        FRESH_ID = Math.max(FRESH_ID, givenId + 1);
        edges = new HashSet<>();
        neighbors = new HashSet<>();

        unmodifiableEdges = Collections.unmodifiableSet(edges);
        unmodifiableNeighbors = Collections.unmodifiableSet(neighbors);

        tile = t;
    }

    /* package */
    @Override
    public void addEdge(Edge e) {
        edges.add(e);
        neighbors.add(e.getOther(this));
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public Edge getEdge(Node q) {
        for (Edge e : edges) {
            if (e.getDestination().equals(q)) {
                return e;
            }
        }
        throw new IllegalArgumentException("getEdge: Node must be a neighbor of this Node");
    }

    @Override
    public Set<Edge> getExits() {
        return unmodifiableEdges;
    }

    @Override
    public Set<Node> getNeighbors() {
        return unmodifiableNeighbors;
    }

    @Override
    public Tile getTile() {
        return tile;
    }

    /**
     * Return truee if ob is a Node with the same id as this one.
     */
    @Override
    public boolean equals(Object ob) {
        if (ob == this) {
            return true;
        }
        if (!(ob instanceof NodeImpl)) {
            return false;
        }
        return id == ((NodeImpl) ob).id;
    }

    /**
     * Return a hash code for this node, based solely on its id.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

package game;

import java.util.Map;

/**
 * An Edge represents an immutable directed, weighted edge.
 */
public class Edge {

    /**
     * The length of this edge
     */
    public final int length;
    /**
     * The Node this edge is coming from
     */
    private final Node source;
    /**
     * The node this edge is going to
     */
    private final Node destination;

    /**
     * Constructor: an edge of length len from source to destination.
     */
    public Edge(Node source, Node destination, int len) {
        this.source = source;
        this.destination = destination;
        this.length = len;
    }

    /**
     * Constructor: an edge like e ...
     */
    public Edge(Edge e, Map<Node, Node> isomorphism) {
        source = isomorphism.get(e.source);
        destination = isomorphism.get(e.destination);
        length = e.length;
    }

    /**
     * Return the <tt>Node</tt> on this <tt>Edge</tt> that is not equal to n.
     * Throw an <tt>IllegalArgumentException</tt> if <tt>n</tt> is not in this <tt>Edge</tt>.
     */
    public Node getOther(Node n) {
        if (source == n) {
            return destination;
        }
        if (destination == n) {
            return source;
        }
        throw new IllegalArgumentException("getOther: Edge must contain provided node");
    }

    /**
     * Return the length of this <tt>Edge</tt>
     */
    public int length() {
        return length;
    }

    /**
     * Return the source of this edge.
     */
    public Node getSource() {
        return source;
    }

    /**
     * Return the destination of this edge.
     */
    public Node getDestination() {
        return destination;
    }
}

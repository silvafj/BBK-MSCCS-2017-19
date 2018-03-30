package game;

import java.util.Set;

public interface Node {
    /**
     * Add edge e to the graph.
     */
    /* package */  void addEdge(Edge e);

    /**
     * Return the unique Identifier of this Node.
     */
    long getId();

    /**
     * Returns the Edge of this Node that connects to q.
     * .     * Throw an IllegalArgumentException if there is no such edge.
     */
    Edge getEdge(Node q);

    /**
     * Return an unmodifiable set of Edges leaving this Node.
     */
    Set<Edge> getExits();

    /**
     * Return an unmodifiable set Nodes neighboring this Node.
     */
    Set<Node> getNeighbors();

    /**
     * Return the Tile corresponding to this Node.
     */
    Tile getTile();
}

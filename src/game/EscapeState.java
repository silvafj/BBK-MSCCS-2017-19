package game;

import java.util.Collection;

/**
 * The state of the game while escaping from the cavern.
 * This interface provides access to a complete graph of the cavern,
 * which will allow you to compute your path out.
 * Once you have determined how you want to escape, you can call
 * moveTo(Node) to move to each node, and pickUpGold() to collect
 * gold on your way out.
 * <p>
 * An EscapeState provides all of the information necessary to
 * escape from the cavern and collect gold on the way.
 */
public interface EscapeState {
    /**
     * Return your current location in the graph.
     */
    public Node getCurrentNode();

    /**
     * Return the exit from the cavern.
     * This is the node you need to move to in order to escape.
     */
    public Node getExit();

    /**
     * Return all the nodes in the graph, in no particular order.
     */
    public Collection<Node> getVertices();

    /**
     * Change your current location n.
     * Throw an IllegalArgumentException if n is not a neihgbor of your current location.
     */
    public void moveTo(Node n);

    /**
     * Picks up any gold on the current tile.
     * You must first check that there is gold before picking it up.
     * <p>
     * Throw an IllegalStateException if there is no gold at the current location,
     * either because there never was any or because you picked it up already.
     */
    public void pickUpGold();

    /**
     * Return the time remaining to escape from the cavern.
     * This value changes with every call to moveTo(Node),
     * and if it reaches 0 before you escape, you have failed to escape.
     */
    public int getTimeRemaining();
}

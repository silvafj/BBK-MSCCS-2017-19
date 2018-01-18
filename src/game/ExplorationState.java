package game;

import java.util.Collection;

/**
 * The state of the game while performing exploration.
 * In order to determine your next move you will need to call the various
 * methods of this interface. To move through the cavern, you will need to call moveTo(long).
 * <p>
 * An ExplorationState provides all the information necessary
 * to search through the cavern and find the target.
 */
public interface ExplorationState {
    /**
     * Return the unique identifier associated with your current location.
     */
    long getCurrentLocation();

    /**
     * Returns an unordered collection of NodeStatus objects
     * associated with all direct neighbours of your current location.
     * Each status contains a unique identifier for the neighbouring node
     * as well as the distance of that node to the target along the grid
     * (NB: This is NOT the distance in the graph, it is only the number
     * of rows and columns away from the target.)
     * It is possible to move directly to any node identifier in this collection.
     */
    Collection<NodeStatus> getNeighbours();

    /**
     * Return your current distance along the grid (NOT THE GRAPH) from the target.
     */
    int getDistanceToTarget();

    /**
     * Change your current location to the node specified by id.
     * You can move only to nodes adjacent to your current node.
     * Throw an IllegalArgumentException if id is not a neihgbor of your current location.
     */
    void moveTo(long id);
}

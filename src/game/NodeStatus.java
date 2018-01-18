package game;

import java.util.Objects;

/**
 * An instance maintains the status of a node -- it's id and its distance from the Orb.
 */
public class NodeStatus implements Comparable<NodeStatus> {
    private final long id;
    private final int distance;

    /**
     * Constructor: an instnce with id nodeId and distance dist.
     */
    /* package */ NodeStatus(long nodeId, int dist) {
        id = nodeId;
        distance = dist;
    }

    /**
     * Return the Id of the Node that corresponds to this NodeStatus.
     */
    public long getId() {
        return id;
    }

    /**
     * Return the distance to the orb from the Node that corresponds to
     * this NodeStatus.
     */
    public int getDistanceToTarget() {
        return distance;
    }

    /**
     * Return a negative number, 0, or a positive number depending on
     * whether this is closer to, at the same ditance, or farther from the Orb.
     */
    @Override
    public int compareTo(NodeStatus other) {
        return Integer.compare(distance, other.distance);
    }

    /**
     * Return true if ob is an instance of NodeStatus and has the same id as this one.
     */
    @Override
    public boolean equals(Object ob) {
        if (ob == this) {
            return true;
        }
        if (!(ob instanceof NodeStatus)) {
            return false;
        }
        return id == ((NodeStatus) ob).id;
    }

    /**
     * Return a hashcode for this, based solely on its id.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

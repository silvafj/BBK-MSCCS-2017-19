package student;

import game.ExplorationState;
import game.NodeStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Stack;

/**
 * Cavern exploration and Orb finder based on depth-first search algorithm.
 *
 * @author Fernando Silva
 */
public class OrbFinder {

    private ExplorationState state;

    /**
     * Keep a list of visited nodes, to avoid visiting the same node twice
     */
    private List<NodeStatus> visited = new ArrayList<>();

    /**
     * Keep the current path in the stack, to be able to go back when we find a dead end
     */
    private Stack<NodeStatus> currentPath = new Stack<>();

    /**
     * Constructor.
     *
     * @param state exploration state.
     */
    OrbFinder(ExplorationState state) {
        this.state = state;
    }

    /**
     * Calculates the next node in the path to the Orb. It can be:
     * 1. the unvisited neighbour closest to the orb
     * 2. a previous visited node (when going back from a dead end)
     *
     * @return next node to move to.
     */
    private NodeStatus getNode() {
        // The best unvisited neighbour:
        // 1. Exclude the nodes that were visited
        // 2. Sort the nodes by how close they are to the target
        Optional<NodeStatus> nextNode = state.getNeighbours().stream()
                .filter(nodeStatus -> !(visited.contains(nodeStatus)))
                .sorted(NodeStatus::compareTo)
                .findFirst();

        if (nextNode.isPresent()) {
            return nextNode.get();
        } else {
            currentPath.pop();
            return currentPath.pop();
        }
    }

    /**
     * Returns True if the target was reached.
     *
     * @return True if the target was reached.
     */
    private boolean found() {
        return state.getDistanceToTarget() == 0;
    }

    /**
     * Instructs the finder to move to the next location.
     */
    private void move() {
        NodeStatus nextNode = this.getNode();
        state.moveTo(nextNode.getId());
        visited.add(nextNode);
        currentPath.add(nextNode);
    }

    public void find() {
        while (!this.found()) {
            this.move();
        }
    }
}

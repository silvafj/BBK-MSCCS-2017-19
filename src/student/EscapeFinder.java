package student;

import game.Edge;
import game.EscapeState;
import game.Node;

import java.util.*;


/**
 * Escape the cavern based on Dijkstra's algorithm.
 *
 * @author Fernando Silva
 */
public class EscapeFinder {

    private EscapeState state;

    /**
     * Shortest route to the exit.
     */
    private Stack<Node> route;

    /**
     * Constructor.
     *
     * @param state escape state.
     */
    EscapeFinder(EscapeState state) {
        this.state = state;
        route = getShortestRoute(state.getCurrentNode(), state.getExit());
    }

    /**
     * Calculates the shortest path between start and end nodes.
     * <p>
     * This code is based on Cavern.minPathLengthToTarget() which is used to calculate the time limit for the escape
     * stage of the game.
     *
     * @param start initial node in the path.
     * @param end   final node in the path.
     * @return stack of nodes with the shortest path between start and end
     */
    private Stack<Node> getShortestRoute(Node start, Node end) {
        Map<Node, NodeAndWeightTuple> pathWeights = new HashMap<>();
        pathWeights.put(start, new NodeAndWeightTuple(null, 0));

        PriorityQueue<NodeAndWeightTuple> frontier = new PriorityQueue<>(1, Comparator.comparingInt(n -> n.weight));
        frontier.add(new NodeAndWeightTuple(start, 0));

        while (!frontier.isEmpty()) {
            Node node = frontier.poll().node;
            if (node.equals(end)) {
                break;
            }

            int nWeight = pathWeights.get(node).weight;

            for (Edge edge : node.getExits()) {
                Node edgeNode = edge.getOther(node);
                NodeAndWeightTuple existingTuple = pathWeights.get(edgeNode);

                int weightThroughN = nWeight + edge.length();

                if (existingTuple == null) {
                    pathWeights.put(edgeNode, new NodeAndWeightTuple(node, weightThroughN));
                    frontier.add(new NodeAndWeightTuple(edgeNode, weightThroughN));
                } else if (weightThroughN < existingTuple.weight) {
                    pathWeights.put(edgeNode, new NodeAndWeightTuple(node, weightThroughN));

                    // Change the weight of an existent node, by removing it first
                    for (NodeAndWeightTuple nodeAndWeight : frontier) {
                        if (edgeNode.equals(nodeAndWeight.node)) {
                            frontier.remove(nodeAndWeight);
                            break;
                        }
                    }
                    frontier.add(new NodeAndWeightTuple(edgeNode, weightThroughN));
                }
            }
        }

        // Generate the stack of nodes representing the route (begins at the exit node)
        Stack<Node> path = new Stack<>();
        Node node = end;
        while (node != null) {
            path.push(node);
            node = pathWeights.get(node).node;
        }

        return path;
    }

    /**
     * Calculates the next node in the path to the exit.
     *
     * @return next node to move to.
     */
    private Node getNode() {
        return route.pop();
    }

    /**
     * Returns True if the target was reached.
     *
     * @return True if the target was reached.
     */
    private boolean found() {
        return route.empty();
    }

    /**
     * Instructs the finder to move to the next location.
     */
    private void move() {
        Node nextNode = this.getNode();
        if (!state.getCurrentNode().equals(nextNode)) {
            state.moveTo(nextNode);
        }
    }

    public void find() {
        while (!this.found()) {
            if (state.getCurrentNode().getTile().getGold() > 0) {
                state.pickUpGold();
            }

            this.move();
        }
    }

    /**
     * Tuple structure of Node and Weight to be used internally for shortest route calculation.
     */
    private class NodeAndWeightTuple {
        public Node node;
        public int weight;

        NodeAndWeightTuple(Node node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }
}

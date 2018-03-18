package student;

import game.Node;

/**
 * Tuple structure of Node and Weight to be used internally for shortest route calculation.
 */
public class NodeAndWeightTuple {
    public Node node;
    public int weight;

    NodeAndWeightTuple(Node node, int weight) {
        this.node = node;
        this.weight = weight;
    }
}

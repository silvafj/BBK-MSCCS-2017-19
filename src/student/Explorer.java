package student;

import game.*;

import java.util.*;

public class Explorer {

    /**
     * Explore the cavern, trying to find the orb in as few steps as possible.
     * Once you find the orb, you must return from the function in order to pick
     * it up. If you continue to move after finding the orb rather
     * than returning, it will not count.
     * If you return from this function while not standing on top of the orb,
     * it will count as a failure.
     * <p>
     * There is no limit to how many steps you can take, but you will receive
     * a score bonus multiplier for finding the orb in fewer steps.
     * <p>
     * At every step, you only know your current tile's ID and the ID of all
     * open neighbor tiles, as well as the distance to the orb at each of these tiles
     * (ignoring walls and obstacles).
     * <p>
     * To get information about the current state, use functions
     * getCurrentLocation(),
     * getNeighbours(), and
     * getDistanceToTarget()
     * in ExplorationState.
     * You know you are standing on the orb when getDistanceToTarget() is 0.
     * <p>
     * Use function moveTo(long id) in ExplorationState to move to a neighboring
     * tile by its ID. Doing this will change state to reflect your new position.
     * <p>
     * A suggested first implementation that will always find the orb, but likely won't
     * receive a large bonus multiplier, is a depth-first search.
     *
     * @param state the information available at the current state
     */
    public void explore(ExplorationState state) {
        // Keep a list of visited nodes, to avoid visiting the same node twice
        List<NodeStatus> visited = new ArrayList<>();

        // Keep the current path in the stack, to be able to go back when we find a dead end
        Stack<NodeStatus> currentPath = new Stack<>();

        while (state.getDistanceToTarget() > 0) {
            // Generate a list with the next possible moves, excluding the nodes that were visited.
            List<NodeStatus> toVisit = new ArrayList<>();
            for (NodeStatus node : state.getNeighbours()) {
                if (!visited.contains(node)) {
                    toVisit.add(node);
                }
            }

            NodeStatus closerNode = null;
            if (!toVisit.isEmpty()) {
                // Sort the nodes we can visit by how close they are to the target
                toVisit.sort(NodeStatus::compareTo);

                // Pick the next possible move
                for (NodeStatus node : toVisit) {
                    closerNode = node;
                    visited.add(node);
                    currentPath.add(node);
                    break;
                }
            } else {
                // If there are no more possible moves, we must go back on our previous path
                currentPath.pop();
                closerNode = currentPath.peek();
            }

            state.moveTo(closerNode.getId());
        }
    }

    /**
     * Escape from the cavern before the ceiling collapses, trying to collect as much
     * gold as possible along the way. Your solution must ALWAYS escape before time runs
     * out, and this should be prioritized above collecting gold.
     * <p>
     * You now have access to the entire underlying graph, which can be accessed through EscapeState.
     * getCurrentNode() and getExit() will return you Node objects of interest, and getVertices()
     * will return a collection of all nodes on the graph.
     * <p>
     * Note that time is measured entirely in the number of steps taken, and for each step
     * the time remaining is decremented by the weight of the edge taken. You can use
     * getTimeRemaining() to get the time still remaining, pickUpGold() to pick up any gold
     * on your current tile (this will fail if no such gold exists), and moveTo() to move
     * to a destination node adjacent to your current node.
     * <p>
     * You must return from this function while standing at the exit. Failing to do so before time
     * runs out or returning from the wrong location will be considered a failed run.
     * <p>
     * You will always have enough time to escape using the shortest path from the starting
     * position to the exit, although this will not collect much gold.
     *
     * @param state the information available at the current state
     */
    public void escape(EscapeState state) {
        Stack<Node> route = getShortestRoute(state.getCurrentNode(), state.getExit());

        System.out.println(state.getCurrentNode().getId());
        while (!route.empty()) {
            if (state.getCurrentNode().getTile().getGold() > 0) {
                state.pickUpGold();
            }

            Node node = route.pop();
            System.out.print(node.getId() + ", ");
            // TODO: moveTo() throws an exception if the node is the same as the current node
            if (!state.getCurrentNode().equals(node)) {
                state.moveTo(node);
            }
        }
        System.out.println();
    }

    private Stack<Node> getShortestRoute(Node start, Node end) {
        // Copied most of the code from Cavern.minPathLengthToTarget() to calculate the shortest path
        InternalMinHeap<Node> frontier = new InternalMinHeap<>();
        Map<Long, Integer> pathWeights = new HashMap<>();
        Map<Node, Node> pathNodes = new HashMap<>();

        pathWeights.put(start.getId(), 0);
        frontier.add(start, 0);

        while (!frontier.isEmpty()) {
            Node f = frontier.poll();
            if (f.equals(end)) {
                break;
            }

            int nWeight = pathWeights.get(f.getId());

            for (Edge e : f.getExits()) {
                Node w = e.getOther(f);
                int weightThroughN = nWeight + e.length();
                Integer existingWeight = pathWeights.get(w.getId());
                if (existingWeight == null) {
                    pathWeights.put(w.getId(), weightThroughN);
                    frontier.add(w, weightThroughN);
                } else if (weightThroughN < existingWeight) {
                    pathWeights.put(w.getId(), weightThroughN);
                    frontier.changePriority(w, weightThroughN);
                }

                if (existingWeight == null || weightThroughN < existingWeight) {
                    pathNodes.put(w, f);
                }

            }
        }

        //
        System.out.println(pathWeights.keySet());
        for (Node n : pathNodes.keySet()) {
            System.out.print(n.getId() + ", ");
        }
        System.out.println();

        Stack<Node> path = new Stack<>();

        Node n = end;
        while (n != null) {
            path.push(n);
            n = pathNodes.get(n);
        }

        return path;
    }

}

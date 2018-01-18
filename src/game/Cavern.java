package game;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * An instance represents a Cavern that the explorer can navigate through.
 * The cavern is set up as a grid of Tile objects with a weighted graph of all non-floor tiles.
 * There is an entrance to the cavern and a target location (which may also be the entrance).
 */
public class Cavern {

    public static final int MAX_EDGE_WEIGHT = 15;
    public static final int MAX_GOLD_VALUE = 1000;
    public static final int TASTY_VALUE = 5000;
    private static final double DENSITY = 0.6;
    private static final double GOLD_PROBABILITY = 0.33;
    private final int rows;
    private final int cols;
    private final Set<Node> graph;
    private final Node entrance;
    private final Node target;
    private final Node[][] tiles;

    /**
     * Constructor: an instance of size (rws, cls) .
     * Use rand as a source of randomness for the cavern generation.
     * Use {@code edgeWeightGenerator} and {@code goldGenerator} to generate edge weights and gold values.
     * Precondition: {@code targetType} must be either {@code Tile.Type.ORB} or {@code Tile.Type.ENTRANCE}.
     */
    private Cavern(int rws,
                   int cls,
                   Random rand,
                   Supplier<Integer> edgeWeightGenerator,
                   Supplier<Integer> goldGenerator,
                   Tile.Type targetType) {
        rows = rws;
        cols = cls;

        graph = generateGraph(rand, targetType, goldGenerator);

        entrance = graph.stream().filter((n) -> n.getTile().getType() == Tile.Type.ENTRANCE).findAny().get();

        target = graph.stream().filter((n) -> n.getTile().getType() == targetType).findAny().get();

        // Set tiles for the floor and then add walls wherever floor is missing.
        tiles = new Node[rows][cols];
        for (Node node : graph) {
            Tile t = node.getTile();
            tiles[t.getRow()][t.getColumn()] = node;
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (tiles[i][j] == null) {
                    tiles[i][j] = new Node(new Tile(i, j, 0, Tile.Type.WALL));
                }
            }
        }
        createEdges(tiles, edgeWeightGenerator);
    }

    /**
     * Constructor: an instance using givenGraph, givenTiles, and target trgt.
     * Preconditions:
     * 1. {@code givenGraph} and {@code givenTiles} represent the same graph
     * (i.e. {@code givenGraph} contains all non-floor nodes in
     * {@code givenTiles} and edges are along the grid).
     * 2. {@code trgt} is a node in {@code givenGraph}.
     */
    private Cavern(Set<Node> givenGraph, Node[][] givenTiles, Node trgt) {
        tiles = givenTiles;
        rows = tiles.length;
        cols = tiles[0].length;

        graph = Collections.unmodifiableSet(givenGraph);

        entrance = graph.stream().filter((n) -> n.getTile().getType() == Tile.Type.ENTRANCE).findAny().get();

        target = trgt;
    }

    /**
     * Return a new random Cavern of size (rows, cols) with no gold.
     * All edges have weight 1, and an orb is a reasonable distance from the exit.
     * Use rand as a source of randomness for the cavern generation.
     */
    public static Cavern digExploreCavern(int rows, int cols, Random rand) {
        int minOrbDist = minOrbDistance(rows, cols);

        Cavern cavern = new Cavern(rows, cols, rand, () -> 1, () -> 0, Tile.Type.ORB);
        while (cavern.minPathLengthToTarget(cavern.getEntrance()) < minOrbDist) {
            cavern = new Cavern(rows, cols, rand, () -> 1, () -> 0, Tile.Type.ORB);
        }
        return cavern;
    }

    /**
     * Return the minimum allowable path distance from the entrance to the orb.
     */
    private static int minOrbDistance(int rows, int cols) {
        return (rows + cols) / 2;
    }

    /**
     * Return a new random Cavern of size (rows, cols) with random gold and edge weights.
     * It is guaranteed that (currentRow, currentCol}) will be an open floor cell.
     * Use rand as a source of randomness for the cavern generation.
     */
    public static Cavern digEscapeCavern(int rows, int cols, int currentRow, int currentCol, Random rand) {
        Supplier<Integer> edgeWeightGen = () -> rand.nextInt(MAX_EDGE_WEIGHT) + 1;
        Supplier<Integer> goldGen = () -> Cavern.generateGoldValue(rand);
        Cavern potentialCavern = new Cavern(rows, cols, rand, edgeWeightGen, goldGen, Tile.Type.ENTRANCE);
        while (potentialCavern.getTileAt(currentRow, currentCol).getType() != Tile.Type.FLOOR) {
            potentialCavern = new Cavern(rows, cols, rand, edgeWeightGen, goldGen, Tile.Type.ENTRANCE);
        }
        return potentialCavern;
    }

    /**
     * Return a randomly determined gold value for a given tile.
     */
    private static int generateGoldValue(Random rand) {
        if (rand.nextDouble() > GOLD_PROBABILITY) {
            return 0;
        }

        int val = rand.nextInt(MAX_GOLD_VALUE) + 1;
        if (val == MAX_GOLD_VALUE) {
            val = TASTY_VALUE;
        }
        return val;
    }

    /**
     * Convert nodeStrList into a Cavern and return it.
     * Precondition: The list of strings is of the format output by {@code serialize()}.
     */
    public static Cavern deserialize(List<String> nodeStrList) {
        String extraInfo = nodeStrList.get(0);
        String[] infoParts = extraInfo.split(",");
        String[] dimensions = infoParts[0].split(":");
        int rows = Integer.parseInt(dimensions[0]);
        int cols = Integer.parseInt(dimensions[1]);
        long targetId = Long.parseLong(infoParts[1].split(":")[1]);

        Map<Long, Node> idToNode = new HashMap<>();
        for (String nodeStr : nodeStrList) {
            if (!nodeStr.equals(extraInfo)) {

                String nodeInfo = nodeStr.substring(0, nodeStr.indexOf("="));
                String[] splitInfo = nodeInfo.split(",");

                long nodeId = Long.parseLong(splitInfo[0]);
                Node n = new Node(nodeId,
                        new Tile(Integer.parseInt(splitInfo[1]),
                                Integer.parseInt(splitInfo[2]),
                                Integer.parseInt(splitInfo[3]),
                                Tile.Type.valueOf(splitInfo[4])));
                idToNode.put(nodeId, n);
            }
        }

        Node[][] tiles = new Node[rows][cols];
        for (String nodeStr : nodeStrList) {
            // The first line is not a node, it's metadata, so skip it.
            if (nodeStr.equals(extraInfo)) {
                continue;
            }

            String[] nodeAndEdgeInfo = nodeStr.split("=");
            long nodeId = Long.parseLong(nodeAndEdgeInfo[0].split(",")[0]);

            Node n = idToNode.get(nodeId);
            tiles[n.getTile().getRow()][n.getTile().getColumn()] = n;
            for (String edgeStr : nodeAndEdgeInfo[1].split(",")) {
                String[] idAndWeight = edgeStr.split("-");
                long otherId = Long.parseLong(idAndWeight[0]);
                int weight = Integer.parseInt(idAndWeight[1]);
                n.addEdge(new Edge(n, idToNode.get(otherId), weight));
            }
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (tiles[i][j] == null) {
                    tiles[i][j] = new Node(new Tile(i, j, 0, Tile.Type.WALL));
                }
            }
        }
        return new Cavern(new HashSet<>(idToNode.values()), tiles, idToNode.get(targetId));
    }

    /**
     * Add edges to the grid of nodes, tiles, between adjacent non-wall tiles,
     * using edgeWeightGenerator to get edge weights.
     * Precondition: all elements of {@code tiles} are non-null and
     * the grid of nodes has no edges.
     */
    private void createEdges(Node[][] tiles, Supplier<Integer> edgeWeightGenerator) {
        for (int i = 0; i < tiles.length - 1; i++) {
            for (int j = 0; j < tiles[i].length - 1; j++) {
                Node node = tiles[i][j];
                if (node.getTile().getType() == Tile.Type.WALL) {
                    continue;
                }

                final Point p = new Point(i, j);
                Stream.of(Direction.SOUTH, Direction.EAST)
                        .map(Direction::getPoint)
                        .map(p::add)
                        .map((q) -> tiles[q.row][q.col])
                        .filter((m) -> m.getTile().getType() != Tile.Type.WALL)
                        .forEach((m) -> {
                            int weight = edgeWeightGenerator.get();
                            node.addEdge(new Edge(node, m, weight));
                            m.addEdge(new Edge(m, node, weight));
                        });
            }
        }
    }

    /**
     * Return true iff p is on the grid
     */
    private boolean isValid(Point p) {
        return p.row > 0 && p.row < rows - 1 &&
                p.col > 0 && p.col < cols - 1;
    }

    /**
     * Generate a new random graph that fits within the grid and return the set of nodes.
     * Use goldGenerator to generate gold.
     * If targetType is not ENTRANCE, make the target be some random node that is
     * not the entrance.
     */
    private Set<Node> generateGraph(Random rand,
                                    Tile.Type targetType,
                                    Supplier<Integer> goldGenerator) {
        List<Node> nodes = new ArrayList<>();

        Set<Point> pointsSeen = new HashSet<>();
        Set<Point> openPoints = new HashSet<>();
        Queue<Node> frontier = new ArrayDeque<>();

        Point entrancePoint = getEntrancePoint(rand);
        Node entrance = new Node(new Tile(entrancePoint.row, entrancePoint.col, 0, Tile.Type.ENTRANCE));
        nodes.add(entrance);

        pointsSeen.add(entrancePoint);
        openPoints.add(entrancePoint);
        frontier.add(entrance);
        while (!frontier.isEmpty()) {
            Node node = frontier.remove();
            Point p = new Point(node.getTile().getRow(), node.getTile().getColumn());

            // We want to make sure there's a way out if we can get one.
            // This will prevent stupid degenerate graphs.
            int existingExits = 0;
            List<Point> newExits = new ArrayList<>();
            for (Direction dir : Direction.values()) {
                Point newPt = dir.getPoint().add(p);
                if (isValid(newPt)) {
                    if (openPoints.contains(newPt)) {
                        existingExits++;
                    } else if (pointsSeen.add(newPt)) {
                        newExits.add(newPt);
                    }
                }
            }

            int nExits = newExits.size();
            if (nExits > 0) {
                double modifiedDensity;
                Point forcedExit;
                // Modify the density function so that the expected number of open exits
                // is the same even though we're forcing something to be open.
                if (existingExits < 2) {
                    modifiedDensity = (nExits == 1 ? 0.0 : (nExits * DENSITY - 1) / (nExits - 1));
                    forcedExit = newExits.get(rand.nextInt(newExits.size()));
                } else {
                    modifiedDensity = DENSITY;
                    forcedExit = null;
                }
                newExits.stream()
                        .filter((q) -> q.equals(forcedExit) || rand.nextDouble() < modifiedDensity)
                        .peek(openPoints::add)
                        .map((q) -> new Node(new Tile(q.row, q.col, goldGenerator.get(), Tile.Type.FLOOR)))
                        .peek(frontier::add)
                        .forEach(nodes::add);
            }
        }

        if (targetType != Tile.Type.ENTRANCE) {
            // Grab a random tile that's not the entrance and make it the target.
            int targetIdx = rand.nextInt(nodes.size() - 1) + 1;
            nodes.get(targetIdx).getTile().setType(targetType);
        }

        return Collections.unmodifiableSet(new HashSet<>(nodes));
    }

    /**
     * Randomly determine and return the entrance to the cavern
     * (the only non-wall tile along an edge of the grid).
     */
    private Point getEntrancePoint(Random rand) {
        switch (rand.nextInt(4)) {
            case 0: // North wall
                return new Point(rand.nextInt(rows - 2) + 1, 0);
            case 1: // South wall
                return new Point(rand.nextInt(rows - 2) + 1, cols - 1);
            case 2: // West wall
                return new Point(0, rand.nextInt(cols - 2) + 1);
            case 3: // East wall
                return new Point(rows - 1, rand.nextInt(cols - 2) + 1);
            default:
                throw new IllegalStateException("Unexpected random value!");
        }
    }

    /**
     * Return the number of open floor tiles in this cavern (this is the size of the graph).
     */
    public int numOpenTiles() {
        return graph.size();
    }

    /**
     * Return the number of rows in the grid.
     */
    public int getRowCount() {
        return rows;
    }

    /**
     * Return the number of columns in the grid.
     */
    public int getColumnCount() {
        return cols;
    }

    /**
     * Return the set of all nodes in the graph.
     * This is an umodifiable view of the graph.
     */
    public Set<Node> getGraph() {
        return graph;
    }

    /**
     * Return the node corresponding to the entrance to the cavern.
     */
    public Node getEntrance() {
        return entrance;
    }

    /**
     * Return the target node in this cavern.
     */
    public Node getTarget() {
        return target;
    }

    /**
     * Return the Tile information for (r, c).
     * Precondition: ({@code r}, {@code c}) must be in the grid.
     */
    public Tile getTileAt(int r, int c) {
        return tiles[r][c].getTile();
    }

    /**
     * Return the node at position (r, c).
     * Precondition: ({@code r}, {@code c}) must be in the grid.
     */
    public Node getNodeAt(int r, int c) {
        return tiles[r][c];
    }

    /**
     * Package-private implementation of Dijkstra's algorithm that returns
     * only the minimum distance between the given node and the target node for
     * this cavern (no path).
     * Precondition: {@code start} must be a node in this cavern.
     */
    int minPathLengthToTarget(Node start) {

        InternalMinHeap<Node> frontier = new InternalMinHeap<>();

        /** Contains an entry for each node in the Settled and Frontier sets. */
        Map<Long, Integer> pathWeights = new HashMap<>();

        pathWeights.put(start.getId(), 0);
        frontier.add(start, 0);
        /// invariant: as in lecture notes
        while (!frontier.isEmpty()) {
            Node f = frontier.poll();
            if (f.equals(target)) {
                return pathWeights.get(f.getId());
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
            }
        }
        throw new IllegalStateException("The above loop should always reach the desired location.");
    }

    /**
     * Serialize this cavern as a list of strings that can be written to a file.
     * The list can be converted back into a {@code Cavern} using {@code deserialize()}.
     */
    public List<String> serialize() {
        List<String> nodes = new ArrayList<>();
        nodes.add(rows + ":" + cols + ",trgt:" + target.getId());
        for (Node n : graph) {
            Tile t = n.getTile();
            String nodeStr = n.getId() + "," + t.getRow() + "," + t.getColumn() + "," + t.getGold() + "," + t.getType().name();

            String edges = n.getExits().stream()
                    .map((e) -> e.getOther(n).getId() + "-" + e.length())
                    .collect(Collectors.joining(","));
            nodes.add(nodeStr + "=" + edges);
        }
        return nodes;
    }

    /**
     * An enum reprsenting a grid direction.
     */
    public enum Direction {
        NORTH(-1, 0), EAST(0, 1), SOUTH(1, 0), WEST(0, -1);

        private final Point dir;

        /**
         * Constructor: an instance indirection (row, col).
         */
        private Direction(int row, int col) {
            dir = new Point(row, col);
        }

        /**
         * Return the direction.
         */
        public Point getPoint() {
            return dir;
        }
    }

    /**
     * An instance is a point (row, col) on the grid.
     */
    private static class Point {
        private final int row;
        private final int col;

        /**
         * Constructor: an instance (r, c).
         */
        private Point(int r, int c) {
            row = r;
            col = c;
        }

        /**
         * Return a new point, which is p added to this point.
         */
        public Point add(Point p) {
            return new Point(row + p.row, col + p.col);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Point)) return false;
            Point p = (Point) o;
            return p.row == row && p.col == col;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, col);
        }
    }
}

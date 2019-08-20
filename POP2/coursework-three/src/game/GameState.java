package game;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import gui.GUI;

import student.Explorer;

public class GameState implements ExplorationState, EscapeState {

    public static final int MIN_ROWS = 8;
    public static final int MAX_ROWS = 25;
    public static final int MIN_COLS = 12;
    public static final int MAX_COLS = 40;
    public static final double MIN_BONUS = 1.0;
    public static final double MAX_BONUS = 1.3;
    private static final double EXTRA_TIME_FACTOR = 0.3;     //bigger is nicer - addition to total multiplier
    private static final double NO_BONUS_LENGTH = 3;
    private final Cavern exploreCavern;
    private final Cavern escapeCavern;
    private final Explorer explorer;
    private final Optional<GUI> gui;
    private final long seed;
    private Node position;
    private int stepsTaken;
    private int timeRemaining;
    private int goldCollected;
    private Stage stage;
    private boolean exploreSucceeded = false;
    private boolean escapeSucceeded = false;
    private boolean exploreErrored = false;
    private boolean escapeErrored = false;
    private int minTimeToExplore;

    /* package */ GameState(Path exploreCavernPath, Path escapeCavernPath) throws IOException {
        exploreCavern = Cavern.deserialize(Files.readAllLines(exploreCavernPath));
        minTimeToExplore = exploreCavern.minPathLengthToTarget(exploreCavern.getEntrance());
        escapeCavern = Cavern.deserialize(Files.readAllLines(escapeCavernPath));

        explorer = new Explorer();

        position = exploreCavern.getEntrance();
        stepsTaken = 0;
        timeRemaining = Integer.MAX_VALUE;
        goldCollected = 0;

        seed = -1;

        stage = Stage.EXPLORE;
        gui = Optional.of(new GUI(exploreCavern, position.getTile().getRow(), position.getTile().getColumn(), 0));
    }

    /**
     * Constructor: a random instance with a GUI only if useGUI is true.
     */
    private GameState(boolean useGui) {
        this((new Random()).nextLong(), useGui);
    }

    /**
     * Constructor: a random instance with a GUI only if useGUI is true.
     * The randomness is based on seed.
     */
    private GameState(long seed, boolean useGui) {
        Random rand = new Random(seed);
        int ROWS = rand.nextInt(MAX_ROWS - MIN_ROWS + 1) + MIN_ROWS;
        int COLS = rand.nextInt(MAX_COLS - MIN_COLS + 1) + MIN_COLS;
        exploreCavern = Cavern.digExploreCavern(ROWS, COLS, rand);
        minTimeToExplore = exploreCavern.minPathLengthToTarget(exploreCavern.getEntrance());
        Tile orbTile = exploreCavern.getTarget().getTile();
        escapeCavern = Cavern.digEscapeCavern(ROWS, COLS, orbTile.getRow(), orbTile.getColumn(), rand);

        position = exploreCavern.getEntrance();
        stepsTaken = 0;
        timeRemaining = Integer.MAX_VALUE;
        goldCollected = 0;

        explorer = new Explorer();
        stage = Stage.EXPLORE;

        this.seed = seed;

        if (useGui) {
            gui = Optional.of(new GUI(exploreCavern, position.getTile().getRow(),
                    position.getTile().getColumn(), seed));
        } else {
            gui = Optional.empty();
        }
    }

    public static int runNewGame(long seed, boolean useGui) {
        GameState state;
        if (seed != 0) {
            state = new GameState(seed, useGui);
        } else {
            state = new GameState(useGui);
        }
        System.out.println("Seed : " + state.seed);
        state.run();
        return state.getScore();
    }

    public static void main(String[] args) throws IOException {
        List<String> argList = new ArrayList<String>(Arrays.asList(args));
        int repeatNumberIndex = argList.indexOf("-n");
        int numTimesToRun = 1;
        if (repeatNumberIndex >= 0) {
            try {
                numTimesToRun = Math.max(Integer.parseInt(argList.get(repeatNumberIndex + 1)), 1);
            } catch (Exception e) {
                // numTimesToRun = 1
            }
        }
        int seedIndex = argList.indexOf("-s");
        long seed = 0;
        if (seedIndex >= 0) {
            try {
                seed = Long.parseLong(argList.get(seedIndex + 1));
            } catch (NumberFormatException e) {
                System.err.println("Error, -s must be followed by a numerical seed");
                return;
            } catch (ArrayIndexOutOfBoundsException e) {
                System.err.println("Error, -s must be followed by a seed");
                return;
            }
        }

        int totalScore = 0;
        for (int i = 0; i < numTimesToRun; i++) {
            totalScore += runNewGame(seed, false);
            System.out.println();
        }

        System.out.println("Average score : " + totalScore / numTimesToRun);
    }

    /**
     * Run the game: first find the Orb and then escape.
     */
    private void run() {
        // TODO: In the error cases we should really pop something up!
        explore();
        if (!exploreSucceeded) return;
        escape();
    }

    /* package */ void explore() {
        stage = Stage.EXPLORE;
        stepsTaken = 0;
        exploreSucceeded = false;
        position = exploreCavern.getEntrance();
        gui.ifPresent((g) -> g.setLighting(false));
        gui.ifPresent((g) -> g.updateCavern(exploreCavern, 0));
        gui.ifPresent((g) -> g.moveTo(position));

        try {
            explorer.explore(this);
            if (position.equals(exploreCavern.getTarget())) {
                exploreSucceeded = true;
            } else {
                System.err.println("Your solution to explore returned at the wrong location.");
                gui.ifPresent((g) -> g.displayError("Your solution to explore returned at the wrong location."));
            }
        } catch (Throwable t) {
            System.err.println("Your code errored during the explore phase.");
            gui.ifPresent((g) -> g.displayError("Your code errored during the explore phase. Please see console output."));
            System.err.println("We will move on to the escape phase anyway, but your solution is not correct!");
            System.err.println("Here is the error that occurred.");
            t.printStackTrace();
            exploreErrored = true;
        }
    }

    /* package */ void escape() {
        stage = Stage.ESCAPE;
        Tile orbTile = exploreCavern.getTarget().getTile();
        position = escapeCavern.getNodeAt(orbTile.getRow(), orbTile.getColumn());
        if (exploreSucceeded) {
            timeRemaining = computeTimeToEscape();
        } else {
            timeRemaining = escapeCavern.minPathLengthToTarget(position);
            gui.ifPresent((g) -> g.moveTo(position));
        }
        gui.ifPresent((g) -> g.setLighting(true));
        gui.ifPresent((g) -> g.updateCavern(escapeCavern, timeRemaining));

        try {
            explorer.escape(this);
            if (position.equals(escapeCavern.getTarget())) {
                escapeSucceeded = true;
            }
        } catch (OutOfTimeException e) {
            System.err.println("Your solution to escape ran out of steps before returning!");
            gui.ifPresent((g) -> g.displayError("Your solution to escape ran out of steps before returning!"));
        } catch (Throwable t) {
            System.err.println("Your code errored during the escape phase.");
            gui.ifPresent((g) -> g.displayError("Your code errored during the escape phase. Please see console output."));
            t.printStackTrace();
            escapeErrored = true;
        }

        if (!escapeSucceeded) {
            System.err.println("Your solution to escape failed to end at the stairs. Your code is not correct!");
            gui.ifPresent((g) -> g.displayError("Your solution to escape failed to end at the stairs. Your code is not correct!"));
        }
        System.out.println("Gold collected   : " + getGoldCollected());
        DecimalFormat df = new DecimalFormat("#.##");
        System.out.println("Bonus multiplier : " + df.format(computeBonusFactor()));
        System.out.println("Score            : " + getScore());
    }

    /**
     * Return the time to escape
     */
    private int computeTimeToEscape() {
        //        int minTimeToExplore= exploreCavern.minPathLengthToTarget(exploreCavern.getEntrance());
        //        int minTimeToEscape= escapeCavern.minPathLengthToTarget(position);
        //
        //        int maxExtraTime= escapeCavern.getRowCount() * escapeCavern.getColumnCount() * Cavern.MAX_EDGE_WEIGHT / 6;
        //        double extraTimeFactor = Math.max(0.0, 4 - ((double)stepsTaken - minTimeToExplore)/minTimeToExplore) / 4;
        //        return minTimeToEscape + (int)(maxExtraTime * extraTimeFactor);
        int minTimeToEscape = escapeCavern.minPathLengthToTarget(position);
        return (int) (minTimeToEscape + EXTRA_TIME_FACTOR * (Cavern.MAX_EDGE_WEIGHT + 1) * escapeCavern.numOpenTiles() / 2);

    }

    /**
     * Return the bonus factor, as described in handout.
     */
    private double computeBonusFactor() {
        double exploreDiff = (stepsTaken - minTimeToExplore) / (double) minTimeToExplore;
        if (exploreDiff <= 0) return MAX_BONUS;
        double multDiff = MAX_BONUS - MIN_BONUS;
        return Math.max(MIN_BONUS, MAX_BONUS - exploreDiff / NO_BONUS_LENGTH * multDiff);
    }

    /**
     * See moveTo(Node&lt;TileData&gt; n)
     *
     * @param id The Id of the neighboring Node to move to
     */
    @Override
    public void moveTo(long id) {
        if (stage != Stage.EXPLORE) {
            throw new IllegalStateException("moveTo(ID) can only be called while exploring!");
        }

        for (var n : position.getNeighbors()) {
            if (n.getId() == id) {
                position = n;
                stepsTaken++;
                gui.ifPresent((g) -> g.updateBonus(computeBonusFactor()));
                gui.ifPresent((g) -> g.moveTo(n));
                return;
            }
        }
        throw new IllegalArgumentException("moveTo: Node must be adjacent to position");
    }

    /**
     * Returns the unique id of the current location.
     */
    @Override
    public long getCurrentLocation() {
        if (stage != Stage.EXPLORE) {
            throw new IllegalStateException("getLocation() can only be called while exploring!");
        }

        return position.getId();
    }

    /**
     * Returns a collection of NodeStatus objects which contain the unique ID of the node
     * and the distance from that node to the target.
     */
    @Override
    public Collection<NodeStatus> getNeighbors() {
        if (stage != Stage.EXPLORE) {
            throw new IllegalStateException("getNeighbors() can only be called while exploring!");
        }

        Collection<NodeStatus> options = new ArrayList<>();
        for (Node n : position.getNeighbors()) {
            int distance = computeDistanceToTarget(n.getTile().getRow(), n.getTile().getColumn());
            options.add(new NodeStatus(n.getId(), distance));
        }
        return options;
    }

    private int computeDistanceToTarget(int row, int col) {
        return Math.abs(row - exploreCavern.getTarget().getTile().getRow())
                + Math.abs(col - exploreCavern.getTarget().getTile().getColumn());
    }

    /**
     * Returns the distance from your current location to the target location on the map.
     */
    @Override
    public int getDistanceToTarget() {
        if (stage != Stage.EXPLORE) {
            throw new IllegalStateException("getDistanceToTarget() can only be called while exploring!");
        }

        return computeDistanceToTarget(position.getTile().getRow(), position.getTile().getColumn());
    }

    @Override
    public Node getCurrentNode() {
        if (stage != Stage.ESCAPE) {
            throw new IllegalStateException("getCurrentNode: Error, " +
                    "current Node may not be accessed unless in ESCAPE");
        }
        return position;
    }

    @Override
    public Node getExit() {
        if (stage != Stage.ESCAPE) {
            throw new IllegalStateException("getEntrance: Error, " +
                    "current Node may not be accessed unless in ESCAPE");
        }
        return escapeCavern.getTarget();
    }

    @Override
    public Collection<Node> getVertices() {
        if (stage != Stage.ESCAPE) {
            throw new IllegalStateException("getVertices: Error, " +
                    "Vertices may not be accessed unless in ESCAPE");
        }
        return Collections.unmodifiableSet(escapeCavern.getGraph());
    }

    /**
     * Attempts to move the explorer from the current position to
     * the <tt>Node</tt> <tt>n</tt>. Throws an <tt>IllegalArgumentException</tt>
     * if <tt>n</tt> is not neighboring. Increments the steps taken
     * if successful.
     *
     * @param n A neighboring <tt>Node</tt>
     */
    @Override
    public void moveTo(Node n) {
        if (stage != Stage.ESCAPE) {
            throw new IllegalStateException("moveTo(Node) can only be called when escaping!");
        }
        int distance = position.getEdge(n).length;
        if (timeRemaining - distance < 0) {
            throw new OutOfTimeException();
        }

        if (position.getNeighbors().contains(n)) {
            position = n;
            timeRemaining -= distance;
            gui.ifPresent((g) -> g.updateTimeRemaining(timeRemaining));
            gui.ifPresent((g) -> g.moveTo(n));
        } else {
            throw new IllegalArgumentException("moveTo: Node must be adjacent to position");
        }
    }

    @Override
    public void pickUpGold() {
        if (stage != Stage.ESCAPE) {
            throw new IllegalStateException("pickUpGold() can only be called while escaping!");
        } else if (position.getTile().getGold() <= 0) {
            throw new IllegalStateException("pickUpGold: Error, no gold on this tile");
        }
        goldCollected += position.getTile().takeGold();
        gui.ifPresent((g) -> g.updateCoins(goldCollected, getScore()));
    }

    @Override
    public int getTimeRemaining() {
        if (stage != Stage.ESCAPE) {
            throw new IllegalStateException("getTimeRemaining() can only be called while escaping!");
        }
        return timeRemaining;
    }

    /* package */ int getGoldCollected() {
        return goldCollected;
    }

    /**
     * Returns the player's current score.
     *
     * @return the player's current score
     */
    /* package */ int getScore() {
        return (int) (computeBonusFactor() * goldCollected);
    }

    /* package */ boolean getExploreSucceeded() {
        return exploreSucceeded;
    }

    /* package */ boolean getEscapeSucceeded() {
        return escapeSucceeded;
    }

    /* package */ boolean getExploreErrored() {
        return exploreErrored;
    }

    /* package */ boolean getEscapeErrored() {
        return escapeErrored;
    }

    private enum Stage {
        EXPLORE, ESCAPE;
    }

    @SuppressWarnings("serial")
    private static class OutOfTimeException extends RuntimeException {
    }
}

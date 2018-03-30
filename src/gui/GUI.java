package gui;

import game.Cavern;
import game.GameState;
import game.Node;
import game.Tile;

import java.awt.Point;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

/**
 * An instance is a GUI for the game.
 * Run this file as a Java application to test the project.
 */
public class GUI extends JFrame {
    public static final double GAME_WIDTH_PROP = 0.78;       //Width of the game portion (prop of total)
    public static final double GAME_HEIGHT_PROP = 1.0;      //Height of the game portion (prop of total)
    private static final long serialVersionUID = 1L;
    //Dimensions of the error pane (in pixels)
    private static final int ERROR_WIDTH = 500;
    private static final int ERROR_HEIGHT = 150;
    private static final double INFO_SIZE = 0.5;    //How much of the screen should the info make up?
    public static int SCREEN_WIDTH = 1050;    //Width of the entire screen
    public static int SCREEN_HEIGHT = 600;    //Height of the entire screen
    public static int FRAMES_PER_SECOND = 60;    //Framerate of game (fps)
    public static int FRAMES_PER_MOVE = 25;      //How many frames does a single move take us?
    private MazePanel mazePanel;            //The panel for generating and drawing the maze
    private ExplorerSprite explorer;        //The panel for updating and drawing the explorer
    private OptionsPanel options;           //The panel for showing stats / displaying options
    private TileSelectPanel tileSelect;     //Panel that provides more info on seleced tile
    private JLayeredPane master;            //The panel that holds all other panels

    /* Constructor a new display for cavern canvern with the player at (playerRow, playerCol)
     * using randomg number seed seed. */
    public GUI(Cavern cavern, int playerRow, int playerCol, long seed) {
        //Initialize frame
        setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        setLocation(150, 150);

        int GAME_WIDTH = (int) (GAME_WIDTH_PROP * SCREEN_WIDTH);
        int GAME_HEIGHT = (int) (GAME_HEIGHT_PROP * SCREEN_HEIGHT);

        //Create the maze
        mazePanel = new MazePanel(cavern, GAME_WIDTH, GAME_HEIGHT, this);
        mazePanel.setBounds(0, 0, GAME_WIDTH, GAME_HEIGHT);
        mazePanel.setVisited(playerRow, playerCol);

        //Create the explorer
        explorer = new ExplorerSprite(playerRow, playerCol);
        explorer.setBounds(0, 0, GAME_WIDTH, GAME_HEIGHT);
        explorer.setOpaque(false);

        //Create the panel for stats and options
        options = new OptionsPanel(GAME_WIDTH, 0, SCREEN_WIDTH - GAME_WIDTH, (int) (SCREEN_HEIGHT * INFO_SIZE), seed);

        //Create the panel for tile information
        tileSelect = new TileSelectPanel(GAME_WIDTH, (int) (SCREEN_HEIGHT * INFO_SIZE),
                SCREEN_WIDTH - GAME_WIDTH, (int) (SCREEN_HEIGHT * (1 - INFO_SIZE)), this);

        //Layer the explorer and maze into master panel
        master = new JLayeredPane();

        master.add(mazePanel, new Integer(1));
        master.add(options, new Integer(1));
        master.add(tileSelect, new Integer(1));
        master.add(explorer, new Integer(2));

        //Display GUI
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(master);
        setVisible(true);

        //What to do when the GUI resized?
        addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
                SCREEN_WIDTH = getWidth();
                SCREEN_HEIGHT = getHeight();
                int GAME_WIDTH = (int) (GAME_WIDTH_PROP * SCREEN_WIDTH);
                int GAME_HEIGHT = (int) (GAME_HEIGHT_PROP * SCREEN_HEIGHT);
                mazePanel.updateScreenSize(GAME_WIDTH, GAME_HEIGHT);
                mazePanel.setBounds(0, 0, GAME_WIDTH, GAME_HEIGHT);
                explorer.setBounds(0, 0, GAME_WIDTH, GAME_HEIGHT);
                explorer.repaint();
                options.setBounds(GAME_WIDTH, 0, SCREEN_WIDTH - GAME_WIDTH, (int) (SCREEN_HEIGHT * INFO_SIZE));
                tileSelect.updateLoc(GAME_WIDTH, (int) (SCREEN_HEIGHT * INFO_SIZE),
                        SCREEN_WIDTH - GAME_WIDTH, (int) (SCREEN_HEIGHT * (1 - INFO_SIZE)));
            }

            @Override
            public void componentMoved(ComponentEvent e) {
            }

            @Override
            public void componentShown(ComponentEvent e) {
            }

            @Override
            public void componentHidden(ComponentEvent e) {
            }
        });
    }

    /**
     * The main program.
     */
    public static void main(String[] args) {
        List<String> argList = new ArrayList<String>(Arrays.asList(args));
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

        GameState.runNewGame(seed, true);
    }

    /**
     * Move the player on the GUI to destination dest.
     * Note : This blocks until the player has moved.
     * Precondition : dest is adjacent to the player's current location
     */
    public void moveTo(Node dest) {
        try {
            mazePanel.setVisited(dest.getTile().getRow(), dest.getTile().getColumn());
            explorer.moveTo(dest);
        } catch (InterruptedException e) {
            throw new RuntimeException("GUI moveTo : Must wait for move to finish");
        }
    }

    /**
     * Update the bonus multiplier as displayed by the GUI by bonus
     */
    public void updateBonus(double bonus) {
        options.updateBonus(bonus);
    }

    /**
     * Update the number of coins picked up as displayed on the GUI.
     *
     * @param coins the number of coins to be displayed
     * @param score the player's current score
     */
    public void updateCoins(int coins, int score) {
        options.updateCoins(coins, score);
        tileSelect.repaint();
    }

    /**
     * Update the time remaining as displayed on the GUI.
     * timeRemaining is the time remaining before the cave collapses
     */
    public void updateTimeRemaining(int timeRemaining) {
        options.updateTimeRemaining(timeRemaining);
    }

    /**
     * What is the specification?
     */
    public void updateCavern(Cavern c, int numStepsRemaining) {
        mazePanel.setCavern(c);
        options.updateMaxTimeRemaining(numStepsRemaining);
        updateTimeRemaining(numStepsRemaining);
        tileSelect.repaint();
    }

    /**
     * Set the cavern to be all light or all dark, depending on light.
     */
    public void setLighting(boolean light) {
        mazePanel.setLighting(light);
    }

    /**
     * Return an image representing tile type.
     */
    public BufferedImage getIcon(Tile.Type type) {
        return mazePanel.getIcon(type);
    }

    /**
     * Return an icon for the gold on tile n, or null otherwise.
     */
    public BufferedImage getGoldIcon(Node n) {
        return mazePanel.getGoldIcon(n);
    }

    /**
     * Select node n on the GUI. This displays information on that
     * node's panel on the screen to the right.
     */
    public void selectNode(Node n) {
        tileSelect.selectNode(n);
    }

    /**
     * Display error e to the player.
     */
    public void displayError(String e) {
        JFrame errorFrame = new JFrame();
        errorFrame.setTitle("Error in Solution");
        JLabel errorText = new JLabel(e);
        errorText.setHorizontalAlignment(JLabel.CENTER);
        errorFrame.add(errorText);
        errorFrame.setSize(ERROR_WIDTH, ERROR_HEIGHT);
        errorFrame.setLocation(new Point(getX() + getWidth() / 2 - ERROR_WIDTH / 2,
                getY() + getHeight() / 2 - ERROR_HEIGHT / 2));
        errorFrame.setVisible(true);
    }
}

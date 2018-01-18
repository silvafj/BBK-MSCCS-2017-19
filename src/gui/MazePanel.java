package gui;

import game.Cavern;
import game.Node;
import game.Tile;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static gui.Constants.ROOT;

/**
 * An instance is responsible for drawing the underlying maze on the screen.
 * The MazePanel should contain only static images that don't need to change unless
 * the screen is redrawn.
 */
public class MazePanel extends JPanel {
    private static final long serialVersionUID = 1L;

    private static final String ORB_PATH = ROOT + "orb.png";           //Path to orb image
    private static final String PATH_PATH = ROOT + "path.png";         //Path to image representing path
    private static final String WALL_PATH = ROOT + "wall.png";         //Path to wall image
    private static final String COIN_PATH = ROOT + "coins.png";        //Path to the coin image
    private static final String ENTRANCE_PATH = ROOT + "entrance.png"; //Path to the entrance image
    private static final String TASTY_PATH = ROOT + "notes.txt";
    private static final String BACKGROUND_PATH = ROOT + "info_texture.png";
    private static final float DARK_FACTOR = 0.3f; //How dark should dark path be? Lower values means darker
    private static final int COIN_SPRITES_PER_ROW = 7;
    private static final int COIN_SPRITES_PER_COL = 2;

    public static int TILE_WIDTH;       //The width (in pixels) of a tile on the grid
    public static int TILE_HEIGHT;      //The height (in pixels) of a tile on the grid

    private final BufferedImage path;   //Image representing an area the explorer can walk on
    private final BufferedImage wall;   //Image representing a blocked area
    private final BufferedImage orb;    //Image representing the orb
    private final BufferedImage entrance;
    private final BufferedImage tasty;
    private final Sprite coinSheet;        //Image representing a coin spritesheet
    private BufferedImage background;
    private Cavern cavern;              //Representation of the graph/level
    private boolean[][] visited;         //Contains the nodes already visited
    private Color darkness;                           //Color to place over unvisited paths

    /**
     * Create a new MazePanel of a given size
     *
     * @param cav          The Cavern to display
     * @param screenWidth  The width of the panel, in pixels
     * @param screenHeight The height of the panel, in pixels
     * @param gui          The GUI that owns this MazePanel
     */
    public MazePanel(Cavern cav, int screenWidth, int screenHeight, GUI gui) {
        cavern = cav;
        visited = new boolean[cavern.getRowCount()][cavern.getColumnCount()];

        //Compute the dimensions of an individual tile
        TILE_WIDTH = (int) (screenWidth * 1.0 / cavern.getColumnCount());
        TILE_HEIGHT = (int) (screenHeight * 0.95 / cavern.getRowCount());
        //Force tiles to be square
        TILE_WIDTH = Math.min(TILE_WIDTH, TILE_HEIGHT);
        TILE_HEIGHT = Math.min(TILE_WIDTH, TILE_HEIGHT);

        //Load content
        try {
            path = ImageIO.read(new File(PATH_PATH));
            wall = ImageIO.read(new File(WALL_PATH));
            orb = ImageIO.read(new File(ORB_PATH));
            coinSheet = new Sprite(COIN_PATH, 32, 32, 1);
            entrance = ImageIO.read(new File(ENTRANCE_PATH));
            tasty = ImageIO.read(new File(TASTY_PATH));
            background = ImageIO.read(new File(BACKGROUND_PATH));
        } catch (IOException e) {
            throw new IllegalArgumentException("Can't find input file : " + e.toString());
        }

        //Create the dark path
        darkness = new Color(0, 0, 0, (int) (256 - 256 * DARK_FACTOR));

        //Add listener for clicking tiles
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int row = e.getY() / TILE_HEIGHT;
                int col = e.getX() / TILE_WIDTH;
                if (row < cavern.getRowCount() && col < cavern.getColumnCount()) {
                    gui.selectNode(cavern.getNodeAt(row, col));
                }
            }
        });
    }

    /**
     * Set the cavern to c
     */
    void setCavern(Cavern c) {
        cavern = c;
        repaint();
    }

    /**
     * The screen size has changed. Adjust the maze panel to (width, height).
     */
    void updateScreenSize(int width, int height) {
        TILE_WIDTH = (int) (width * 1.0 / cavern.getColumnCount());
        TILE_HEIGHT = (int) (height * 0.95 / cavern.getRowCount());
        //Force tiles to be square
        TILE_WIDTH = Math.min(TILE_WIDTH, TILE_HEIGHT);
        TILE_HEIGHT = Math.min(TILE_WIDTH, TILE_HEIGHT);
        repaint();
    }

    /**
     * Set the cavern to be all light (light = true) or all dark.
     */
    public void setLighting(boolean light) {
        for (int i = 0; i < cavern.getRowCount(); i++) {
            for (int j = 0; j < cavern.getColumnCount(); j++) {
                visited[i][j] = light;
            }
        }
        repaint();
    }

    /**
     * Update the GUI to inform it that tile (row, col) was visited.
     */
    public void setVisited(int row, int col) {
        visited[row][col] = true;
    }

    /**
     * Return an image representing tile type type.
     */
    public BufferedImage getIcon(Tile.Type type) {
        switch (type) {
            case FLOOR:
                return path;
            case ORB:
                return orb;
            case ENTRANCE:
                return entrance;
            case WALL:
                return wall;
            default:
                return path;
        }
    }

    /**
     * Return an icon for the gold on tile n, or null otherwise.
     */
    public BufferedImage getGoldIcon(Node n) {
        double gold = n.getTile().getGold();
        if (gold == Cavern.TASTY_VALUE) return tasty;
        gold *= ((double) COIN_SPRITES_PER_ROW * COIN_SPRITES_PER_COL) / Cavern.MAX_GOLD_VALUE;
        int spriteIndex = (int) gold;
        int rowIndex = spriteIndex / COIN_SPRITES_PER_ROW;
        int colIndex = spriteIndex % COIN_SPRITES_PER_ROW;
        return coinSheet.getSprite(rowIndex, colIndex);
    }

    /**
     * Draw the maze on the screen.
     */
    @Override
    public void paintComponent(Graphics page) {
        super.paintComponent(page);
        for (int i = 0; i < getWidth(); i += 100) {
            page.drawImage(background, i, 0, 100, getHeight(), null);
        }
        page.setColor(darkness);

        //Draw the maze tiles
        for (int row = 0; row < cavern.getRowCount(); row++) {
            for (int col = 0; col < cavern.getColumnCount(); col++) {
                //This is a walkable tile
                if (cavern.getTileAt(row, col).getType() != Tile.Type.WALL) {
                    //Draw the path image to the background
                    page.drawImage(path, TILE_WIDTH * col, TILE_HEIGHT * row,
                            TILE_WIDTH, TILE_HEIGHT, null);
                    //Darken this tile if we haven't been there yet
                    if (!visited[row][col]) {
                        page.fillRect(TILE_WIDTH * col, TILE_HEIGHT * row,
                                TILE_WIDTH, TILE_HEIGHT);
                    }
                    //If this is the goal, draw the orb
                    if (cavern.getTileAt(row, col).getType() == Tile.Type.ORB) {
                        page.drawImage(orb, TILE_WIDTH * col, TILE_HEIGHT * row,
                                TILE_WIDTH, TILE_HEIGHT, null);
                    }
                    //If there is a coin here, draw it
                    if (cavern.getTileAt(row, col).getGold() > 0) {
                        page.drawImage(getGoldIcon(cavern.getNodeAt(row, col)),
                                TILE_WIDTH * col, TILE_HEIGHT * row,
                                TILE_WIDTH, TILE_HEIGHT, null);
                    }
                    //If this tile is the entrance, draw the graphic
                    if (cavern.getTileAt(row, col).getType() == Tile.Type.ENTRANCE) {
                        page.drawImage(entrance, TILE_WIDTH * col, TILE_HEIGHT * row,
                                TILE_WIDTH, TILE_HEIGHT, null);
                    }
                }
                //This is a wall
                else {
                    page.drawImage(wall, TILE_WIDTH * col, TILE_HEIGHT * row,
                            TILE_WIDTH, TILE_HEIGHT, null);
                }
            }
        }
    }
}

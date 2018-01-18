package gui;

import game.Node;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static gui.Constants.ROOT;

/**
 * An instance is a panel that displays information about a currently selected Tile.
 */
public class TileSelectPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    //What font size do we want to use for the information?
    private static final int FONT_SIZE = 16;
    //Location of the rectangle to display, in proportions of the total panel
    private static final double RECT_X = 0.1;
    private static final double RECT_Y = 0.1;
    private static final double RECT_WIDTH = 0.75;
    private static final double RECT_HEIGHT = 0.65;
    //Height proportion at which to display tile type
    private static final double TYPE_HEIGHT = 0.17;
    //Location at which to draw the image
    private static final double IMAGE_X = 0.28;
    private static final double IMAGE_Y = 0.20;
    private static final double IMAGE_WIDTH = 0.38;
    private static final double IMAGE_HEIGHT = 0.25;
    //Location at which to draw the location info
    private static final double ROW_X = 0.15;
    private static final double COL_X = 0.47;
    private static final double ROW_WIDTH = 0.32;
    private static final double COL_WIDTH = 0.33;
    private static final double ROW_COL_Y = 0.60;
    //Height proportion at which to display the amount of gold
    private static final double GOLD_HEIGHT = 0.70;
    private BufferedImage background;        //Background for options pane
    private String BACKGROUND_PATH = ROOT + "info_texture.png";            //Location of background image
    private JLabel instructions;            //Tells user how to use this panel
    private GUI gui;                        //Information about the larger GUI
    private Node selectedNode;        //The currently selected node

    /* Constructor: an instance at (x, y) with size (width, height) on Guie gui. */
    public TileSelectPanel(int x, int y, int width, int height, GUI gui) {
        this.gui = gui;

        instructions = new JLabel("Select a tile for more information.");

        add(instructions);

        setBounds(x, y, width, height);

        //Load content
        try {
            background = ImageIO.read(new File(BACKGROUND_PATH));
        } catch (IOException e) {
            throw new IllegalArgumentException("Can't find input file : " + e.toString());
        }
    }

    /**
     * Update the location to (x, y) with size (width, height)
     * of this element (for instance, on screen resize).
     */
    public void updateLoc(int x, int y, int width, int height) {
        setBounds(x, y, width, height);
        repaint();
    }

    /**
     * Select node n on the GUI. This displays information on n's
     * panel on the screen to the right.
     */
    public void selectNode(Node n) {
        selectedNode = n;
        repaint();
    }

    /**
     * Determine at what x-coordinate to draw a string in order to center it.
     *
     * @param graphics the graphics, styled with the font to be used for drawing
     * @param str      the string to draw
     * @param minX     the minimum x we want to center over
     * @param width    the width of the area to center over
     * @return the minimum x to draw the string to center it
     */
    private int getTextXForCenter(Graphics2D graphics, String str, int minX, int width) {
        FontMetrics fm = graphics.getFontMetrics();
        Rectangle2D r = fm.getStringBounds(str, graphics);
        return minX + (width - (int) r.getWidth()) / 2;
    }

    /**
     * Paint this component using page.
     */
    @Override
    public void paintComponent(Graphics page) {
        super.paintComponent(page);
        page.drawImage(background, 0, 0, getWidth(), getHeight(), null);

        //Draw the rectangle outline the information panel
        page.drawRect((int) (RECT_X * getWidth()), (int) (RECT_Y * getHeight()),
                (int) (RECT_WIDTH * getWidth()), (int) (RECT_HEIGHT * getHeight()));

        //Display information about a node if one is present
        if (selectedNode != null) {
            //Tile type
            String text = selectedNode.getTile().getType().toString();
            page.setFont(new Font("default", Font.BOLD, FONT_SIZE));
            int x = getTextXForCenter((Graphics2D) page, text, (int) (RECT_X * getWidth()),
                    (int) (RECT_WIDTH * getWidth()));
            page.drawString(text, x, (int) (TYPE_HEIGHT * getHeight()));

            //Draw the image
            BufferedImage pic = gui.getIcon(selectedNode.getTile().getType());
            page.drawImage(pic, (int) (IMAGE_X * getWidth()), (int) (IMAGE_Y * getHeight()),
                    (int) (IMAGE_WIDTH * getWidth()), (int) (IMAGE_HEIGHT * getHeight()), null);
            //Draw the coin image
            if (selectedNode.getTile().getGold() > 0) {
                BufferedImage coinPic = gui.getGoldIcon(selectedNode);
                page.drawImage(coinPic, (int) (IMAGE_X * getWidth()), (int) (IMAGE_Y * getHeight()),
                        (int) (IMAGE_WIDTH * getWidth()), (int) (IMAGE_HEIGHT * getHeight()), null);
            }

            //Coordinates
            text = "Row : " + selectedNode.getTile().getRow();
            page.setFont(new Font("default", Font.ROMAN_BASELINE, FONT_SIZE));
            x = getTextXForCenter((Graphics2D) page, text, (int) (ROW_X * getWidth()),
                    (int) (ROW_WIDTH * getWidth()));
            page.drawString(text, x, (int) (ROW_COL_Y * getHeight()));
            text = "Col : " + selectedNode.getTile().getColumn();
            x = getTextXForCenter((Graphics2D) page, text, (int) (COL_X * getWidth()),
                    (int) (COL_WIDTH * getWidth()));
            page.drawString(text, x, (int) (ROW_COL_Y * getHeight()));

            //Amount of gold
            text = "Gold Count : " + selectedNode.getTile().getGold();
            x = getTextXForCenter((Graphics2D) page, text, (int) (RECT_X * getWidth()),
                    (int) (RECT_WIDTH * getWidth()));
            page.drawString(text, x, (int) (GOLD_HEIGHT * getHeight()));
        }
    }
}

package gui;

import game.Cavern;
import game.Cavern.Direction;
import game.Node;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.SynchronousQueue;

import static gui.Constants.ROOT;

/**
 * Responsible for managing the explorer and drawing it on the screen.
 * Handles functions to update the explorer and update its drawing as well.
 */
public class ExplorerSprite extends JPanel {
    private static final long serialVersionUID = 1L;
    private static final String SPRITESHEET = ROOT + "explorer_sprites.png";    //Location of the spritesheet image
    private Sprite sprite;                      //Sprite class to handle animating the explorer
    private int SPRITE_WIDTH = 29;              //Width (in pixels) of a single explorer image on the spritesheet
    private int SPRITE_HEIGHT = 36;             //Height (in pixels) if a single explorer image on the spritesheet
    private int row;                            //Explorer's row index (updates only once move completes)
    private int col;                            //Explorer's column index (updates only once move completes)
    private int posX;                           //x-coordinate (pixels)
    private int posY;                           //y-coordinate(pixels)
    private BlockingQueue<MovePair> queuedMoves;//List of moves we need to make to get to the goal location
    private Cavern.Direction dir = Direction.NORTH;       //Which direction is the explorer currently facing?
    private Semaphore blockUntilDone;           //Allow our moveTo to block until complete.
    private Thread updateThread;                //Thread that updates explorer's location
    private Thread animationUpdateThread;       //Thread that updates explorer's animation
    private double ANIMATION_FPS = 10;    //Number of animation frames displayed per second

    /**
     * Constructor:  an instance with player;'s starting position (startRow, startCol).
     */
    public ExplorerSprite(int startRow, int startCol) {
        //Initialize fields
        sprite = new Sprite(SPRITESHEET, SPRITE_WIDTH, SPRITE_HEIGHT, 3);
        queuedMoves = new SynchronousQueue<MovePair>();
        blockUntilDone = new Semaphore(0);

        //Initialize our starting location
        row = startRow;
        col = startCol;
        posX = row * MazePanel.TILE_WIDTH;
        posY = col * MazePanel.TILE_HEIGHT;

        //Create a thread which will periodically update the explorer's position
        updateThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        int frames = GUI.getFramesPerMove();
                        MovePair move = queuedMoves.take();
                        //Move to the goal
                        for (int i = 1; i <= frames; i++) {
                            long startTime = System.currentTimeMillis();
                            //Get the next move to make
                            update(frames, i, move);
                            long lagTime = System.currentTimeMillis() - startTime;
                            if (lagTime < 1000 / GUI.getFramesPerSecond()) {
                                Thread.sleep(1000 / GUI.getFramesPerSecond() - lagTime);
                            }
                        }
                        blockUntilDone.release();

                    } catch (InterruptedException e) {
                        return;
                    }
                }
            }
        });

        updateThread.start();

        //Create a thread that will periodically update the explorer's animation
        animationUpdateThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        long startTime = System.currentTimeMillis();
                        sprite.tick();
                        long lagTime = System.currentTimeMillis() - startTime;
                        if (lagTime < 1000 / ANIMATION_FPS) {
                            Thread.sleep((long) (1000 / ANIMATION_FPS - lagTime));
                        }
                    } catch (InterruptedException e) {
                        return;
                    }
                }
            }
        });

        animationUpdateThread.start();

    }

    /**
     * Return the image representing the current state of the explorer.
     */
    public BufferedImage sprite() {
        /* Use the direction to determine which offset into the
         * spritesheet to use. Class Sprite handles animation. */
        switch (dir) {
            case NORTH:
                return sprite.getSprite(0, 0);
            case SOUTH:
                return sprite.getSprite(0, 3);
            case WEST:
                return sprite.getSprite(1, 0);
            case EAST:
                return sprite.getSprite(1, 3);
            default:
                return sprite.getSprite(0, 0);
        }
    }

    /**
     * Return the explorer's row on the grid. Will remain the explorer's
     * old position until the explorer has completely arrived at the new one.
     */
    public int getRow() {
        return row;
    }

    /**
     * Return the explorer's column on the grid. Will remain the explorer's
     * old position until the explorer has completely arrived at the new one.
     */
    public int getCol() {
        return col;
    }

    /* Tell the explorer to move from its current location to dst.
     * After making move, calling thread will block until move completes on GUI.
     * Precondition: dst must be adjacent to the current location and not currently moving.
     * May throw an InterruptedException */
    public void moveTo(Node dst) throws InterruptedException {
        dir = getDirection(row, col, dst.getTile().getRow(), dst.getTile().getColumn());

        //Determine sequence of moves to add to queue to get to goal
        int xDiff = (dst.getTile().getColumn() - col) * MazePanel.TILE_WIDTH;
        int yDiff = (dst.getTile().getRow() - row) * MazePanel.TILE_HEIGHT;
        queuedMoves.put(new MovePair(xDiff, yDiff));

        blockUntilDone.acquire();
        row = dst.getTile().getRow();
        col = dst.getTile().getColumn();
    }

    /**
     * Draw the explorer on its own panel.
     */
    public void paintComponent(Graphics page) {
        super.paintComponent(page);
        page.drawImage(sprite(), posX, posY, MazePanel.TILE_WIDTH, MazePanel.TILE_HEIGHT, null);
    }

    /**
     * Update the location of the explorer as necessary.
     */
    private void update(int framesPerMove, int framesIntoMove, MovePair move) {
        //Make the move toward our destination
        posX = MazePanel.TILE_WIDTH * getCol() + (framesIntoMove * move.xDiff) / framesPerMove;
        posY = MazePanel.TILE_HEIGHT * getRow() + (framesIntoMove * move.yDiff) / framesPerMove;
        repaint();
    }

    /**
     * Return the the direction the current location (row, col) to (goalRow, goalCol).
     * If already there, return the current direction.
     */
    private Direction getDirection(int row, int col, int goalRow, int goalCol) {
        if (goalRow < row) return Direction.NORTH;
        if (goalRow > row) return Direction.SOUTH;
        if (goalCol < col) return Direction.WEST;
        if (goalCol > col) return Direction.EAST;
        return dir;
    }

    /**
     * Store information that uniquely represents a move we can make.
     */
    private class MovePair {
        final int xDiff;
        final int yDiff;

        /**
         * Constructor: an instance with change (Xdiff, yDiff).
         *
         * @param xChange The change in the x coordinate to make this move
         * @param yChange The change in the y coordinate to make this move
         */
        public MovePair(int xChange, int yChange) {
            xDiff = xChange;
            yDiff = yChange;
        }
    }
}

package game;

public class Tile {
    /**
     * The row and column position of the GameNode
     */
    private final int row;
    private final int col;
    /**
     * Amount of gold on this Node
     */
    private final int gold;
    /**
     * The Type of Tile of this Node
     */
    private Type type;
    private boolean goldPickedUp;

    /**
     * Constructor: a new Tile at (r, c), with g amount of gold, and Type t.
     */
    public Tile(int r, int c, int g, Type t) {
        row = r;
        col = c;
        gold = g;
        type = t;
        goldPickedUp = false;
    }

    /**
     * Return the amount of gold on this Tile.
     */
    public int getGold() {
        return (goldPickedUp ? 0 : gold);
    }

    /**
     * Return the original amount of gold on this tile (even if picked up.)
     */
    public int getOriginalGold() {
        return gold;
    }

    /**
     * Return the row of this Tile.
     */
    public int getRow() {
        return row;
    }

    /**
     * Return the column of this Tile.
     */
    public int getColumn() {
        return col;
    }

    /**
     * Returns the Type of this Tile.
     */
    public Type getType() {
        return type;
    }

    /**
     * Set the Type of this Tile to t.
     */
    /* package */ void setType(Type t) {
        type = t;
    }

    /**
     * Set the gold on this Node to 0 and returns the amount "picked up".
     */
    public int takeGold() {
        final int result = getGold();
        goldPickedUp = true;
        return result;
    }

    /**
     * An enum representing the different types of Tiles that may appear in a cavern.
     */
    public enum Type {
        FLOOR, ORB, ENTRANCE,
        WALL {
            @Override
            public boolean isOpen() {
                return false;
            }
        };

        /**
         * Return true if this Type of Tile is traversable.
         */
        public boolean isOpen() {
            return true;
        }
    }
}

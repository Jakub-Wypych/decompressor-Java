package decompressor.Dictionary;

/* Stores the x position for Node
 */
public abstract class NodePos {
    protected static int global_x = 0;
    private final int x;

    public NodePos() {
        x = global_x;
        increment_x();
    }

    private void increment_x() {
        global_x++;
    }

    public int getX() {
        return x;
    }
}

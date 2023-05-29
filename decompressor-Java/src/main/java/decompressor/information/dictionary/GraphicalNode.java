package decompressor.information.dictionary;

/**
 * Used for setting and getting coordinates for nodes
 */
public interface GraphicalNode {

    Position getPos();

    /**
     * Set position
     * @param OffsetRight how far to right is the node
     * @param depth how low in the tree is the node
     */
    void setPos(double OffsetRight, int depth);
}

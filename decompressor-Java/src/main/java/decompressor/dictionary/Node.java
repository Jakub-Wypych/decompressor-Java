package decompressor.dictionary;

/**
 * Interface used for Tree class
 */
public interface Node extends GraphicalNode {
    /**
     * @return current nodes probability
     */
    int value();

    /**
     * @return left branch node
     */
    Object left();

    /**
     * @return right branch node
     */
    Object right();
}

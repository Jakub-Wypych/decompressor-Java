package decompressor.information.dictionary;

/**
 * Stores the position of the node
 * @param offsetRight how far to right is the node in the tree
 * @param depth how low in the tree is the node
 */
public record Position(double offsetRight, int depth) {

    @Override
    public String toString() {
        return "Offset to right: " + offsetRight + "; Depth: " + depth;
    }
}

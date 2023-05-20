package decompressor.Dictionary;

/* A blank node in a tree,
i.e. it doesn't have a symbol and has branches
 */
public record BlankNode(Object left, Object right, int value, double x) implements Node {

    @Override
    public String toString() {
        return "Node: left = " + left + "; right = " + right + "; value = " + value + "; x = " + x;
    }
}

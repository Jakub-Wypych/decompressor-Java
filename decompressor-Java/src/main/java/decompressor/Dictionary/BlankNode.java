package decompressor.Dictionary;

public class BlankNode implements Node {
    private Object left;
    private Object right;
    private int value;

    public BlankNode(Object left, Object right, int value) {
        this.left = left;
        this.right = right;
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public Object left() {
        return left;
    }

    @Override
    public Object right() {
        return right;
    }

    @Override
    public String toString() {
        return "Node: left = " + left + "; right = " + right + "; value = " + value;
    }
}

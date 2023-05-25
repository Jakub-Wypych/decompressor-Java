package graphical.decompressorjava;

import decompressor.dictionary.Node;

public class TreeDrawer {

    private final Object root;
    private int offsetRight;

    public TreeDrawer(Object root) {
        this.root = root;
        offsetRight = 0;
    }

    public void setUp() {
        check((Node) root, 0);
    }

    private void check(Node node, int depth) {
        if (node.left() == null && node.right() == null) {
            node.setPos(++offsetRight, depth);
            return;
        }
        if (node.left() != null)
            check((Node) node.left(), depth-1);
        if (node.right() != null)
            check((Node) node.right(), depth-1);
    }
}

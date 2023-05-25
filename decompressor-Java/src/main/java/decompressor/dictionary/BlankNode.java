package decompressor.dictionary;

/* A blank node in a tree,
i.e. it doesn't have a symbol and has branches
 */
public record BlankNode(Object left, Object right, int value) implements Node {
    @Override
    public String toString() {
        return "Node: left = " + left + "; right = " + right + "; value = " + value;
    }

    @Override
    public Position getPos() {
        Position leftPos = ((Node) left).getPos();
        Position rightPos = ((Node) right).getPos();
        return new Position((leftPos.offsetRight()+ rightPos.offsetRight())/2, leftPos.depth()+1);
    }

    @Override
    public void setPos(double offsetRight, int depth) {
        throw new RuntimeException(new IllegalArgumentException("ERROR: You cannot set Pos for Blank Nodes!"));
    }
}

package decompressor.dictionary;

import java.util.ArrayList;

/* Stores the raw dictionary
 */
public final class RawDictionary implements Node {
    private final ArrayList<Byte> symbol;
    private final int probability;
    private Position position;

    public RawDictionary(ArrayList<Byte> symbol, int probability) {
        this.symbol = symbol;
        this.probability = probability;
        position = null;
    }

    @Override
    public String toString() {
        return "RawDictionary[" +
                "symbol=" + symbol + ", " +
                "probability=" + probability + ']';
    }

    @Override
    public int value() {
        return probability;
    }

    @Override
    public Object left() {
        return null; // it's a "leaf" so no more branches
    }

    @Override
    public Object right() {
        return null; // it's a "leaf" so no more branches
    }

    @Override
    public Position getPos() {
        return position;
    }

    @Override
    public void setPos(double offsetRight, int depth) {
        position = new Position(offsetRight, depth);
    }

    public ArrayList<Byte> symbol() {
        return symbol;
    }
}

package decompressor.Dictionary;

import java.util.ArrayList;

/* Stores the raw dictionary
 */
public final class RawDictionary extends NodePos implements Node {
    private final ArrayList<Byte> symbol;
    private final int probability;

    public RawDictionary(ArrayList<Byte> symbol, int probability) {
        this.symbol = symbol;
        this.probability = probability;
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
    public double x() {
        return getX();
    }

    public ArrayList<Byte> symbol() {
        return symbol;
    }

    public int probability() {
        return probability;
    }
}

package decompressor.Dictionary;

import java.util.ArrayList;

public final class RawDictionary implements Node {
    private final ArrayList<Byte> symbol;
    private final int probability;

    public RawDictionary(ArrayList<Byte> symbol, int probability) {
        this.symbol = symbol;
        this.probability = probability;
    }

    public ArrayList<Byte> symbol() {
        return symbol;
    }

    public int probability() {
        return probability;
    }

    @Override
    public String toString() {
        return "RawDictionary[" +
                "symbol=" + symbol + ", " +
                "probability=" + probability + ']';
    }

    @Override
    public int getValue() {
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
}

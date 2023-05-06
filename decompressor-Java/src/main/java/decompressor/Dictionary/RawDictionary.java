package decompressor.Dictionary;

import java.util.ArrayList;

/* Stores the raw dictionary
 */
public record RawDictionary(ArrayList<Byte> symbol, int probability) implements Node {

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
}

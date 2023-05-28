package decompressor.dictionary;

import java.util.ArrayList;

import static java.lang.Math.pow;

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

    public String toStringBinaryToText() {
        return "RawDictionary[" +
                "symbol=" + symbolBinaryToText() + ", " +
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

    public String symbolBinaryToText() {
        ArrayList<Byte> binary = new ArrayList<>(symbol);
        StringBuilder binaryInText = new StringBuilder();
        while (binary.size()/8 >= 1) {
            byte character = 0;
            for (int i = 7; i >= 0; i--) {
                if (binary.get((0)) == 1) {
                    character += pow(2, i);
                }
                binary.remove(0);
            }
            binaryInText.append((char) character);
        }
        if (!binary.isEmpty())
            binaryInText.append(" ").append(binary);
        return binaryInText.toString();
    }
}

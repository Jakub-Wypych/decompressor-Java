package decompressor.Dictionary;

import java.util.ArrayList;

public record RawDictionary(ArrayList<Byte> symbol, int probability) {
}

package decompressor.Dictionary;

import decompressor.Bitwork.Bitread;

import java.util.ArrayList;

/* Read the raw dictionary from the file,
which later will be used to make a tree
*/
public class ReadRawDictionary {
    public static ArrayList<RawDictionary> read(Bitread bitread, byte bit) {
        ArrayList<RawDictionary> rawDictionaries = new ArrayList<>();
        rawDictionaries.add(new RawDictionary(bitread.readNbits(bit), 4));
        return rawDictionaries;
    }
}

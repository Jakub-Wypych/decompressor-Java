package decompressor;

import decompressor.bitwork.Bitread;
import decompressor.bitwork.Bitwrite;
import decompressor.dictionary.Tree;
import decompressor.dictionary.Node;
import decompressor.dictionary.RawDictionary;

import java.util.ArrayList;

/* The 'deciphering' part of decompression,
will use the dictionary tree to decipher
*/
public class Decipher {
    public static void decipher(Bitread bitread, Bitwrite bitwrite, Tree root) {
        Byte bit = readbit(bitread);
        Node current_node = (Node) root.getRoot();
        while (bit != null) {
            if(current_node.right() == null || current_node.left() == null) { // we've reached a leaf
                RawDictionary rd = (RawDictionary) current_node; // it's a leaf so => it's a RawDictionary
                current_node = (Node) root.getRoot();
                bitwrite.writebits(rd.symbol());
            }
            if(bit == 1) { // right branch
                current_node = (Node) current_node.right();
            } else if (bit == 0) { // left branch
                current_node = (Node) current_node.left();
            }
            bit = readbit(bitread);
        }
    }

    private static Byte readbit(Bitread bitread) {
        ArrayList<Byte> read_bits_array = bitread.readNbits(1);
        if(read_bits_array.size() != 1)
            return null;
        else
            return read_bits_array.get(0);
    }
}

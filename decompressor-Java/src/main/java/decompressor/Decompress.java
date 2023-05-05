package decompressor;

import decompressor.Bitwork.Bitread;
import decompressor.Dictionary.MakeTree;
import decompressor.Dictionary.Node;
import decompressor.Dictionary.RawDictionary;
import decompressor.Dictionary.ReadRawDictionary;

import java.util.ArrayList;

/* Used for decompressing files,
takes care of communication between classes */
public class Decompress {
    /* TODO
    *   Bitwrite - needs to be able to write N bits
    *   Decipher - does the "deciphering" part of the decompression
    *   Graphical interface */
    public static void decompress(String filepath, String raw_password) {
        Password password = new Password(raw_password);
        Bitread bitread = new Bitread(filepath, password.getPassword()); // setting up bitread (also reading raw ident at the same time)
        Ident ident = new Ident(bitread.readNbits(8));
        ident.check(password.getPassword());

        System.out.println(ident); // DEBUG

        ArrayList<Object> rawDictionaries = ReadRawDictionary.read(bitread, ident.bit_read());

        for (Object o: rawDictionaries) { // DEBUG
            RawDictionary rd = (RawDictionary)o;
            System.out.println(rd.symbol() + " " + rd.probability());
        }

        MakeTree dictionary = new MakeTree(rawDictionaries);

        Node root = (Node) dictionary.getRoot(); // DEBUG
        root = (Node) root.right();
        if(root.right() == null) { // we've reached the leaf
            RawDictionary rd = (RawDictionary) root;
            int char_value = 0;
            if(rd.symbol().get(0) == 1)
                char_value += 128;
            if(rd.symbol().get(1) == 1)
                char_value += 64;
            if(rd.symbol().get(2) == 1)
                char_value += 32;
            if(rd.symbol().get(3) == 1)
                char_value += 16;
            if(rd.symbol().get(4) == 1)
                char_value += 8;
            if(rd.symbol().get(5) == 1)
                char_value += 4;
            if(rd.symbol().get(6) == 1)
                char_value += 2;
            if(rd.symbol().get(7) == 1)
                char_value += 1;
            System.out.println((char) char_value);
        }
    }
}

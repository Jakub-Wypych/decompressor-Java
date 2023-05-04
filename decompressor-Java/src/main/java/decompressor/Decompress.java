package decompressor;

import decompressor.Bitwork.Bitread;
import decompressor.Dictionary.RawDictionary;
import decompressor.Dictionary.ReadRawDictionary;

import java.util.ArrayList;

/* Used for decompressing files,
takes care of communication between classes */
public class Decompress {
    /* TODO
    *   Bitwrite - needs to be able to write N bits
    *   MakeTree - creates a tree from raw dictionary
    *   Dictionary - stores the tree and allow movement throughout the tree
    *   Decipher - does the "deciphering" part of the decompression
    *   Graphical interface */
    public static void decompress(String filepath, String raw_password) {
        Password password = new Password(raw_password);
        Bitread bitread = new Bitread(filepath, password.getPassword()); // setting up bitread (also reading raw ident at the same time)
        Ident ident = new Ident(bitread.readNbits(8));
        ident.check(password.getPassword());
        System.out.println(ident);
        ArrayList<RawDictionary> rawDictionaries = ReadRawDictionary.read(bitread, ident.bit_read());
        for (RawDictionary rd: rawDictionaries) {
            System.out.println(rd.symbol() + " " + rd.probability());
        }
    }
}

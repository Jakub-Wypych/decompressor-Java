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
    *   Bitread - add readNbits with opt for return as value
    *   ReadRawDictionary - (needs Bitwork) reads the raw dictionary from infile
    *   RawDictionary - stores the raw dictionary
    *   MakeTree - creates a tree from raw dictionary
    *   Dictionary - stores the tree and allow movement throughout the tree
    *   Decipher - does the "deciphering" part of the decompression
    *   Graphical interface */
    public static void decompress(String filepath, String raw_password) {
        Bitread bitread = new Bitread(filepath);
        Ident ident = new Ident(bitread.readNbits(8));
        Password password = new Password(raw_password);
        ident.check(password.getPassword());
        System.out.println(ident);
        ArrayList<RawDictionary> rawDictionaries = ReadRawDictionary.read(bitread, ident.bit_read());
        System.out.print(rawDictionaries.get(0).symbol() + " " + rawDictionaries.get(0).probability());
    }
}

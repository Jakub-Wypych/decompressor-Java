package decompressor;

import decompressor.Bitwork.Bitread;
import decompressor.Bitwork.Bitwrite;
import decompressor.Dictionary.Tree;
import decompressor.Dictionary.RawDictionary;
import decompressor.Dictionary.ReadRawDictionary;

import java.util.ArrayList;

/* Used for decompressing files,
takes care of communication between classes */
public class Decompress {
    /* TODO
    *   Bitwrite - needs to be able to write bits
    *   Truncate - cuts off the stray bytes
    *   Graphical interface
    *   Outfilepath - needs to be read */
    public static void decompress(String infilepath, String outfilepath, String raw_password) {
        Password password = new Password(raw_password);
        Bitread bitread = new Bitread(infilepath, password.getPassword()); // setting up bitread (also reading raw ident at the same time)
        Bitwrite bitwrite = new Bitwrite(outfilepath); // setting up bitwrite
        Ident ident = new Ident(bitread.readNbits(8));
        ident.check(password.getPassword());

        System.out.println(ident); // DEBUG

        ArrayList<Object> rawDictionaries = ReadRawDictionary.read(bitread, ident.bit_read());

        for (Object o: rawDictionaries) { // DEBUG
            RawDictionary rd = (RawDictionary)o;
            System.out.println(rd.symbol() + " " + rd.probability());
        }

        Tree dictionary = new Tree(rawDictionaries);
        Decipher.decipher(bitread, bitwrite, dictionary);
        Turncate.cut(outfilepath, dictionary, ident.stray_bits());
    }
}

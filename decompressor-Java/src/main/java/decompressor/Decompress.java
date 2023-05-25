package decompressor;

import decompressor.bitwork.Bitread;
import decompressor.bitwork.Bitwrite;
import decompressor.bitwork.FileIsEmpty;
import decompressor.dictionary.Tree;
import decompressor.dictionary.RawDictionary;
import decompressor.dictionary.ReadRawDictionary;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/* Used for decompressing files,
takes care of communication between classes */
public class Decompress {
    public static Results decompress(String infilepath, String outfilepath, String raw_password) {
        Password password = new Password(raw_password);
        Bitread bitread; // setting up bitread (also reading raw ident at the same time)
        try {
            bitread = new Bitread(infilepath, password.getPassword());
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: Couldn't find file: " + infilepath);
            throw new RuntimeException(e);
        } catch (FileIsEmpty e) {
            System.out.println("ERROR: File is empty!");
            throw new RuntimeException(e);
        }
        Ident ident = new Ident(bitread.readNbits(8));
        ident.check(password.getPassword());
        ArrayList<Object> rawDictionaries = ReadRawDictionary.read(bitread, ident.bit_read());
        ArrayList<RawDictionary> copyRawDictionaries = new ArrayList<>();

        System.out.println(ident); // DEBUG print ident
        for (Object o: rawDictionaries) {
            RawDictionary rd = (RawDictionary)o;
            copyRawDictionaries.add(rd);
            System.out.println(rd.symbol() + " " + rd.value()); // DEBUG print dictionary
        }

        Tree dictionary = new Tree(rawDictionaries);
        Bitwrite bitwrite = new Bitwrite(outfilepath); // setting up bitwrite
        Decipher.decipher(bitread, bitwrite, dictionary);
        if(bitwrite.getBufferSize() != 0)
            throw new RuntimeException(new Exception("ERROR: Failure in deciphering!")); // should never happen, but you never know
        Turncate.cut(outfilepath, dictionary, ident.stray_bits());
        bitwrite.close();
        bitread.close();
        return new Results(ident, copyRawDictionaries, dictionary);
    }
}

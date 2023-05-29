package decompressor;

import decompressor.bitwork.Bitread;
import decompressor.bitwork.Bitwrite;
import decompressor.deciphering.Decipher;
import decompressor.deciphering.Truncate;
import decompressor.exceptions.FileIsDamaged;
import decompressor.exceptions.FileIsEmpty;
import decompressor.information.Ident;
import decompressor.information.Password;
import decompressor.information.Results;
import decompressor.information.dictionary.Tree;
import decompressor.information.dictionary.RawDictionary;
import decompressor.information.dictionary.ReadRawDictionary;
import decompressor.exceptions.WrongPassword;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Used for decompressing files,
 * takes care of communication between classes
 */
public class Decompress {

    /**
     * Begins the deciphering process
     * @param infilepath the compressed file
     * @param outfilepath the decompressed file
     * @param raw_password password in raw form i.e. it's a string
     * @return results of deciphering
     * @throws WrongPassword throws if raw_password is null and FileIsDamaged was thrown
     * @throws FileIsDamaged failure in deciphering process
     */
    public static Results decompress(String infilepath, String outfilepath, String raw_password) throws WrongPassword, FileIsDamaged {
        if (infilepath.equals(outfilepath))
            throw new RuntimeException(new Exception("ERROR: Input file path equals output file path!"));
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
        ArrayList<Object> rawDictionaries;
        try {
            rawDictionaries = ReadRawDictionary.read(bitread, ident.bit_read());
        } catch (FileIsDamaged e) {
            if (raw_password == null)
                throw e;
            else throw new WrongPassword("ERROR: Wrong password!");
        }
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
        try {
            Truncate.cut(outfilepath, dictionary, ident.stray_bits());
        } catch (FileIsDamaged e) {
            if (raw_password == null)
                throw e;
            else throw new WrongPassword("ERROR: Wrong password!");
        }
        bitwrite.close();
        bitread.close();
        return new Results(ident, copyRawDictionaries, dictionary);
    }
}

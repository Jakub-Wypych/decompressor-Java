package decompressor;

import decompressor.Fileoperators.CompressedFile;
import decompressor.Ident.*; // package that deals with identifier
import decompressor.Password.Password;
import decompressor.Password.ReadPassword;

/* Used for decompressing files,
takes care of communication between classes */
public class Decompress {
    /* TODO
    *   Bitwork - needs to be able to read N bits and write N bits
    *   ReadRawDictionary - (needs Bitwork) reads the raw dictionary from infile
    *   RawDictionary - stores the raw dictionary
    *   MakeTree - creates a tree from raw dictionary
    *   Dictionary - stores the tree and allow movement throughout the tree
    *   Decipher - does the "deciphering" part of the decompression
    *   Graphical interface */
    public static void decompress(String filepath, String raw_password) {
        CompressedFile compressedFile = new CompressedFile(filepath);
        Ident ident = ReadIdent.read(compressedFile);
        Password password = ReadPassword.makepassword(raw_password);
        CheckIdent.check(ident, password.password());
        System.out.println(ident);
    }
}

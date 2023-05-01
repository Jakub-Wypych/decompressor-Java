package decompressor;

import decompressor.Ident.*; // package that deals with identifier

/* Used for decompressing files,
takes care of communication between classes */
public class Decompress {
    // Bitwork - used for reading/writing bits
    // THE BEGINNING OF DECOMPRESSION
    // OpenCompressedFile - open the compressed file (done)
    // ReadIdent - get information from identifier (done)
    // ReadPassword - reads password given by user, if no password given set it to 0x00
    // CheckIdent - analyze the info from ident and act accordingly (used for checking for errors) (done)
    // ReadRawDictionary - read RawDictionary
    // MakeTree - makes a 'tree' from RawDictionary called Dictionary (I think such functions in java library already exist)
    // Decipher - the 'deciphering' part of decompression
    public void decompress(String filepath) {
        byte password = 0x00;
        CompressedFile compressedFile = new CompressedFile(filepath);
        Ident ident = ReadIdent.read(compressedFile);
        CheckIdent.check(ident, password);
        System.out.println(ident);
    }
}

package decompressor;

import decompressor.Ident.*; // package that deals with identifier

public class Decompress {
    /* Used for decompressing files,
    takes care of communication between classes
     */
    // Bitwork - used for reading/writing bits
    // THE BEGINNING OF DECOMPRESSION
    // OpenFile - open the compressed file
    // ReadIdent - get information from identifier
    /* CheckIdent - analyze the info from ident and act accordingly (used for checking for errors):
    if password protected - ReadPassword (no password throw exception),
    if not compressed - throw exception
     */
    // ReadRawDictionary - read RawDictionary
    // MakeTree - makes a 'tree' from RawDictionary called Dictionary (I think such functions in java library already exist)
    // Decipher - the 'deciphering' part of decompression
}

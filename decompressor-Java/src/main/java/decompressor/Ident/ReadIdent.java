package decompressor.Ident;

import decompressor.CompressedFile;

/* Reads raw Ident and extracts information out of it,
store the info in record Ident and returns it */
public class ReadIdent {
    public static Ident read(CompressedFile compressedFile) {
        byte readbyte = compressedFile.ReadByte();
        boolean compressed = (readbyte&0x01) != 0;
        boolean password = (readbyte&0x08) != 0;
        byte bit_read = (readbyte&0x06) == 6 ? (byte) 16 : (readbyte&0x06) == 2 ? (byte) 12 : (byte) 8;
        byte stray_bits = (byte) ((readbyte&0xE0)>>5);
        return new Ident(compressed, password, bit_read, stray_bits);
    }
}

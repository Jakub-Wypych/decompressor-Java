package decompressor.Ident;

public class Ident {
    /* Stores information included in the identifier
     */
    private boolean compressed;
    private boolean password;
    private byte bit_read; // 8, 12 or 16 bit reading
    private byte stray_bits; // number of stray bits [0;7]
}

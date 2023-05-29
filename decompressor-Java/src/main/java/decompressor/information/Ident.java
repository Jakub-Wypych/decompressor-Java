package decompressor.information;

import java.util.ArrayList;

/**
 * Reads raw Ident,
 * stores information included in the identifier,
 * also analyzes its contents (mainly password and compressed)
 */
public final class Ident {
    private final boolean compressed;
    private final boolean password;
    private final byte bit_read;
    private final byte stray_bits;
    private final ArrayList<Byte> raw_ident;

    /**
     * Reads raw_ident and stores its it information
     * @param raw_ident identifier in bit form
     */
    public Ident(ArrayList<Byte> raw_ident) {
        this.raw_ident = raw_ident;
        this.compressed = raw_ident.get(7) != 0;
        this.password = raw_ident.get(4) != 0;
        if(raw_ident.get(6) == 0 )
            this.bit_read = (byte) 8;
        else if (raw_ident.get(5) == 0) {
            this.bit_read = (byte) 12;
        } else
            this.bit_read = (byte) 16;
        byte stray_bits = 0;
        if(raw_ident.get(0) == 1)
            stray_bits += 4;
        if(raw_ident.get(1) == 1)
            stray_bits += 2;
        if(raw_ident.get(2) == 1)
            stray_bits += 1;
        this.stray_bits = stray_bits;
    }

    /**
     * Checks if identifier has any issues
     * @param password password
     */
    public void check(byte password) {
        if(!compressed) {
            throw new RuntimeException(new Exception("ERROR: Given file is not compressed!"));
        }
        if(this.password && password == 0x00) {
            throw new RuntimeException(new Exception("ERROR: No password given for password protected file!"));
        }
        if(!this.password && password != 0x00) {
            throw new RuntimeException(new Exception("ERROR: Password was given for unprotected file!"));
        }
    }

    @Override
    public String toString() {
        return  "IDENTIFIER\n" +
                "  " + raw_ident.get(0) + "\n" +
                "  " + raw_ident.get(1) + "\n" +
                "  " + raw_ident.get(2) + " - stray bits = " + stray_bits + "\n" +
                "  " + raw_ident.get(3) + " - means nothing\n" +
                "  " + raw_ident.get(4) + " - password = " + password + "\n" +
                "  " + raw_ident.get(5) + "\n" +
                "  " + raw_ident.get(6) + " - bit_read = " + bit_read + "\n" +
                "  " + raw_ident.get(7) + " - compressed = " + compressed;
    }

    public boolean isPassword() { return password; }

    public boolean isCompressed() { return compressed; }

    public byte bit_read() {
        return bit_read;
    }

    public byte stray_bits() { return stray_bits; }
}

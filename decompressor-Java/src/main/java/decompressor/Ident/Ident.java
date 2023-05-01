package decompressor.Ident;

/**
 * @param compressed is file compressed
 * @param password   is file password protected
 * @param bit_read   8, 12 or 16 bit reading
 * @param stray_bits number of stray bits [0;7]
 */ // Stores information included in the identifier
public record Ident(boolean compressed, boolean password, byte bit_read, byte stray_bits) {

    @Override
    public String toString() {
        return "Stray bits = " + stray_bits + "; password = " + password + "; bit_read = " + bit_read + "; compressed = " + compressed;
    }
}

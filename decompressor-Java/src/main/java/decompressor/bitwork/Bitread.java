package decompressor.bitwork;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Opens file for reading per N bits
 */
public class Bitread {
    private final FileInputStream infile;
    private final byte password;
    private Byte read_byte;
    private int byte_pos;

    /**
     * Sets up bitread,
     * automatically reads the first byte without password
     * @param filepath the read file
     * @param password password with which he filepath is protected with
     * @throws FileNotFoundException given file doesn't exist
     * @throws FileIsEmpty given file is empty
     */
    public Bitread(String filepath, byte password) throws FileNotFoundException, FileIsEmpty {
        if(filepath == null) {
            throw new RuntimeException(new Exception("ERROR: No file given!"));
        }
        infile = new FileInputStream(filepath);
        read_byte = readByte();
        if(read_byte == null)
            throw new FileIsEmpty("ERROR: File is empty!");
        byte_pos = 0;
        this.password = password;
    }

    /**
     * Used by {@link #readNbits(int) readNbits} to read file
     * @return read byte from file
     */
    private Byte readByte() {
        try {
            byte[] byte_array = infile.readNBytes(1);
            if(byte_array.length == 0) // can't read next byte, return null
                return null;
            return byte_array[0];
        } catch (IOException e) {
            System.out.println("ERROR: Can't read infile!");
            throw new RuntimeException(e);
        }
    }

    /**
     * Reads file per N bits
     * @param N how many bits to read
     * @return arraylist of 1s and 0s
     */
    public ArrayList<Byte> readNbits(int N) {
        ArrayList<Byte> bits = new ArrayList<>();
        for(int i=0; i<N; i++) {
            if(byte_pos > 7) {
                read_byte = readByte();
                if(read_byte == null)
                    return bits;
                byte_pos = 0;
                read_byte = (byte) (read_byte^password);
            }
            if(((read_byte<<byte_pos)&0x80) != 0)
                bits.add((byte) 1);
            else
                bits.add((byte) 0);
            byte_pos++;
        }
        return bits;
    }

    /**
     * Closes its file
     */
    public void close() {
        try {
            infile.close();
        } catch (IOException e) {
            System.out.println("ERROR: Failure to close the infile!");
            throw new RuntimeException(e);
        }
    }
}
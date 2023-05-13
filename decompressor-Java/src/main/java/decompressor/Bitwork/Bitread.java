package decompressor.Bitwork;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/* Opens compressed file for reading
 */
public class Bitread {
    private final FileInputStream infile;
    private final byte password;
    private Byte read_byte;
    private int byte_pos;

    public Bitread(String filepath, byte password) throws FileNotFoundException, FileIsEmpty {
        if(filepath == null) {
            throw new RuntimeException(new Exception("ERROR: No file given!"));
        }
        infile = new FileInputStream(filepath);
        read_byte = readByte(); // REMEMBER: FIRST BYTE ISN'T READ WITH PASSWORD!!!
        if(read_byte == null)
            throw new FileIsEmpty("ERROR: File is empty!");
        byte_pos = 0;
        this.password = password;
    }

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

    public void close() {
        try {
            infile.close();
        } catch (IOException e) {
            System.out.println("ERROR: Failure to close the infile!");
            throw new RuntimeException(e);
        }
    }
}
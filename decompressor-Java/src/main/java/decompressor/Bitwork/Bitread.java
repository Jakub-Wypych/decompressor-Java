package decompressor.Bitwork;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/* Opens compressed file for reading */
public class Bitread {
    private final FileInputStream infile;
    private Byte read_byte;
    private int byte_pos;

    public Bitread(String filepath) {
        if(filepath == null) {
            throw new RuntimeException(new Exception("ERROR: No file given!"));
        }
        try {
            infile = new FileInputStream(filepath);
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: Couldn't find file: " + filepath);
            throw new RuntimeException(e);
        }
        read_byte = readByte();
        if(read_byte == null)
            throw new RuntimeException(new Exception("ERROR: File is empty!"));
        byte_pos = 0;
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

    public ArrayList<Byte> readNbits(int length) {
        ArrayList<Byte> bits = new ArrayList<>();
        for(int i=0; i<length; i++) {
            if(byte_pos > 7) {
                read_byte = readByte();
                if(read_byte == null)
                    return bits;
                byte_pos = 0;
            }
            if(((read_byte<<byte_pos)&0x80) != 0)
                bits.add((byte) 1);
            else
                bits.add((byte) 0);
            byte_pos++;
        }
        return bits;
    }
}

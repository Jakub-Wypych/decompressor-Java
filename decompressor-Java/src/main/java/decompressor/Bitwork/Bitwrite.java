package decompressor.Bitwork;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

/* Writes bits into file from given Arraylist of bits
 */
public class Bitwrite {
    private final FileOutputStream outfile;
    private ArrayList<Byte> buffer; // Byte to be written

    public Bitwrite(String filepath) {
        if(filepath == null) { // write into default file
            filepath = "decompressed.bin";
        }
        try {
            outfile = new FileOutputStream(filepath);
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: Couldn't open file: " + filepath);
            throw new RuntimeException(e);
        }
    }
    public void writebits(ArrayList<Byte> bits) {
        int char_value = 0;
        if(bits.get(0) == 1)
            char_value += 128;
        if(bits.get(1) == 1)
            char_value += 64;
        if(bits.get(2) == 1)
            char_value += 32;
        if(bits.get(3) == 1)
            char_value += 16;
        if(bits.get(4) == 1)
            char_value += 8;
        if(bits.get(5) == 1)
            char_value += 4;
        if(bits.get(6) == 1)
            char_value += 2;
        if(bits.get(7) == 1)
            char_value += 1;
        System.out.print((char) char_value);
    }
}

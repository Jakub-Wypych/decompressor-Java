package decompressor.bitwork;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import static java.lang.Math.pow;

/**
 * Writes bits into file from given Arraylist of bits
 */
public class Bitwrite {
    private final FileOutputStream outfile;
    private final ArrayList<Byte> buffer; // Byte to be written

    public Bitwrite(String filepath) {
        try {
            outfile = new FileOutputStream(filepath);
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: Couldn't open file: " + filepath);
            throw new RuntimeException(e);
        }
        buffer = new ArrayList<>();
    }

    /**
     * Writes as many bits it can into the file,
     * the rest it stores for later
     * @param bits the arraylist of bits
     */
    public void writebits(ArrayList<Byte> bits) {
        for (Byte bit : bits) {
            if (buffer.size() == 8)
                writebyte();
            buffer.add(bit);
        }
        if (buffer.size() == 8)
            writebyte();
    }

    /**
     * Used by {@link #writebits(ArrayList) writebits} to write into file
     */
    private void writebyte() {
        int char_value = 0;
        for (int i = 7; i >= 0 ; i--) {
            if (buffer.get(7-i) == 1)
                char_value += pow(2, i);
        }
        try {
            outfile.write(char_value);
        } catch (IOException e) {
            System.out.println("ERROR: Couldn't write into outfile!");
            throw new RuntimeException(e);
        }
        buffer.clear();
    }

    /**
     * @return how many bits have not been written
     */
    public int getBufferSize() {
        return buffer.size();
    }

    /**
     * Closes the file
     */
    public void close() {
        try {
            outfile.close();
        } catch (IOException e) {
            System.out.println("ERROR: Failure in closing the outfile!");
            throw new RuntimeException(e);
        }
    }
}

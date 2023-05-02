package decompressor.Fileoperators;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/* Opens compressed file for reading */
public class CompressedFile {
    private final FileInputStream infile;

    public CompressedFile(String filepath) {
        if(filepath == null) {
            throw new RuntimeException(new Exception("ERROR: No file given!"));
        }
        try {
            infile = new FileInputStream(filepath);
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: Couldn't find file: " + filepath);
            throw new RuntimeException(e);
        }
    }

    public Byte ReadByte() {
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
}

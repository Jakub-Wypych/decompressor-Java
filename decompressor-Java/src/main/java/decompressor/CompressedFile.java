package decompressor;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/* Opens compressed file for reading */
public class CompressedFile {
    private final FileInputStream infile;

    public CompressedFile(String filepath) {
        try {
            infile = new FileInputStream(filepath);
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: Couldn't find file: " + filepath);
            throw new RuntimeException(e);
        }
    }

    public byte ReadByte() {
        try {
            return infile.readNBytes(1)[0];
        } catch (IOException e) {
            System.out.println("ERROR: Can't read infile!");
            throw new RuntimeException(e);
        }
    }
}

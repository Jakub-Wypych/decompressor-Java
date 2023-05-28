package decompressor.bitwork;

/**
 * An exception for if the file is empty
 */
public class FileIsEmpty extends Exception {
    public FileIsEmpty(String errorMessage) {
        super(errorMessage);
    }
}
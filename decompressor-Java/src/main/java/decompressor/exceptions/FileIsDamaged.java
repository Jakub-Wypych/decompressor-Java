package decompressor.exceptions;

/**
 * An exception for if the file is damaged
 */
public class FileIsDamaged extends Exception {
    public FileIsDamaged(String errorMessage) {
        super(errorMessage);
    }
}
package decompressor.exceptions;

/**
 * An exception for if the given password is wrong
 */
public class WrongPassword extends Exception {
    public WrongPassword(String errorMessage) {
        super(errorMessage);
    }
}
package decompressor.bitwork;

public class FileIsEmpty extends Exception {
    public FileIsEmpty(String errorMessage) {
        super(errorMessage);
    }
}
package decompressor;

/* Main Class, only used to run Decompress and handle options */
public class Main {
    public static void main(String[] args) {
        Decompress decompress = new Decompress();
        decompress.decompress(args[0]);
    }
}

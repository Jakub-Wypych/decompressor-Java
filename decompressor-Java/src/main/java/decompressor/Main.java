package decompressor;

/**
 * Main Class, only used to run Decompress and handle options:
 * -i : input file path
 * -o : output file path
 * -p : password
 */
public class Main {
    public static void main(String[] args) {
        if(args.length != 2 && args.length != 4 && args.length != 6) {
            throw new RuntimeException(new Exception("ERROR: Insufficient amount of arguments given!"));
        }
        String infilepath = null;
        String outfilepath = "decompressed.bin"; // default option
        String password = null;
        for (int i=0; i<args.length;i++) {
            try {
                switch (args[i]) {
                    case "-i" -> infilepath = args[i + 1];
                    case "-o" -> outfilepath = args[i + 1];
                    case "-p" -> password = args[i + 1];
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new RuntimeException(e);
            }
        }
        Decompress.decompress(infilepath, outfilepath, password);
    }
}

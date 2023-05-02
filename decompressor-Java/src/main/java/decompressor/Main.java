package decompressor;

/* Main Class, only used to run Decompress and handle options */
public class Main {
    public static void main(String[] args) {
        if(args.length != 2 && args.length != 4) {
            throw new RuntimeException(new Exception("ERROR: Insufficient amount of arguments given!"));
        }
        String filepath = null;
        String password = null;
        for (int i=0; i<args.length;i++) {
            try {
                if(args[i].equals("-i"))
                    filepath = args[i+1];
                if (args[i].equals("-p"))
                    password = args[i+1];
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new RuntimeException(e);
            }
        }
        Decompress.decompress(filepath, password);
    }
}

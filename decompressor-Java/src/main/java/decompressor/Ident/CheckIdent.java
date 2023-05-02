package decompressor.Ident;

/* analyzes the information stored withing the identifier,
primarily password and compressed */
public class CheckIdent {
    public static void check(Ident ident, byte password) {
        if(!ident.compressed()) {
            throw new RuntimeException(new Exception("ERROR: Given file is not compressed!"));
        }
        if(ident.password() && password == 0x00) {
            throw new RuntimeException(new Exception("ERROR: No password given for password protected file!"));
        }
        if(!ident.password() && password != 0x00) {
            throw new RuntimeException(new Exception("ERROR: Password was given for unprotected file!"));
        }
    }
}
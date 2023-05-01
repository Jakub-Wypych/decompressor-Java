package decompressor.Ident;

/* analyzes the inforamtion stored withing the identifier,
primarly password and compressed */
public class CheckIdent {
    public static void check(Ident ident, byte password) {
        if(!ident.compressed()) {
            throw new RuntimeException(new FileIsNotCompressed("ERROR: Given file is not compressed!"));
        }
        if(ident.password() && password == 0x00) {
            throw new RuntimeException(new NoPasswordGivenForProtectedFile("ERROR: No password given for password protected file!"));
        }
        if(!ident.password() && password != 0x00) {
            throw new RuntimeException(new PasswordGivenForUnprotectedFile("ERROR: Password was given for unprotected file!"));
        }
    }
}

class FileIsNotCompressed extends Exception {
    public FileIsNotCompressed(String errorMessage) {
        super(errorMessage);
    }
}

class NoPasswordGivenForProtectedFile extends Exception {
    public NoPasswordGivenForProtectedFile(String errorMessage) {
        super(errorMessage);
    }
}

class PasswordGivenForUnprotectedFile extends Exception {
    public PasswordGivenForUnprotectedFile(String errorMessage) {
        super(errorMessage);
    }
}
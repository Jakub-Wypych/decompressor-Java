package decompressor;

/* Reads password */
public class Password {
    private final byte password;
    public Password(String raw_password) {
        if(raw_password == null) {
            this.password = ((byte) 0x00);
            return;
        }
        char[] password_array = raw_password.toCharArray();
        byte password_key = (byte) password_array[0];
        for (int i=1; i<password_array.length;i++) {
            password_key^=password_array[i];
        }
        this.password = password_key;
    }

    public byte getPassword() {
        return password;
    }
}

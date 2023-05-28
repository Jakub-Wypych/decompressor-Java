package decompressor;

/**
 * Reads password
 */
public class Password {
    private final byte password;

    /**
     * Converts password from a String to a byte
     * @param raw_password password in raw form i.e. in String form
     */
    public Password(String raw_password) {
        if(raw_password == null) {
            this.password = 0x00;
            return;
        }
        char[] password_array = raw_password.toCharArray();
        byte password_key = (byte) password_array[0];
        for (int i=1; i<password_array.length;i++) {
            password_key^=password_array[i];
        }
        if(password_key == 0x00)
            password_key = 0x01;
        this.password = password_key;
    }

    public byte getPassword() {
        return password;
    }
}

package decompressor.Password;

/* Reads password */
public class ReadPassword {
    public static Password makepassword(String raw_password) {
        if(raw_password == null)
            return new Password((byte)0x00);
        char[] password_array = raw_password.toCharArray();
        byte password_key = (byte) password_array[0];
        for (int i=1; i<password_array.length;i++) {
            password_key^=password_array[i];
        }
        return new Password(password_key);
    }
}

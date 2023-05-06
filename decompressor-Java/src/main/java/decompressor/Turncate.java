package decompressor;

import decompressor.Dictionary.Tree;

/* during Decipher it writes needless bytes at end of file,
this class cuts them off
 */
public class Turncate {
    // https://stackoverflow.com/questions/3301445/read-last-byte-from-file-and-truncate-to-size <- very useful
    public static void cut(String file, Tree tree, int stray_bits) {
        // probably will need to close the outfilepath in bitwrite
    }
}

package decompressor;

import decompressor.dictionary.Node;
import decompressor.dictionary.Tree;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/* during Decipher it writes needless bytes at end of file,
this class cuts them off
 */
public class Turncate {
    public static void cut(String filepath, Tree root, int stray_bits) {
        int bytes_to_cut = 0;
        Node current_node = (Node) root.getRoot();
        for(int i =0; i<stray_bits; i++) {
            if(current_node.left() == null || current_node.right() == null) { // we've reached a leaf
                bytes_to_cut++;
                current_node = (Node) root.getRoot();
            }
            current_node = (Node) current_node.left();
        }
        try {
            RandomAccessFile outfile = new RandomAccessFile(filepath, "rwd");
            if(outfile.length() <= bytes_to_cut)
                throw new RuntimeException(new Exception("ERROR: Compressed file is damaged!")); // TODO throw FileDamaged
            outfile.setLength(outfile.length()-bytes_to_cut);
            outfile.close();
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: Couldn't open file: " + filepath);
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println("ERROR: Couldn't modify file: " + filepath);
            throw new RuntimeException(e);
        }
    }
}

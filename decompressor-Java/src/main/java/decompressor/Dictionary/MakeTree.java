package decompressor.Dictionary;

import java.util.ArrayList;

/* Makes a tree from ArrayList of objects,
expects that all the objects implement interface "Node"
 */
public class MakeTree {
    private final Object root;

    public MakeTree(ArrayList<Object> objectArrayList) {
        if(objectArrayList.size() == 0)
            throw new RuntimeException(new Exception("ERROR: Given arraylist is empty!"));
        else if (objectArrayList.size() == 1) { // one node tree
            Node left = (Node) objectArrayList.get(0);
            int value = left.getValue();
            root = new BlankNode(left, null, value);
        } else { // more than one node tree
            while(objectArrayList.size()>1) {
                // Creating blank node
                Node left = (Node) objectArrayList.get(0);
                Node right = (Node) objectArrayList.get(1);
                int value = left.getValue() + right.getValue();
                BlankNode blanknode = new BlankNode(left, right, value);
                System.out.println(blanknode);
                objectArrayList.remove(1);
                objectArrayList.remove(0);

                int index = 0;
                if(objectArrayList.size() != 0) { // adding blank node sorted in
                    Node n = (Node) objectArrayList.get(0);
                    while (value >= n.getValue()) {
                        if (index + 1 >= objectArrayList.size()) // we've reached the end of the array
                            break;
                        index++;
                        n = (Node) objectArrayList.get(index);
                    }
                }
                objectArrayList.add(index, blanknode);
            }
            root = objectArrayList.get(0);
        }
    }

    public Object getRoot() {
        return root;
    }
}

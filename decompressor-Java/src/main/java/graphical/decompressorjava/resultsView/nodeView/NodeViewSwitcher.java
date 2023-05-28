package graphical.decompressorjava.resultsView.nodeView;

import decompressor.dictionary.Node;
import decompressor.dictionary.RawDictionary;
import graphical.decompressorjava.Switcher;

/**
 * Switches to node view,
 * tree in results view is built from buttons and each one uses this class to show its contents
 */
public class NodeViewSwitcher extends Switcher {
    private final Node node;
    private final boolean symbolInBinary;

    public NodeViewSwitcher(Node node, boolean symbolInBinary) {
        this.node = node;
        this.symbolInBinary = symbolInBinary;
    }

    /**
     * Mainly sets up the text in node view
     */
    @Override
    public void setUp() {
        getStage().setTitle("Node");
        getStage().setResizable(false);
        ((NodeViewController) getController()).probability.setText("Probability: " + node.value());
        if(node instanceof RawDictionary) { // checking is said node is a leaf
            if (symbolInBinary)
                ((NodeViewController) getController()).symbol.setText("Symbol: " + ((RawDictionary) node).symbol());
            else ((NodeViewController) getController()).symbol.setText("Symbol: " + ((RawDictionary) node).symbolBinaryToText());
        }
    }

    @Override
    public String getUrl() {
        return "node-view.fxml";
    }
}

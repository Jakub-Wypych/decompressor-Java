package graphical.decompressorjava.resultsView.nodeView;

import decompressor.dictionary.Node;
import decompressor.dictionary.RawDictionary;
import graphical.decompressorjava.Switcher;

public class NodeViewSwitcher extends Switcher {
    private final Node node;

    public NodeViewSwitcher(Node node) {
        this.node = node;
    }

    @Override
    public void setUp() {
        getStage().setTitle("Node");
        ((NodeViewController) getController()).probability.setText("Probability: " + node.value());
        if(node instanceof RawDictionary)
            ((NodeViewController) getController()).symbol.setText("Symbol: " + ((RawDictionary) node).symbol());
    }

    @Override
    public String getUrl() {
        return "node-view.fxml";
    }
}

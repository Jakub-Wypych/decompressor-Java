package graphical.decompressorjava.resultsView;

import decompressor.dictionary.Node;
import decompressor.dictionary.RawDictionary;
import decompressor.Results;
import graphical.decompressorjava.TreeDrawer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;

/* Shows results of decompression
 */
public class ResultsViewController {

    @FXML Canvas treeCanvas;
    @FXML Label identLabel, dictionaryLabel;

    public void setAll(Results results) {
        this.identLabel.setText(results.ident().toString());
        StringBuilder dictionaryLabeltxt = new StringBuilder();
        for (RawDictionary rd: results.rawDictionary()) {
            dictionaryLabeltxt.append(rd.toString()).append("\n");
        }
        this.dictionaryLabel.setText(dictionaryLabeltxt.toString());
        TreeDrawer treeDrawer = new TreeDrawer(results.tree().getRoot());
        treeDrawer.setUp();
        System.out.println(((Node) results.tree().getRoot()).getPos().toString());
    }
}

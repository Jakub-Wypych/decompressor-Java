package graphical.decompressorjava.resultsView;

import decompressor.Results;
import decompressor.dictionary.RawDictionary;
import graphical.decompressorjava.resultsView.treeDrawer.TreeDrawer;
import graphical.decompressorjava.resultsView.treeDrawer.TreePosModifier;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/* Shows results of decompression
 */
public class ResultsViewController {

    @FXML AnchorPane canvas;
    @FXML Label identLabel, dictionaryLabel;

    public void setAll(Results results) {
        this.identLabel.setText(results.ident().toString());
        StringBuilder dictionaryLabeltxt = new StringBuilder();
        for (RawDictionary rd: results.rawDictionary()) {
            dictionaryLabeltxt.append(rd.toString()).append("\n");
        }
        this.dictionaryLabel.setText(dictionaryLabeltxt.toString());
        TreePosModifier modifier = new TreePosModifier(0, 30, 10, 30, 30);
        TreeDrawer treeDrawer = new TreeDrawer(results.tree().getRoot(), canvas, modifier);
        treeDrawer.setModifier(modifier);
        treeDrawer.drawTree();
    }
}

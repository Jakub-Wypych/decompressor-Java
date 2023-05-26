package graphical.decompressorjava.resultsView;

import decompressor.Results;
import decompressor.dictionary.RawDictionary;
import graphical.decompressorjava.resultsView.treeDrawer.TreeDrawer;
import graphical.decompressorjava.resultsView.treeDrawer.TreePosModifier;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/* Shows results of decompression
 */
public class ResultsViewController {

    @FXML ScrollPane scrollPane;
    @FXML AnchorPane canvas;
    @FXML Label identLabel, dictionaryLabel;

    private double previous_x;
    private double previous_y;

    public void setAll(Results results) {
        this.identLabel.setText(results.ident().toString());
        StringBuilder dictionaryLabeltxt = new StringBuilder();
        for (RawDictionary rd: results.rawDictionary()) {
            dictionaryLabeltxt.append(rd.toString()).append("\n");
        }
        this.dictionaryLabel.setText(dictionaryLabeltxt.toString());
        TreePosModifier modifier = new TreePosModifier(0, 40, 10, 40, 30);
        TreeDrawer treeDrawer = new TreeDrawer(results.tree().getRoot(), canvas, modifier);
        treeDrawer.setModifier(modifier);
        treeDrawer.drawTree();
    }

    public void onClickAndDrag(MouseEvent mouseEvent) {
        scrollPane.setHvalue(scrollPane.getHvalue()-(mouseEvent.getX()-previous_x)/canvas.getWidth());
        scrollPane.setVvalue(scrollPane.getVvalue()-(mouseEvent.getY()-previous_y)/canvas.getHeight());
        previous_x = mouseEvent.getX();
        previous_y = mouseEvent.getY();
    }

    public void onMousePressed(MouseEvent mouseEvent) {
        previous_x = mouseEvent.getX();
        previous_y = mouseEvent.getY();
    }
}

package graphical.decompressorjava.resultsView;

import decompressor.Results;
import decompressor.dictionary.RawDictionary;
import graphical.decompressorjava.resultsView.options.OptionsViewSwitcher;
import graphical.decompressorjava.resultsView.treeDrawer.Options;
import graphical.decompressorjava.resultsView.treeDrawer.TreeDrawer;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/* Shows results of decompression
 */
public class ResultsViewController {

    @FXML ScrollPane scrollPane;
    @FXML AnchorPane canvas;
    @FXML Label identLabel, dictionaryLabel;

    private Results results;
    private double previous_x;
    private double previous_y;
    private Options options;

    public void setAll(Options options) {
        this.options = options;
        this.identLabel.setText(results.ident().toString());
        StringBuilder dictionaryLabeltxt = new StringBuilder();
        for (RawDictionary rd: results.rawDictionary()) {
            if(options.symbolInBinary())
                dictionaryLabeltxt.append(rd.toString()).append("\n");
            else dictionaryLabeltxt.append(rd.toStringBinaryToText()).append("\n");
        }
        this.dictionaryLabel.setText(dictionaryLabeltxt.toString());
        TreeDrawer treeDrawer = new TreeDrawer(results.tree().getRoot(), canvas, options);
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

    public void options() {
        OptionsViewSwitcher optionsViewSwitcher = new OptionsViewSwitcher(options, this);
        optionsViewSwitcher.switchToMe(new Stage());
    }

    public void setResults(Results results) {
        this.results = results;
    }
}

package graphical.decompressorjava.resultsView;

import decompressor.information.Results;
import decompressor.information.dictionary.RawDictionary;
import graphical.decompressorjava.resultsView.options.OptionsViewSwitcher;
import graphical.decompressorjava.resultsView.options.Options;
import graphical.decompressorjava.resultsView.treeDrawer.TreeDrawer;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Shows results of deciphering
 */
public class ResultsViewController {

    @FXML ScrollPane scrollPane;
    @FXML AnchorPane canvas;
    @FXML Label identLabel, dictionaryLabel;

    private Results results;
    private Stage stage;
    private double previous_x;
    private double previous_y;
    private Options options;

    /**
     * Sets up the ident, dictionary, tree view
     * @param options determines how the results should be shown
     */
    public void setAll(Options options) {
        this.options = options; // Function options() needs this
        stage.setResizable(options.stageResizable());
        this.identLabel.setText(results.ident().toString()); // writing ident results
        StringBuilder dictionaryLabeltxt = new StringBuilder(); // writing dictionary results
        for (RawDictionary rd: results.rawDictionary()) {
            if(options.symbolInBinary())
                dictionaryLabeltxt.append(rd.toString()).append("\n");
            else dictionaryLabeltxt.append(rd.toStringBinaryToText()).append("\n");
        }
        this.dictionaryLabel.setText(dictionaryLabeltxt.toString());
        TreeDrawer treeDrawer = new TreeDrawer(results.tree().getRoot(), canvas, options); // creating tree drawer
        treeDrawer.drawTree(); // drawing said tree
    }

    /**
     * Calculates by how much the scroll pane needs to be moved,
     * i.e. if mouse moved 100 pixels and size is 600 pixels, then move scroll by 100/600
     * @param mouseEvent stores the x,y coordinate of the mouse
     */
    public void onClickAndDrag(MouseEvent mouseEvent) {
        //
        scrollPane.setHvalue(scrollPane.getHvalue()-(mouseEvent.getX()-previous_x)/canvas.getWidth());
        scrollPane.setVvalue(scrollPane.getVvalue()-(mouseEvent.getY()-previous_y)/canvas.getHeight());
        previous_x = mouseEvent.getX();
        previous_y = mouseEvent.getY();
    }

    /**
     * initiates before {@link #onClickAndDrag(MouseEvent) onClickAndDrag}
     * @param mouseEvent stores the x,y coordinate of the mouse
     */
    public void onMousePressed(MouseEvent mouseEvent) {
        previous_x = mouseEvent.getX();
        previous_y = mouseEvent.getY();
    }

    /**
     * Show options window
     */
    public void options() {
        OptionsViewSwitcher optionsViewSwitcher = new OptionsViewSwitcher(options, this);
        optionsViewSwitcher.switchToMe(new Stage());
    }

    public void setResults(Results results) {
        this.results = results;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}

package graphical.decompressorjava.resultsView.options;

import graphical.decompressorjava.resultsView.ResultsViewController;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class OptionsViewController {

    @FXML CheckBox symbolBinary;
    @FXML TextField xAddition, xMultiplier, yAddition, yMultiplier, buttonWidth, waitTime;
    private ResultsViewController controller;

    public void setController(ResultsViewController controller) {
        this.controller = controller;
    }
}

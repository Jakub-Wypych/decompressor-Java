package graphical.decompressorjava.resultsView.options;

import graphical.decompressorjava.resultsView.ResultsViewController;
import graphical.decompressorjava.resultsView.treeDrawer.Options;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class OptionsViewController {

    @FXML CheckBox symbolBinary;
    @FXML TextField xAddition, xMultiplier, yAddition, yMultiplier, buttonWidth, waitTime, nodeText;
    private ResultsViewController controller;

    public void setController(ResultsViewController controller) {
        this.controller = controller;
    }

    public void onSubmit() {
        // TODO check input values
        double xAddition = Double.parseDouble(this.xAddition.getText());
        double xMultiplier = Double.parseDouble(this.xMultiplier.getText());
        double yAddition = Double.parseDouble(this.yAddition.getText());
        double yMultiplier = Double.parseDouble(this.yMultiplier.getText());
        double buttonWidth = Double.parseDouble(this.buttonWidth.getText());
        double waitTime = Double.parseDouble(this.waitTime.getText());
        String nodeText = this.nodeText.getText();
        boolean symbolInBinary = this.symbolBinary.isSelected();
        controller.setAll(new Options(xAddition, xMultiplier, yAddition, yMultiplier, buttonWidth, waitTime, nodeText, symbolInBinary));
    }
}

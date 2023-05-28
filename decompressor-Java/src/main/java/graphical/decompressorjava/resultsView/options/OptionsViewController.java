package graphical.decompressorjava.resultsView.options;

import graphical.decompressorjava.resultsView.ResultsViewController;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

/**
 * Controller for options view, manages user input
 */
public class OptionsViewController {

    @FXML CheckBox symbolBinary, resizeStage;
    @FXML TextField xAddition, xMultiplier, yAddition, yMultiplier, buttonWidth, waitTime, nodeText;
    private ResultsViewController controller;

    public void setController(ResultsViewController controller) {
        this.controller = controller;
    }

    /**
     * Reads all the values inputted in the TextFields/CheckBoxes
     * and sends them to the controller
     */
    public void onSubmit() {
        double xAddition = setValue(this.xAddition, 0.0);
        double xMultiplier = setValue(this.xMultiplier, 31.0);
        double yAddition = setValue(this.yAddition, 10);
        double yMultiplier = setValue(this.yMultiplier, 28.0);
        double buttonWidth = setValue(this.buttonWidth, 30.0);
        double waitTime = setValue(this.waitTime, 0.0);
        String nodeText = this.nodeText.getText();
        boolean symbolInBinary = this.symbolBinary.isSelected();
        boolean stageResizable = this.resizeStage.isResizable();
        controller.setAll(new Options(xAddition, xMultiplier, yAddition, yMultiplier, buttonWidth, waitTime, nodeText, symbolInBinary, stageResizable));
    }

    /**
     * Converts String (text) in textField to a value
     * @param textField text field which stores the values in String
     * @param defaultValue if String in textField is not numeric, returns the default value
     * @return value read from the text in textField
     */
    private double setValue(TextField textField, double defaultValue) {
        try {
            if (textField.getText().isEmpty())
                return  0;
            else return Double.parseDouble(textField.getText());
        } catch (NumberFormatException e) { // String is not a number
            textField.setStyle("-fx-border-color: red");
            return defaultValue;
        }
    }

    /**
     * Resets TextFields border color (i.e. turns off its warning)
     * @param event the TextField which ran the function
     */
    public void onKeyTyped(KeyEvent event) {
        ((TextField) event.getSource()).setStyle("-fx-border-color: black");
    }
}

package graphical.decompressorjava.userInputView;

import decompressor.Decompress;
import decompressor.Results;
import graphical.decompressorjava.resultsView.ResultsViewSwitcher;
import graphical.decompressorjava.resultsView.options.Options;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

/**
 * Handles submit button press, decompression and switching to results view
 */
public class SubmitController {
    private final UserInputViewController controller;

    public SubmitController(UserInputViewController controller) {
        this.controller = controller;
    }

    /**
     * Checks if it can decompress and if so start decompression,
     * if succeeds switch to results view,
     * if not show warning
     * @param event button which ran the method
     */
    public void tryDecompress(ActionEvent event) {
        if(canDecompress()) {
            Results results = startDecompression(); // TODO if decompression fails show warning
            switchSceneResults(event, results);
        }
    }

    /**
     * Checks if input file path is correct (which also checks if password is given) and if outfile path is correct
     * @return if it can decompress
     */
    private boolean canDecompress() {
        return controller.getInputController().check() && controller.getOutputController().check();
    }

    /**
     * Begins the decompression process using the user inputted values
     * @return results of decompression
     */
    private Results startDecompression() {
        String inputfilepath = controller.inputfileTextfield.getText();
        String outputfilepath, password;
        if(controller.outputfileTextfield.getText().isEmpty())
            outputfilepath = "decompressed.bin";
        else outputfilepath = controller.outputfileTextfield.getText();
        if(controller.passwordTextfield.getText().isEmpty())
            password = null;
        else password = controller.passwordTextfield.getText();
        return Decompress.decompress(inputfilepath, outputfilepath, password);
    }

    /**
     * Switches to results view window, sets it up with default options
     * @param event needed to switch window
     * @param results results of the decompression
     */
    private void switchSceneResults(ActionEvent event, Results results) {
        ResultsViewSwitcher resultsViewSwitcher = new ResultsViewSwitcher(results, new Options(0, 31, 10, 28, 30, 0, "/enum", true, false));
        resultsViewSwitcher.switchToMe((Stage) ((Node) event.getSource()).getScene().getWindow());
    }
}

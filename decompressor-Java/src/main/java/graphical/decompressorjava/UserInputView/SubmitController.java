package graphical.decompressorjava.UserInputView;

import decompressor.Decompress;
import decompressor.Results;
import graphical.decompressorjava.ResultsViewController;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

/* Handles submit button press, decompression and switching to results view
 */
public class SubmitController {
    private final UserInputViewController controller;

    public SubmitController(UserInputViewController controller) {
        this.controller = controller;
    }

    public void tryDecompress(ActionEvent event) {
        if(canDecompress()) {
            Results results = startDecompression();
            switchSceneResults(event, results);
        }
    }
    private boolean canDecompress() {
        return controller.getInputController().check() && controller.getOutputController().check();
    }
    private Results startDecompression() {
        String inputfilepath = controller.inputfileTextfield.getText();
        String outputfilepath, password;
        if(controller.outputfileTextfield.getText().isEmpty())
            outputfilepath = "decompressed.bin";
        else outputfilepath = controller.outputfileTextfield.getText();
        if(controller.passwordTextfield.getText().isEmpty())
            password = null;
        else password = controller.passwordTextfield.getText();
        Decompress.decompress(inputfilepath, outputfilepath, password);
        return new Results(); // TODO
    }

    private void switchSceneResults(ActionEvent event, Results results) { // TODO needs Results
        ResultsViewController resultsViewController = new ResultsViewController();
        resultsViewController.switchToMe((Stage) ((Node) event.getSource()).getScene().getWindow());
    }
}

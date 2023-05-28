package graphical.decompressorjava.userInputView;

import decompressor.Decompress;
import decompressor.Results;
import graphical.decompressorjava.resultsView.ResultsViewSwitcher;
import graphical.decompressorjava.resultsView.treeDrawer.Options;
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
        return Decompress.decompress(inputfilepath, outputfilepath, password);
    }

    private void switchSceneResults(ActionEvent event, Results results) {
        ResultsViewSwitcher resultsViewSwitcher = new ResultsViewSwitcher(results, new Options(0, 31, 10, 28, 30, 0, "/enum", true));
        resultsViewSwitcher.switchToMe((Stage) ((Node) event.getSource()).getScene().getWindow());
    }
}

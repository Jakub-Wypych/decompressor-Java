package graphical.decompressorjava.UserInputView;

import decompressor.Decompress;
import decompressor.Results;
import graphical.decompressorjava.ResultsViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SubmitController {
    private final UserViewController controller;

    public SubmitController(UserViewController controller) {
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
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("results-view.fxml"));
        Parent root;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            System.out.println("ERROR: Failed to load results-view!");
            throw new RuntimeException(e);
        }

        ResultsViewController resultsViewController = fxmlLoader.getController();
        resultsViewController.setIdent_label("EGG");

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 320, 240);
        stage.setScene(scene);
        stage.show();
    }
}

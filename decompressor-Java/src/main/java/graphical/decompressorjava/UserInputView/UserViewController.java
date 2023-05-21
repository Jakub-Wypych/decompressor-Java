package graphical.decompressorjava.UserInputView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class UserViewController {
    @FXML
    Label inputLabel, passwordLabel, outputLabel;
    @FXML
    TextField passwordTextfield, inputfileTextfield, outputfileTextfield;

    private final SubmitController submitController;
    private final PasswordController passwordController;
    private final InputController inputController;
    private final OutputController outputController;
    private final ChooserController chooserController;

    public UserViewController() {
        submitController = new SubmitController(this);
        passwordController = new PasswordController(this);
        inputController = new InputController(this);
        outputController = new OutputController(this);
        chooserController = new ChooserController(this);
    }
    public void onSubmitButtonClick(ActionEvent event) {
        submitController.tryDecompress(event);
    }
    public void onInputfileKeyTyped() { // User is changing the input file path
        inputController.clearWarnings();
        passwordController.hidePasswordTextField();
        outputController.check();
    }
    public void onPasswordKeyTyped() { // Turns off the password warning, needs to check outfile path, can only exist if infile path is correct, if infile path is modified it stops existing
        passwordController.clearWarnings();
        outputController.check();
    }
    public void onOutputfileKeyTyped() { // User is inputting the outfile path, turn off any warnings and check the input file path
        outputController.clearWarnings();
        inputController.check();
    }
    public void onFileChooser(ActionEvent event) { // User is choosing the output file path, need to check outfile path and infile path
        chooserController.showFileChooser(event);
    }
    public void onDirectoryChooser(ActionEvent event) { // User is choosing the output file directory, need to check outfile path and infile path
        chooserController.showDirectoryChooser(event);
    }
    public PasswordController getPasswordController() { return passwordController; }
    public InputController getInputController() { return inputController; }
    public OutputController getOutputController() { return outputController; }
}
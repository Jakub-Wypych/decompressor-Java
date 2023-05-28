package graphical.decompressorjava.userInputView;

import graphical.decompressorjava.Switcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * Primarily handles user input (typing and button presses),
 * also handles switching to its scene
 */
public class UserInputViewController extends Switcher {
    @FXML
    Label inputLabel, passwordLabel, outputLabel;
    @FXML
    TextField passwordTextfield, inputfileTextfield, outputfileTextfield;

    private final SubmitController submitController;
    private final PasswordController passwordController;
    private final InputController inputController;
    private final OutputController outputController;
    private final ChooserController chooserController;

    /**
     * Sets up all the controllers for user input
     */
    public UserInputViewController() {
        submitController = new SubmitController(this);
        passwordController = new PasswordController(this);
        inputController = new InputController(this);
        outputController = new OutputController(this);
        chooserController = new ChooserController(this);
    }

    @Override
    public String getUrl() {
        return "userinput-view.fxml";
    }
    @Override
    public void setUp() {
        getStage().setResizable(false);
        getStage().setTitle("Decompression App");
    }

    /**
     * User presses the submit button,
     * uses {@link SubmitController} to handle it
     * @param event button which ran the method
     */
    public void onSubmitButtonClick(ActionEvent event) {
        submitController.tryDecompress(event);
    }

    /**
     * User is changing the input file path,
     * clear input warnings, hides the password and check if output is correct
     */
    public void onInputfileKeyTyped() {
        inputController.clearWarnings();
        passwordController.hidePasswordTextField();
        outputController.check();
    }

    /**
     * User is changing the password,
     * turns off the password warning and check outfile path
     * (can only exist if infile path is correct, if infile path is modified it stops existing)
     */
    public void onPasswordKeyTyped() {
        passwordController.clearWarnings();
        outputController.check();
    }

    /**
     * User is changing the outfile path,
     * turn off any warnings and check the input file path
     */
    public void onOutputfileKeyTyped() {
        outputController.clearWarnings();
        inputController.check();
    }

    /**
     * User presses the file chooser button,
     * uses {@link ChooserController} to handle it
     * @param event button which ran the method
     */
    public void onFileChooser(ActionEvent event) { // User is choosing the output file path, need to check outfile path and infile path
        chooserController.showFileChooser(event);
    }

    /**
     * User presses the directory chooser button,
     * uses {@link ChooserController} to handle it
     * @param event button which ran the method
     */
    public void onDirectoryChooser(ActionEvent event) { // User is choosing the output file directory, need to check outfile path and infile path
        chooserController.showDirectoryChooser(event);
    }
    public PasswordController getPasswordController() { return passwordController; }
    public InputController getInputController() { return inputController; }
    public OutputController getOutputController() { return outputController; }
}
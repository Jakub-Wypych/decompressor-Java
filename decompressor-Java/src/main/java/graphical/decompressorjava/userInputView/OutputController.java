package graphical.decompressorjava.userInputView;

import java.io.File;

/**
 * Handles outputTextField
 */
public class OutputController {
    private final UserInputViewController controller;

    public OutputController(UserInputViewController controller) {
        this.controller = controller;
    }

    /**
     * Resets border color and hides warning text
     */
    public void clearWarnings() {
        controller.outputfileTextfield.setStyle("-fx-border-color: black");
        controller.outputLabel.setVisible(false);
    }

    /**
     * Will check if it can write into the output file path or if it can create a new file in said location,
     * if no output file path was given it will use the default location
     * @return if output file path is correct
     */
    public boolean check() {
        clearWarnings();
        String filepath = controller.outputfileTextfield.getText();
        if (filepath == null) // no output file path -> use default 'decompressed.bin'
            return true;
        File file = new File(filepath);
        if(file.exists() && !file.canWrite() || !file.exists() && file.canWrite()) {
            controller.outputfileTextfield.setStyle("-fx-border-color: red");
            controller.outputLabel.setVisible(true);
        }
        return !(file.exists() && !file.canWrite() || !file.exists() && file.canWrite()); // This doesn't "smell" right...
    }
}

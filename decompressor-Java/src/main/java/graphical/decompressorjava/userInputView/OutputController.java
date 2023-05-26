package graphical.decompressorjava.userInputView;

import java.io.File;

/* Handles outputTextField
 */
public class OutputController {
    private final UserInputViewController controller;

    public OutputController(UserInputViewController controller) {
        this.controller = controller;
    }

    public void clearWarnings() {
        controller.outputfileTextfield.setStyle("-fx-border-color: black");
        controller.outputLabel.setVisible(false);
    }

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

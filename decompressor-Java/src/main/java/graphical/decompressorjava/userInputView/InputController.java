package graphical.decompressorjava.userInputView;

import decompressor.bitwork.Bitread;
import decompressor.bitwork.FileIsEmpty;
import decompressor.Ident;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/* Handles InputTextField
 */
public class InputController {
    private final UserInputViewController controller;

    public InputController(UserInputViewController controller) {
        this.controller = controller;
    }

    public void clearWarnings() {
        controller.inputfileTextfield.setStyle("-fx-border-color: black");
        controller.inputLabel.setVisible(false);
    }

    private boolean setWarning(String errorMessage) {
        controller.inputLabel.setText(errorMessage);
        controller.inputLabel.setVisible(true);
        controller.inputfileTextfield.setStyle("-fx-border-color: red");
        return false;
    }

    public boolean check() {
        String infilepath = controller.inputfileTextfield.getText();
        if (infilepath.isEmpty()) // did user input the input file path?
            return setWarning("An input file is required!");
        try {
            Bitread bitread = new Bitread(infilepath, (byte) 0x00); // throws FileNotFoundException and FileIsEmpty
            ArrayList<Byte> raw_ident = bitread.readNbits(8);
            Ident ident = new Ident(raw_ident);
            if (!ident.isCompressed()) // is the given file compressed?
                return setWarning("File is not compressed!");
            if (ident.isPassword() && !controller.getPasswordController().hasUserGivenPassword()) // is it password protected? and has the user given a password?
                return false;
        } catch (FileNotFoundException e) {
            return setWarning("File not found!");
        } catch (FileIsEmpty e) {
            return setWarning("File is empty!");
        }
        return true;
    }
}

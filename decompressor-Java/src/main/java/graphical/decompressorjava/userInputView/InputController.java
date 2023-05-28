package graphical.decompressorjava.userInputView;

import decompressor.bitwork.Bitread;
import decompressor.bitwork.FileIsEmpty;
import decompressor.Ident;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Handles InputTextField
 */
public class InputController {
    private final UserInputViewController controller;

    public InputController(UserInputViewController controller) {
        this.controller = controller;
    }

    /**
     * Resets border color and turns off the text warning
     */
    public void clearWarnings() {
        controller.inputfileTextfield.setStyle("-fx-border-color: black");
        controller.inputLabel.setVisible(false);
    }

    /**
     * @param errorMessage what text warning to write
     * @return always returns false, makes {@link #check() check} look tidier
     */
    private boolean setWarning(String errorMessage) {
        controller.inputLabel.setText(errorMessage);
        controller.inputLabel.setVisible(true);
        controller.inputfileTextfield.setStyle("-fx-border-color: red");
        return false;
    }

    /**
     * Will check if inputted file path is correct,
     * as well as if its password protected or output file path is the same as input file path
     * @return if inputted infile is correct
     */
    public boolean check() {
        String infilepath = controller.inputfileTextfield.getText();
        if (infilepath.isEmpty()) // did user input the input file path?
            return setWarning("An input file is required!");
        if (infilepath.equals(controller.outputfileTextfield.getText()))
            return setWarning("Input equals output!"); // is the inputfile path diffrent from outputfilepath?
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

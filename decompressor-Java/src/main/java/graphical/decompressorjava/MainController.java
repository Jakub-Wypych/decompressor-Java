package graphical.decompressorjava;

import decompressor.Bitwork.Bitread;
import decompressor.Bitwork.FileIsEmpty;
import decompressor.Decompress;
import decompressor.Ident;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class MainController {

    public Label inputLabel, passwordLabel, outputLabel;
    public TextField passwordTextfield, inputfileTextfield, outputfileTextfield;

    public void onSubmitButtonClick(ActionEvent event) {
        if(!checkInputfileTextField() || !checkOutfilepath())
            return;
        //if(passwordLabel.isVisible() && passwordTextfield.getText().isEmpty()) return; // checkInputfileTextField already checks for that
        String inputfilepath = inputfileTextfield.getText();
        String outputfilepath, password;
        if(outputfileTextfield.getText().isEmpty())
            outputfilepath = "decompressed.bin";
        else outputfilepath = outputfileTextfield.getText();
        if(passwordTextfield.getText().isEmpty())
            password = null;
        else password = passwordTextfield.getText();
        Decompress.decompress(inputfilepath, outputfilepath, password);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    private boolean checkInputfileTextField() {
        String infilepath = inputfileTextfield.getText();
        if (infilepath.isEmpty()) // did user input the input file path?
            return inputError("An input file is required!");
        try {
            Bitread bitread = new Bitread(infilepath, (byte) 0x00); // throws FileNotFoundException and FileIsEmpty
            ArrayList<Byte> raw_ident = bitread.readNbits(8);
            Ident ident = new Ident(raw_ident);
            if (!ident.isCompressed()) { // is the given file compressed?
                return inputError("File is not compressed!");
            }
            if (ident.isPassword()) { // is it password protected?
                if(passwordTextfield.getText().isEmpty()) { // has the user given a password?
                    passwordTextfield.setStyle("-fx-border-color: red");
                    passwordTextfield.setVisible(true);
                    passwordLabel.setVisible(true);
                    return false;
                }
            }
        } catch (FileNotFoundException e) {
            return inputError("File not found!");
        } catch (FileIsEmpty e) {
            return inputError("File is empty!");
        }
        return true;
    }

    private boolean inputError(String errorMessage) {
        inputLabel.setText(errorMessage);
        inputLabel.setVisible(true);
        inputfileTextfield.setStyle("-fx-border-color: red");
        return false;
    }

    public void onInputfileKeyTyped() { // User is changing the input file path
        // resetting inputTextfield
        inputfileTextfield.setStyle("-fx-border-color: black");
        inputLabel.setVisible(false);
        // resetting passwordTextfield
        passwordTextfield.clear();
        passwordTextfield.setVisible(false);
        passwordLabel.setVisible(false);
        // checking the out file path (if null don't do anything)
        checkOutfilepath();
    }

    public void onPasswordKeyTyped() { /* Turns off the password warning, needs to check outfile path,
                                        * can only exist if infile path is correct, if infile path is modified it stops existing */
        passwordTextfield.setStyle("-fx-border-color: black");
        passwordLabel.setVisible(false);
        checkOutfilepath();
    }

    public void onOutputfileKeyTyped() { // User is inputting the outfile path, turn off any warnings and check the input file path
        outputfileTextfield.setStyle("-fx-border-color: black");
        outputLabel.setVisible(false);
        checkInputfileTextField();
    }

    public void onFileChooser(ActionEvent event) { // User is choosing the output file path, need to check outfile path and infile path
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("C:\\")); // default path 'C:\'
        File filepath = fileChooser.showOpenDialog( ((Node) event.getSource()).getScene().getWindow()); // show the window
        if(filepath == null) // User pressed cancel
            return;
        if(((Node) event.getSource()).getId().equals("outfilepath")) {
            outputfileTextfield.setText(filepath.getAbsolutePath());
            onOutputfileKeyTyped();
            // checkOutfilepath(); // onInputfileKeyTyped already does that, but I leave it here just in case in future it doesn't
        } else if (((Node) event.getSource()).getId().equals("infilepath")) {
            inputfileTextfield.setText(filepath.getAbsolutePath());
            onInputfileKeyTyped();
        }
        checkInputfileTextField();
    }

    public void onDirectoryChooser(ActionEvent event) { // User is choosing the output file directory, need to check outfile path and infile path
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setInitialDirectory(new File("C:\\")); // default path 'C:\'
        File directorypath = directoryChooser.showDialog(((Node) event.getSource()).getScene().getWindow()); // show the window
        if(directorypath == null) // User pressed cancel
            return;
        outputfileTextfield.setText(directorypath.getAbsolutePath() + "\\decompressed.bin"); /* it's a directory chooser not file chooser,
                                                                                              * so we append 'decompressed.bin' by default on the end */
        onOutputfileKeyTyped();
        checkOutfilepath();
        checkInputfileTextField();
    }

    private boolean checkOutfilepath() {
        String filepath = outputfileTextfield.getText();
        if (filepath == null) // no output file path -> use default 'decompressed.bin'
            return true;
        File file = new File(filepath);
        if(file.canWrite()) {
            outputfileTextfield.setStyle("-fx-border-color: red");
            outputLabel.setVisible(true);
        }
        return !file.canWrite();
    }
}
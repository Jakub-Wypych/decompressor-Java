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

    public Label inputLabel;
    public TextField passwordTextfield;
    public TextField inputfileTextfield;
    public Label passwordLabel;
    public TextField outputfileTextfield;

    public void onSubmitButtonClick(ActionEvent event) {
        if(!checkInputfileTextField())
            return;
        if(passwordLabel.isVisible() && passwordTextfield.getText().isEmpty())
            return;
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

    public boolean checkInputfileTextField() {
        String infilepath = inputfileTextfield.getText();
        if (infilepath.isEmpty()) {
            inputLabel.setVisible(true);
            inputLabel.setText("An input file is required!");
            inputfileTextfield.setStyle("-fx-border-color: red");
            return false;
        }
        try {
            Bitread bitread = new Bitread(infilepath, (byte) 0x00);
            ArrayList<Byte> raw_ident = bitread.readNbits(8);
            Ident ident = new Ident(raw_ident);
            if (!ident.isCompressed()) {
                return inputError("File is not compressed!");
            }
            if (ident.isPassword()) {
                if(!passwordTextfield.getText().isEmpty())
                    return true;
                passwordTextfield.setStyle("-fx-border-color: red");
                passwordTextfield.setVisible(true);
                passwordLabel.setVisible(true);
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

    public void onInputfileKeyTyped() {
        inputfileTextfield.setStyle("-fx-border-color: black");
        inputLabel.setVisible(false);
        passwordTextfield.clear();
        passwordTextfield.setVisible(false);
        passwordLabel.setVisible(false);
    }

    public void onPasswordKeyTyped() {
        passwordTextfield.setStyle("-fx-border-color: black");
        passwordLabel.setVisible(false);
    }

    public void onFileChooser(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("C:\\"));
        File filepath = fileChooser.showOpenDialog( ((Node) event.getSource()).getScene().getWindow());
        if(filepath == null) // User pressed cancel
            return;
        if(((Node) event.getSource()).getId().equals("outfilepath"))
            outputfileTextfield.setText(filepath.getAbsolutePath());
        else if (((Node) event.getSource()).getId().equals("infilepath"))
            inputfileTextfield.setText(filepath.getAbsolutePath());
        onInputfileKeyTyped();
        checkInputfileTextField();
    }

    public void onDirectoryChooser(ActionEvent event) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setInitialDirectory(new File("C:\\"));
        File directorypath = directoryChooser.showDialog(((Node) event.getSource()).getScene().getWindow());
        if(directorypath == null) // User pressed cancel
            return;
        outputfileTextfield.setText(directorypath.getAbsolutePath() + "\\decompressed.bin");
        checkInputfileTextField();
    }
}
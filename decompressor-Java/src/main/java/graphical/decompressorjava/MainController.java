package graphical.decompressorjava;

import decompressor.Bitwork.Bitread;
import decompressor.Bitwork.FileIsEmpty;
import decompressor.Decompress;
import decompressor.Ident;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class MainController {
    @FXML
    public Label inputLabel;
    @FXML
    public TextField passwordTextfield;
    @FXML
    public TextField inputfileTextfield;
    @FXML
    public Label passwordLabel;
    @FXML
    public TextField outputfileTextfield;

    @FXML
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

    public void onInputfileKeyTyped(KeyEvent keyEvent) { // resets inputfileTextField border color
        TextField inputfileTextField = (TextField) keyEvent.getSource();
        inputfileTextField.setStyle("-fx-border-color: black");
        inputLabel.setVisible(false);
        passwordTextfield.clear();
        passwordTextfield.setVisible(false);
        passwordLabel.setVisible(false);
    }

    public void onPasswordKeyTyped() {
        passwordTextfield.setStyle("-fx-border-color: black");
        passwordLabel.setVisible(false);
    }
}
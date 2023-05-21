package graphical.decompressorjava;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ResultsViewController {
    @FXML
    Label ident_label, output_label, huffman_label;

    public void setIdent_label(String text) {
        ident_label.setText(text);
    }
}

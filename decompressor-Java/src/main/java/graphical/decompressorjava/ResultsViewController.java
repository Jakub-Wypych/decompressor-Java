package graphical.decompressorjava;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

/* Shows results of decompression
 */
public class ResultsViewController extends Switcher {
    @FXML
    Label ident_label, output_label, huffman_label;

    @Override
    public String getUrl() {
        return "results-view.fxml";
    }

    @Override
    public void setUp() {
        ((ResultsViewController) getController()).ident_label.setText("egg");
    }
}

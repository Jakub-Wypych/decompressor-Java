module decompressor.decompressorjava {
    requires javafx.controls;
    requires javafx.fxml;
            
        requires org.controlsfx.controls;
                            
    opens graphical.decompressorjava to javafx.fxml;
    exports graphical.decompressorjava;
    exports graphical.decompressorjava.userInputView;
    opens graphical.decompressorjava.userInputView to javafx.fxml;
    exports graphical.decompressorjava.resultsView;
    opens graphical.decompressorjava.resultsView to javafx.fxml;
    exports decompressor;
    exports decompressor.bitwork;
    exports decompressor.dictionary;
    opens decompressor.dictionary to javafx.fxml;
}
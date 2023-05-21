module decompressor.decompressorjava {
    requires javafx.controls;
    requires javafx.fxml;
            
        requires org.controlsfx.controls;
                            
    opens graphical.decompressorjava to javafx.fxml;
    exports graphical.decompressorjava;
    exports graphical.decompressorjava.UserInputView;
    opens graphical.decompressorjava.UserInputView to javafx.fxml;
}
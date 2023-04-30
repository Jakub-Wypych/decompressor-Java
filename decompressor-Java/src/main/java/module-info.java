module decompressor.decompressorjava {
    requires javafx.controls;
    requires javafx.fxml;
            
        requires org.controlsfx.controls;
                            
    opens decompressor.decompressorjava to javafx.fxml;
    exports decompressor.decompressorjava;
}
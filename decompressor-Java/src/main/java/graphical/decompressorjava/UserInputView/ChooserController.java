package graphical.decompressorjava.UserInputView;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.io.File;

/* Handles File Chooser and Directory Chooser
 */
public class ChooserController {
    private final UserInputViewController controller;

    public ChooserController(UserInputViewController controller) {
        this.controller = controller;
    }

    public void showFileChooser(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("C:\\")); // default path 'C:\'
        File filepath = fileChooser.showOpenDialog( ((Node) event.getSource()).getScene().getWindow()); // show the window
        if(filepath == null) // User pressed cancel
            return;
        if(((Node) event.getSource()).getId().equals("outfilepath")) {
            controller.outputfileTextfield.setText(filepath.getAbsolutePath());
            controller.onOutputfileKeyTyped();
        } else if (((Node) event.getSource()).getId().equals("infilepath")) {
            controller.inputfileTextfield.setText(filepath.getAbsolutePath());
            controller.onInputfileKeyTyped();
        }
        controller.getInputController().check();
    }

    public void showDirectoryChooser(ActionEvent event) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setInitialDirectory(new File("C:\\")); // default path 'C:\'
        File directorypath = directoryChooser.showDialog(((Node) event.getSource()).getScene().getWindow()); // show the window
        if(directorypath == null) // User pressed cancel
            return;
        controller.outputfileTextfield.setText(directorypath.getAbsolutePath() + "\\decompressed.bin"); // it's a directory chooser not file chooser, so we append 'decompressed.bin' by default on the end
        controller.onOutputfileKeyTyped();
        controller.getOutputController().check();
    }
}

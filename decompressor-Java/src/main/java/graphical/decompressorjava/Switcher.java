package graphical.decompressorjava;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/* Allows for easy scene switching,
automatically stores stage and controller,
also helps with setting up text, buttons etc.
 */
public abstract class Switcher {
    private Stage stage;
    private Object controller;
    public abstract String getUrl();
    public void switchToMe(Stage stage) {
        this.stage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(Switcher.class.getResource(getUrl()));
        Parent root;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            System.out.println("ERROR: Failed to load " + getUrl());
            throw new RuntimeException(e);
        }
        controller = fxmlLoader.getController();
        setUp();
        Scene scene = new Scene(root);
        this.stage.setScene(scene);
        this.stage.show();
    }

    public void setUp() {
        // Code
    }

    public Stage getStage() {
        return stage;
    }

    public Object getController() {
        return controller;
    }
}

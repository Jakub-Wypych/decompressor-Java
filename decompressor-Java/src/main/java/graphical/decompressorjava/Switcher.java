package graphical.decompressorjava;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Allows for easy scene switching,
 * automatically stores stage and controller,
 * also helps with setting up text, buttons etc.
 */
public abstract class Switcher {
    private Stage stage;
    private Object controller;

    /**
     * @return .fxml file string example: "example.fxml"
     */
    public abstract String getUrl();

    /**
     * Switches to their scene
     * @param stage what to switch to
     */
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

    /**
     * Is ran before {@link #switchToMe(Stage) switchToMe} sets the scene
     */
    public void setUp() {
        // Code
    }

    /**
     * @return his stage
     */
    public Stage getStage() {
        return stage;
    }

    /**
     * @return his controller
     */
    public Object getController() {
        return controller;
    }
}

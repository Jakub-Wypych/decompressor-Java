package graphical.decompressorjava;

import graphical.decompressorjava.userInputView.UserInputViewController;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * [Main]ly used to initialize the application
 */
public class MainApplication extends Application {
    @Override
    public void start(Stage stage) {
        UserInputViewController userInputViewController = new UserInputViewController();
        userInputViewController.switchToMe(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}
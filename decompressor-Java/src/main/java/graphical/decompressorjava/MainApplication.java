package graphical.decompressorjava;

import graphical.decompressorjava.UserInputView.UserInputViewController;
import javafx.application.Application;
import javafx.stage.Stage;

/* Mainly used to initialize the application */
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
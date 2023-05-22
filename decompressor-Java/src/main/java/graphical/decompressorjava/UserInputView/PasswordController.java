package graphical.decompressorjava.UserInputView;

/* Handles PasswordTextField
 */
public class PasswordController {
    private final UserInputViewController controller;

    public PasswordController(UserInputViewController controller) {
        this.controller = controller;
    }

    public void hidePasswordTextField() {
        controller.passwordTextfield.clear();
        controller.passwordTextfield.setVisible(false);
        controller.passwordLabel.setVisible(false);
    }

    public void clearWarnings() {
        controller.passwordTextfield.setStyle("-fx-border-color: black");
        controller.passwordLabel.setVisible(false);
    }

    public boolean hasUserGivenPassword() {
        if(controller.passwordTextfield.getText().isEmpty()) {
            controller.passwordTextfield.setStyle("-fx-border-color: red");
            controller.passwordTextfield.setVisible(true);
            controller.passwordLabel.setVisible(true);
            return false;
        }
        return true;
    }
}

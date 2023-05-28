package graphical.decompressorjava.userInputView;

/**
 * Handles PasswordTextField
 */
public class PasswordController {
    private final UserInputViewController controller;

    public PasswordController(UserInputViewController controller) {
        this.controller = controller;
    }

    /**
     * Clears passwordTextFields text and hides it
     */
    public void hidePasswordTextField() {
        controller.passwordTextfield.clear();
        controller.passwordTextfield.setVisible(false);
        controller.passwordLabel.setVisible(false);
    }

    /**
     * Clears border color and hides its warning text
     */
    public void clearWarnings() {
        controller.passwordTextfield.setStyle("-fx-border-color: black");
        controller.passwordLabel.setVisible(false);
    }

    /**
     * Check if user inputted any text into passwordTextField,
     * if not sets a warning
     * @return if passwordTextField have any text
     */
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

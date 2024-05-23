package app.Controladores;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private TextField userNameTextField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private void handleLoginButtonAction() {
        // Lógica de autenticación aquí
        String username = userNameTextField.getText();
        String password = passwordField.getText();
        System.out.println("Username: " + username + ", Password: " + password);
        // Añade tu lógica de autenticación aquí
    }

    @FXML
    private void handleCancelButtonAction() {
        // Cierra la ventana actual
        Stage stage = (Stage) userNameTextField.getScene().getWindow();
        stage.close();
    }
}
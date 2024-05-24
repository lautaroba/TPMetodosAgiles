package app.Controladores;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private TextField dniTextfield;

    @FXML
    private PasswordField pwTextfield;

    @FXML
    private Button aceptarButton;

    @FXML
    private Button nuevoAdmButton;

    @FXML
    private Button cancelarButton;
    
    @FXML
    private void aceptar(ActionEvent event) {
        // Lógica de autenticación aquí
        String username = dniTextfield.getText();
        String password = pwTextfield.getText();
        System.out.println("Username: " + username + ", Password: " + password);
        // Añade tu lógica de autenticación aquí
    }

    @FXML
    private void cancelar(ActionEvent event) {
        // Cierra la ventana actual
        Stage stage = (Stage) dniTextfield.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void nuevo(ActionEvent event){}
    
}
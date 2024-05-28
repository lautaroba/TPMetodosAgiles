package app.Controladores;
import java.util.ResourceBundle;

import app.App;
import app.DTOs.AdministradorDTO;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.Node;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;

public class LoginController implements Initializable{

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
    
    private Stage stage;
	private Scene scene;
	private Parent root;

    @Override
	public void initialize(URL location, ResourceBundle resources) {

        dniTextfield.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    // Si el nuevo valor contiene caracteres no numéricos, reemplace el texto con el antiguo valor
                    dniTextfield.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

	}

    @FXML
    private void aceptar(ActionEvent event) throws IOException {

        // Verifico si es super usuario para cargar administradores
        if(dniTextfield.getText().equals("") && pwTextfield.getText().equals("root")){
            SuperUsuario(event);
        }
        else{
            try{
                AdministradorDTO administrador = App.gestor.BuscarAdministrador(new AdministradorDTO(Integer.parseInt(dniTextfield.getText()), null, null, null, null, null, pwTextfield.getText()));
                if(administrador.contraseña.equals(pwTextfield.getText()))
                    menuPrincipal(event);
                else {
                    Alert alert = new Alert(AlertType.WARNING);
                    alert.setTitle("Sistema de licencias");
                    alert.setContentText("Contrasena y/o DNI incorrectos, intente nuevamente.");
                    alert.showAndWait();
                }
            }catch(Exception e){
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Sistema de licencias");
                alert.setContentText("Contrasena y/o DNI incorrectos, intente nuevamente.");
                alert.showAndWait();
            }
        }
    }

    @FXML
    private void cancelar(ActionEvent event) {
        
        App.entityManager.getTransaction().begin();
        App.entityManager.createNativeQuery("DROP DATABASE licenciasdb").executeUpdate();
        App.entityManager.getTransaction().commit();
        Stage stage = (Stage) dniTextfield.getScene().getWindow();
        stage.close();
    }

    public void menuPrincipal(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("/ControladoresFXML/MenuPrincipal.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setTitle("Menu Principal");
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

    public void SuperUsuario(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/ControladoresFXML/CrearAdm.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Cargar usuario administrador");
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
	}
    
}
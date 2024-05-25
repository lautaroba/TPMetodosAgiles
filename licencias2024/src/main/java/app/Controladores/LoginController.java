package app.Controladores;
import java.util.ResourceBundle;

import app.DTOs.AdministradorDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

    AdministradorDTO adm;
    
    private Stage stage;
	private Scene scene;
	private Parent root;

    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
    	adm = new AdministradorDTO();
	}

    @FXML
    private void aceptar(ActionEvent event) {

        String username = dniTextfield.getText();
        String password = pwTextfield.getText();
 
        try{
            menuPrincipal(event);
        } catch(IOException exception){
            exception.printStackTrace();
        }
    }

    @FXML
    private void cancelar(ActionEvent event) {

        Stage stage = (Stage) dniTextfield.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void nuevo(ActionEvent event){

    }

    public void menuPrincipal(ActionEvent event) throws IOException {

		root = FXMLLoader.load(getClass().getResource("/ControladoresFXML/MenuPrincipal.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setTitle("Capit@l humano - Menu Principal");
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
    
}
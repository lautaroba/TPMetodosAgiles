package app.Controladores;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.Node;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import app.App;

public class MenuController implements Initializable{

    @FXML
    private Button emitirLicenciaButton;

    @FXML
    private Button emitirCopiaButton;

    @FXML
    private Button cancelarButton;

    @FXML
    private Button renovarLicenciaButton;

    @FXML
    private Button imprimirLicenciaButton;

    @FXML
    private Button listadoExpiradasButton;

    @FXML
    private Button listadoVigentesButton;

    @FXML
    private Button logoutButton;

    @FXML
    private Button darDeAltaButton;

    @FXML
    private Button modificarDatosButton;

    @FXML
    private Button eliminarUnTitularButton;
    
    @FXML
    private Label nombreUsuarioLabel;
    
    private Stage stage;
	private Scene scene;
	private Parent root;
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
        nombreUsuarioLabel.setText(App.gestor.administradorLogeado.nombre);
	}

    @FXML
    private void emitirLicencia(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("/ControladoresFXML/EmitirLicencia.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setTitle("Ventana de inicio");
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
    }


    @FXML
    private void logout(ActionEvent event) throws IOException {
        
        // ADEMAS DELOGEAR LA SESION

        root = FXMLLoader.load(getClass().getResource("/ControladoresFXML/Login.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setTitle("Ventana de inicio");
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
        
    }

    @FXML
    private void emitirCopia(ActionEvent event) {

    }


    @FXML
    private void renovarLicencia(ActionEvent event) {

    }

    @FXML
    private void imprimirLicencia(ActionEvent event) {

    }


    @FXML
    private void listadoExpiradas(ActionEvent event) {

    }


    @FXML
    private void listadoVigentes(ActionEvent event) {

    }

    @FXML
    private void darDeAlta(ActionEvent event) {
 
        try{
            root = FXMLLoader.load(getClass().getResource("/ControladoresFXML/AltaTitular.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Menu Principal - Sistema de licencias");
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch(IOException exception){
            exception.printStackTrace();
        }
    }

    @FXML
    private void modificarDatos(ActionEvent event) {

    }

    @FXML
    private void eliminar(ActionEvent event) {

    }

    @FXML
    private void cancelar(ActionEvent event) {
        Stage stage = (Stage) cancelarButton.getScene().getWindow();
        stage.close();
    }

    
}
package app.Controladores;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {

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
    
    private Stage stage;
	private Scene scene;
	private Parent root;

    @FXML
    private void emitirLicencia(ActionEvent event) {
 
        try{
            menuPrincipal(event);
        } catch(IOException exception){
            System.out.println("entro al catch");
        }
    }


    @FXML
    private void logout(ActionEvent event) {
 
    //     try{
    //         menuPrincipal(event);
    //     } catch(IOException exception){
    //         System.out.println("entro al catch");
    //     }
    }

    @FXML
    private void emitirCopia(ActionEvent event) {
 
        try{
            menuPrincipal(event);
        } catch(IOException exception){
            System.out.println("entro al catch");
        }
    }


    @FXML
    private void renovarLicencia(ActionEvent event) {
 
        try{
            menuPrincipal(event);
        } catch(IOException exception){
            System.out.println("entro al catch");
        }
    }

    @FXML
    private void imprimirLicencia(ActionEvent event) {
 
        try{
            menuPrincipal(event);
        } catch(IOException exception){
            System.out.println("entro al catch");
        }
    }


    @FXML
    private void listadoExpiradas(ActionEvent event) {
 
        try{
            menuPrincipal(event);
        } catch(IOException exception){
            System.out.println("entro al catch");
        }
    }


    @FXML
    private void listadoVigentes(ActionEvent event) {
 
        try{
            menuPrincipal(event);
        } catch(IOException exception){
            System.out.println("entro al catch");
        }
    }

    @FXML
    private void darDeAlta(ActionEvent event) {
 
        try{
            root = FXMLLoader.load(getClass().getResource("/ControladoresFXML/AltaTitular.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Capit@l humano - Menu Principal");
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch(IOException exception){
            exception.printStackTrace();
        }
    }

    @FXML
    private void modificarDatos(ActionEvent event) {
 
        try{
            menuPrincipal(event);
        } catch(IOException exception){
            System.out.println("entro al catch");
        }
    }

    @FXML
    private void eliminar(ActionEvent event) {
 
        try{
            menuPrincipal(event);
        } catch(IOException exception){
            System.out.println("entro al catch");
        }
    }
    

    @FXML
    private void cancelar(ActionEvent event) {

        Stage stage = (Stage) cancelarButton.getScene().getWindow();
        stage.close();
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
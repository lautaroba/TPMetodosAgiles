package app.Controladores;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.io.IOException;

public class AltaTitularController {

    @FXML
    private Button logoutButton;

    @FXML
    private Button aceptarButton;

    @FXML
    private Button cancelarButton;

    @FXML
    private TextField nombreTextfield;

    @FXML
    private TextField apellidoTextfield;

    @FXML
    private TextField direccionTextfield;

    @FXML
    private DatePicker fechaNacDatePicker;

    @FXML
    private SplitMenuButton claseSplitMenu;

    @FXML
    private SplitMenuButton grupoSplitMenu;

    @FXML
    private SplitMenuButton RHSplitMenu;

    @FXML
    private RadioButton siRadioButton;

    @FXML
    private RadioButton noRadioButton;
    
    private Stage stage;
	private Scene scene;
	private Parent root;



    @FXML
    private void logout(ActionEvent event) {
 
    //     try{
    //         menuPrincipal(event);
    //     } catch(IOException exception){
    //         System.out.println("entro al catch");
    //     }
    }

    @FXML
    private void cancelar(ActionEvent event) {

        Stage stage = (Stage) cancelarButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void aceptar(ActionEvent event) {

        // try{
            
        // } catch(IOException exception){
        //     exception.printStackTrace();
        // }
    }

    // public void menuPrincipal(ActionEvent event) throws IOException {

	// 	root = FXMLLoader.load(getClass().getResource("/ControladoresFXML/MenuPrincipal.fxml"));
	// 	stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	// 	stage.setTitle("Capit@l humano - Menu Principal");
	// 	scene = new Scene(root);
	// 	stage.setScene(scene);
	// 	stage.show();
	// }
    
}
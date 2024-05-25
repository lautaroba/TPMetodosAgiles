package app.Controladores;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.io.IOException;


public class AltaTitularController  {

    @FXML
    private Button logoutButton;

    @FXML
    private Button aceptarButton;

    @FXML
    private Button volverButton;
    
    @FXML
    private TextField dniTextfield;
    
    @FXML
    private TextField apellidoTextfield;
    
    @FXML
    private TextField direccionTextfield;
    
    @FXML
    private DatePicker fechaNacDatePicker;
    
    @FXML
    private MenuButton claseMenu;
    
    @FXML
    private MenuButton grupoMenu;
    
    @FXML
    private MenuButton RHMenu;
    
    @FXML
    private MenuButton tipoMenu;
    
    @FXML
    private RadioButton siRadioButton;
    
    @FXML
    private RadioButton noRadioButton;
    
    @FXML
    private TextField nombreTextfield;
    
    private Stage stage;
	private Scene scene;
	private Parent root;

    @FXML
    private void logout(ActionEvent event) {

    }

    @FXML
    public void volver(ActionEvent event) throws IOException {

		root = FXMLLoader.load(getClass().getResource("/ControladoresFXML/MenuPrincipal.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setTitle("Capit@l humano - Menu Principal");
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

    @FXML
    private void aceptar(ActionEvent event) {

    }

    @FXML
    private void handleMenuSelection(ActionEvent event) {
        MenuItem selectedItem = (MenuItem) event.getSource();
        MenuButton parentButton = (MenuButton) selectedItem.getParentPopup().getOwnerNode();
        parentButton.setText(selectedItem.getText());
    }
    
}
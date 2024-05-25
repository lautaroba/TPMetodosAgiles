package app.Controladores;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AltaTitularController implements Initializable{

    @FXML
    private Button logoutButton;

    @FXML
    private Button aceptarButton;

    @FXML
    private Button volverButton;

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


    @Override
	public void initialize(URL location, ResourceBundle resources) {
        cargarClaseLicencia();
        cargarGrupoSanguineo();
        cargarFactorRH();
    }

    private void cargarGrupoSanguineo(){
        grupoSplitMenu.setText("Seleccione Grupo Sanguineo");
        MenuItem grupoA = new MenuItem("Grupo A");
        MenuItem grupoB = new MenuItem("Grupo B");
        MenuItem grupoAB = new MenuItem("Grupo AB");
        MenuItem grupoO= new MenuItem("Grupo O");
        grupoSplitMenu.getItems().removeAll();
        grupoSplitMenu.getItems().addAll(grupoA,grupoB,grupoAB,grupoO);
    }

    private void cargarFactorRH(){
        RHSplitMenu.setText("Seleccione Factor RH");
        MenuItem positivo = new MenuItem("Positivo");
        MenuItem negativo = new MenuItem("Negativo");
        RHSplitMenu.getItems().removeAll();
        RHSplitMenu.getItems().addAll(positivo,negativo);
    }

    private void cargarClaseLicencia(){
        claseSplitMenu.setText("Seleccione Clase de Licencia");
        MenuItem claseA = new MenuItem("Clase A");
        MenuItem claseB = new MenuItem("Clase B");
        MenuItem claseC = new MenuItem("Clase C");
        MenuItem claseD = new MenuItem("Clase D");
        MenuItem claseE = new MenuItem("Clase E");
        MenuItem claseF = new MenuItem("Clase F");
        MenuItem claseG = new MenuItem("Clase G");
        claseSplitMenu.getItems().removeAll();
        claseSplitMenu.getItems().addAll(claseA, claseB, claseC, claseD, claseE, claseF, claseG);
    }

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

    
}
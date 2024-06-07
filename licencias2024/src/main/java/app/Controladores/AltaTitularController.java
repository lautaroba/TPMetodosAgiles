package app.Controladores;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.Node;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import app.App;
import app.DTOs.TitularDTO;
import app.Enunumenadores.Clase;
import app.Enunumenadores.FactorRH;
import app.Enunumenadores.GrupoSanguineo;
import app.Enunumenadores.TipoDocumento;
import jakarta.persistence.EntityExistsException;


public class AltaTitularController implements Initializable{

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
    private ComboBox<TipoDocumento> tipoComboBox = new ComboBox<>();

    @FXML
    private ComboBox<Clase> claseComboBox = new ComboBox<>();
    
    @FXML
    private ComboBox<GrupoSanguineo> grupoComboBox = new ComboBox<>();
    
    @FXML
    private ComboBox<FactorRH> factorComboBox = new ComboBox<>();
    
    @FXML
    private RadioButton siRadioButton;
    
    @FXML
    private RadioButton noRadioButton;
    
    @FXML
    private TextField nombreTextfield;
    
    @FXML
    private Label nombreUsuarioLabel;
    
    private Stage stage;
	private Scene scene;
	private Parent root;
    private TitularDTO titular;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        nombreUsuarioLabel.setText(App.gestor.administradorLogeado.nombre);
        tipoComboBox.getItems().setAll(TipoDocumento.values());
        claseComboBox.getItems().setAll(Clase.values());
        grupoComboBox.getItems().setAll(GrupoSanguineo.values());
        factorComboBox.getItems().setAll(FactorRH.values());

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
    private void logout(ActionEvent event) throws IOException{
		root = FXMLLoader.load(getClass().getResource("/ControladoresFXML/Login.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setTitle("Log in - Sistema de licencias");
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
    }

    @FXML
    public void volver(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("/ControladoresFXML/MenuPrincipal.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setTitle("Menu Principal - Sistema de licencias");
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

    @FXML
    private void aceptar(ActionEvent event) {

        // Corroborar q no hayan campos vacios  y que todos sean validos !! eso lo haces vos fernando y la concha de tu madre

        try{
            boolean seleccion;
            if(siRadioButton.isSelected())
                seleccion = true;
            else
                seleccion = false;
            
            titular = new TitularDTO(tipoComboBox.getValue(), Integer.parseInt(dniTextfield.getText()),
            nombreTextfield.getText(), apellidoTextfield.getText(), fechaNacDatePicker.getValue(), direccionTextfield.getText(),
            claseComboBox.getValue(), grupoComboBox.getValue(), factorComboBox.getValue(), seleccion);

            App.gestor.CrearTitular(titular);

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Sistema de licencias");
            alert.setContentText("Operacion se ha realizado con exito, el titular ha sido creado correctamente");
            alert.showAndWait(); // Mostrar la alerta y esperar a que el usuario la cierre
        }
        catch(EntityExistsException e){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Sistema de licencias");
            alert.setContentText("Ya existe un titular con el numero de documento ingresado, por favor intente nuevamente");
            alert.showAndWait(); // Mostrar la alerta y esperar a que el usuario la cierre
        }
        catch(Exception e){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Sistema de licencias");
            alert.setContentText("No se ha podido crear el titular, revise los campos nuevamente");
            alert.showAndWait(); // Mostrar la alerta y esperar a que el usuario la cierre
        }
    }

}
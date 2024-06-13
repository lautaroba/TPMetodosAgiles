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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import app.App;
import app.DTOs.AdministradorDTO;
import app.Enumeradores.Sexo;
import app.Enumeradores.TipoDocumento;
import jakarta.persistence.EntityExistsException;

public class ModificarAdmController implements Initializable{

    @FXML
    private Button aceptarButton;

    @FXML
    private Button volverButton;

    @FXML
    private Button buscarButton;
    
    @FXML
    private TextField dniTextfield;
    
    @FXML
    private TextField apellidoTextfield;
    
    @FXML
    private TextField direccionTextfield;

    @FXML
    private PasswordField pwField;

    @FXML
    private TextField emailTextfield;

    @FXML
    private DatePicker fechaNacDatePicker;
    
    @FXML
    private ComboBox<Sexo> sexoComboBox = new ComboBox<>();

    @FXML
    private ComboBox<TipoDocumento> tipoComboBox = new ComboBox<>();
    
    @FXML
    private TextField nombreTextfield;
    
    private Stage stage;
	private Scene scene;
	private Parent root;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sexoComboBox.getItems().setAll(Sexo.values());
        tipoComboBox.getItems().setAll(TipoDocumento.values());

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
    public void volver(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("/ControladoresFXML/Login.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setTitle("Inicio - Sistema de licencias");
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

    @FXML
    private void aceptar(ActionEvent event) {
        
        // Corroborar q no hayan campos vacios  y que todos sean validos !! eso lo haces vos fernando y la concha de tu madre

        try{
            App.gestor.ModificarAdministrador(new AdministradorDTO(Integer.parseInt(dniTextfield.getText()), nombreTextfield.getText(),
            apellidoTextfield.getText(), fechaNacDatePicker.getValue(), direccionTextfield.getText(),
            emailTextfield.getText(), pwField.getText(), tipoComboBox.getValue(), sexoComboBox.getValue()));
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Sistema de licencias");
            alert.setContentText("Operacion se ha realizado con exito, el administrador ha sido creado correctamente");
          
            alert.showAndWait(); // Mostrar la alerta y esperar a que el usuario la cierre
        }
        catch(EntityExistsException e){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Sistema de licencias");
            alert.setContentText("Ya existe un administrador con el nro de identificacion ingresado, por favor intente nuevamente.");
            alert.showAndWait(); // Mostrar la alerta y esperar a que el usuario la cierre
        }
        catch(Exception e){
            //e.printStackTrace();
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Sistema de licencias");
            alert.setContentText("No se ha podido crear el administrador, revise los campos nuevamente");
            alert.showAndWait(); // Mostrar la alerta y esperar a que el usuario la cierre
        }
    }

    
    @FXML
    private void buscar(ActionEvent event) {
        try {
            AdministradorDTO admin = App.gestor.BuscarAdministrador(new AdministradorDTO(Integer.parseInt(dniTextfield.getText())));
            tipoComboBox.setValue(admin.tipoDocumento);
            nombreTextfield.setText(admin.nombre);
            apellidoTextfield.setText(admin.apellido);
            fechaNacDatePicker.setValue(admin.fechaDeNacimiento);
            direccionTextfield.setText(admin.direccion);
            sexoComboBox.setValue(admin.sexo);
            emailTextfield.setText(admin.email);
            pwField.setText(admin.contraseña);
        } catch (Exception e) {
            limpiarCampos();
            e.printStackTrace();
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Sistema de licencias");
            alert.setContentText("No se ha podido encontrar el administrador, reingrese el numero de documento");
            alert.showAndWait(); // Mostrar la alerta y esperar a que el usuario la cierre
        }
    }

    private void limpiarCampos(){
        tipoComboBox.setValue(null);
        nombreTextfield.setText("");
        apellidoTextfield.setText("");
        fechaNacDatePicker.setValue(null);
        direccionTextfield.setText("");
        sexoComboBox.setValue(null);
        emailTextfield.setText("");
        pwField.setText("");
    }

}
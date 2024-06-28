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
import app.Excepciones.CamposIncompletosException;
import app.Excepciones.MenorDeEdadException;
import jakarta.persistence.EntityExistsException;

public class CrearAdmController implements Initializable {

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
    @FXML
    private Label contraErrorLabel;
    @FXML
    private Label direcErrorLabel;
    @FXML
    private Label emailErrorLabel;
    @FXML
    private Label fecNacErrorLabel;
    @FXML
    private Label nombreErrorLabel;
    @FXML
    private Label nroError;
    @FXML
    private Label sexoErrorLabel;
    @FXML
    private Label tipoErrorLabel;
    @FXML
    private Label apellidoErrorLabel;


    private Stage stage;
    private Scene scene;
    private Parent root;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        tipoErrorLabel.setVisible(false);
        nroError.setVisible(false);
        apellidoErrorLabel.setVisible(false);
        nombreErrorLabel.setVisible(false);
        sexoErrorLabel.setVisible(false);
        fecNacErrorLabel.setVisible(false);
        emailErrorLabel.setVisible(false);
        direcErrorLabel.setVisible(false);

        sexoComboBox.getItems().setAll(Sexo.values());
        tipoComboBox.getItems().setAll(TipoDocumento.values());

        dniTextfield.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
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
        try {
            comprobarCampos();
            App.gestor.CrearAdministrador(new AdministradorDTO(Integer.parseInt(dniTextfield.getText()),
                    nombreTextfield.getText(),
                    apellidoTextfield.getText(), fechaNacDatePicker.getValue(), direccionTextfield.getText(),
                    emailTextfield.getText(), pwField.getText(), tipoComboBox.getValue(), sexoComboBox.getValue()));

            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Sistema de licencias");
            alert.setContentText("Operacion se ha realizado con exito, el administrador ha sido creado correctamente");
            alert.showAndWait();
            limpiarCampos();
        }
        catch(MenorDeEdadException e){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Sistema de licencias");
            alert.setContentText(
                    "El titular debe tener al menos 17 años para obtener una licencia de conducir");
            alert.showAndWait();
        }
        catch (CamposIncompletosException e){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Sistema de licencias");
            alert.setContentText(
                    "Uno o mas campos estan incompletos, por favor completelos e intente nuevamente");
            alert.showAndWait();
        }
        catch (EntityExistsException e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Sistema de licencias");
            alert.setContentText(
                    "Ya existe un administrador con el nro de identificacion ingresado, por favor intente nuevamente.");
            alert.showAndWait();
        } catch (Exception e) {
            // e.printStackTrace();
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Sistema de licencias");
            alert.setContentText("No se ha podido crear el administrador, revise los campos nuevamente");
            alert.showAndWait();
        }
    }

    private void comprobarCampos() throws Exception {
        boolean bandera = false;
        if (tipoComboBox.getSelectionModel().isEmpty()) {
            tipoErrorLabel.setVisible(true);
            bandera = true;
        } else {
            tipoErrorLabel.setVisible(false);
        }
        if (nombreTextfield.getText().isEmpty()) {
            nombreErrorLabel.setVisible(true);
            bandera = true;
        } else {
            nombreErrorLabel.setVisible(false);
        }
        if (apellidoTextfield.getText().isEmpty()) {
            apellidoErrorLabel.setVisible(true);
            bandera = true;
        } else {
            apellidoErrorLabel.setVisible(false);
        }
        if (fechaNacDatePicker.getValue() == null) {
            fecNacErrorLabel.setVisible(true);
            bandera = true;
        } else {
            fecNacErrorLabel.setVisible(false);
        }
        if (direccionTextfield.getText().isEmpty()) {
            direcErrorLabel.setVisible(true);
            bandera = true;
        } else {
            direcErrorLabel.setVisible(false);
        }
        if (sexoComboBox.getSelectionModel().isEmpty()) {
            sexoErrorLabel.setVisible(true);
            bandera = true;
        } else {
            sexoErrorLabel.setVisible(false);
        }
        if (emailTextfield.getText().isEmpty()) {
            emailErrorLabel.setVisible(true);
            bandera = true;
        } else {
            emailErrorLabel.setVisible(false);
        }
        if (pwField.getText().isEmpty()) {
            contraErrorLabel.setVisible(true);
            bandera = true;
        } else {
            contraErrorLabel.setVisible(false);
        }
        if (dniTextfield.getText().isEmpty()) {
            nroError.setVisible(true);
            bandera = true;
        } else {
            nroError.setVisible(false);
        }
        
        if(bandera) 
            throw new CamposIncompletosException();
    }

    private void limpiarCampos() {
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
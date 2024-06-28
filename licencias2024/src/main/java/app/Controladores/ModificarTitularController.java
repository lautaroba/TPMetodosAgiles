package app.Controladores;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import app.App;
import app.DTOs.TitularDTO;
import app.Enumeradores.FactorRH;
import app.Enumeradores.GrupoSanguineo;
import app.Enumeradores.TipoDocumento;
import app.Excepciones.CamposIncompletosException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/*
 * TAREA: Interfaz de modificar titular
 */

public class ModificarTitularController implements Initializable {

    @FXML
    private Label RHErrorLabel;
    @FXML
    private Button aceptarButton;
    @FXML
    private Button buscarButton;
    @FXML
    private Label apellidoErrorLabel;
    @FXML
    private TextField apellidoTextfield;
    @FXML
    private Label direcErrorLabel;
    @FXML
    private TextField direccionTextfield;
    @FXML
    private TextField dniTextfield;
    @FXML
    private Label donanteErrorLabel;
    @FXML
    private ComboBox<FactorRH> factorComboBox;
    @FXML
    private Label fecNacErrorLabel;
    @FXML
    private DatePicker fechaNacDatePicker;
    @FXML
    private ComboBox<GrupoSanguineo> grupoComboBox;
    @FXML
    private Label grupoErrorLabel;
    @FXML
    private Button logoutButton;
    @FXML
    private RadioButton noRadioButton;
    @FXML
    private Label nombreErrorLabel;
    @FXML
    private TextField nombreTextfield;
    @FXML
    private Label nombreUsuarioLabel;
    @FXML
    private TextArea observacionesTextarea;
    @FXML
    private RadioButton siRadioButton;
    @FXML
    private ComboBox<TipoDocumento> tipoComboBox;
    @FXML
    private Label tipoErrorLabel;
    @FXML
    private Button volverButton;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        nombreUsuarioLabel.setText(App.gestor.administradorLogeado.nombre);
        tipoComboBox.getItems().setAll(TipoDocumento.values());
        grupoComboBox.getItems().setAll(GrupoSanguineo.values());
        factorComboBox.getItems().setAll(FactorRH.values());
        ToggleGroup radioButtons = new ToggleGroup();
        siRadioButton.setToggleGroup(radioButtons);
        noRadioButton.setToggleGroup(radioButtons);
        tipoErrorLabel.setVisible(false);
        nombreErrorLabel.setVisible(false);
        apellidoErrorLabel.setVisible(false);
        fecNacErrorLabel.setVisible(false);
        direcErrorLabel.setVisible(false);
        grupoErrorLabel.setVisible(false);
        RHErrorLabel.setVisible(false);
        donanteErrorLabel.setVisible(false);
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
    private void buscar(ActionEvent event) {
        try {
            TitularDTO titular = App.gestor.BuscarTitular(new TitularDTO(Integer.parseInt(dniTextfield.getText())));
            tipoComboBox.setValue(titular.tipoDocumento);
            dniTextfield.setText(String.valueOf(titular.nroDNI));
            nombreTextfield.setText(titular.nombre);
            apellidoTextfield.setText(titular.apellido);
            fechaNacDatePicker.setValue(titular.fechaDeNacimiento);
            direccionTextfield.setText(titular.direccion);
            grupoComboBox.setValue(titular.grupoSanguineo);
            factorComboBox.setValue(titular.factorRH);
            observacionesTextarea.setText(titular.limitacion);
            siRadioButton.setSelected(titular.donante);
            noRadioButton.setSelected(titular.donante);
        } catch (Exception e) {
            //e.printStackTrace();
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Sistema de licencias");
            alert.setContentText("No se ha podido encontrar el titular, reingrese el numero de documento");
            alert.showAndWait();
        }
    }

    @FXML
    private void aceptar(ActionEvent event) {
        try {
            comprobarCampos();
            App.gestor.ModificarTitular(new TitularDTO(tipoComboBox.getValue(), Integer.parseInt(dniTextfield.getText()),
                    nombreTextfield.getText(), apellidoTextfield.getText(), fechaNacDatePicker.getValue(), direccionTextfield.getText(),
                    grupoComboBox.getValue(), factorComboBox.getValue(), siRadioButton.isSelected() ? true : false,observacionesTextarea.getText()));
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Sistema de licencias");
            alert.setContentText("Operacion se ha realizado con exito, el administrador ha sido actualizado correctamente");
            alert.showAndWait();
            limpiarCampos();
        } catch (CamposIncompletosException e){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Sistema de licencias");
            alert.setContentText(
                    "Uno o mas campos estan incompletos, por favor completelos e intente nuevamente");
            alert.showAndWait();
        }catch (Exception e) {
            // e.printStackTrace();
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Sistema de licencias");
            alert.setContentText("No se ha podido modificar el administrador, revise los campos nuevamente");
            alert.showAndWait();
        }
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/ControladoresFXML/Login.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Log in - Sistema de licencias");
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void volver(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/ControladoresFXML/MenuPrincipal.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Menu Principal - Sistema de licencias");
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
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
        if (grupoComboBox.getSelectionModel().isEmpty()) {
            grupoErrorLabel.setVisible(true);
            bandera = true;
        } else {
            grupoErrorLabel.setVisible(false);
        }
        if (factorComboBox.getSelectionModel().isEmpty()) {
            RHErrorLabel.setVisible(true);
            bandera = true;
        } else {
            RHErrorLabel.setVisible(false);
        }
        if (!siRadioButton.isSelected() && !noRadioButton.isSelected()) {
            donanteErrorLabel.setVisible(true);
            bandera = true;
        } else {
            donanteErrorLabel.setVisible(false);
        }
        
        if(bandera) 
            throw new CamposIncompletosException();
    }

    private void limpiarCampos(){
        tipoComboBox.setValue(null);
        dniTextfield.setText("");
        nombreTextfield.setText("");
        apellidoTextfield.setText("");
        fechaNacDatePicker.setValue(null);
        siRadioButton.setSelected(false);
        noRadioButton.setSelected(false);
        direccionTextfield.setText("");
        factorComboBox.setValue(null);
        observacionesTextarea.setText("");
        dniTextfield.setText("");
    }
}
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.Node;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import app.App;
import app.DTOs.TitularDTO;
import app.Enumeradores.FactorRH;
import app.Enumeradores.GrupoSanguineo;
import app.Enumeradores.TipoDocumento;
import app.Excepciones.CamposIncompletosException;
import app.Excepciones.MenorDeEdadException;
import jakarta.persistence.EntityExistsException;

/*
 * TAREA: Interfaz de alta de titular
 */

public class AltaTitularController implements Initializable {

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
    @FXML
    private Label tipoErrorLabel;
    @FXML
    private Label nroDocErrorLabel;
    @FXML
    private Label nombreErrorLabel;
    @FXML
    private Label apellidoErrorLabel;
    @FXML
    private Label fecNacErrorLabel;
    @FXML
    private Label direcErrorLabel;
    @FXML
    private Label grupoErrorLabel;
    @FXML
    private Label RHErrorLabel;
    @FXML
    private Label donanteErrorLabel;
    @FXML
    private TextArea observacionesTextarea;

    private Stage stage;
    private Scene scene;
    private Parent root;
    private TitularDTO titular;

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
        nroDocErrorLabel.setVisible(false);
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
    private void logout(ActionEvent event) throws IOException {
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

    private void comprobarCampos() throws Exception {
        boolean bandera = false;
        if (tipoComboBox.getSelectionModel().isEmpty()) {
            tipoErrorLabel.setVisible(true);
            bandera = true;
        } else {
            tipoErrorLabel.setVisible(false);
        }
        if (dniTextfield.getText().isEmpty()) {
            nroDocErrorLabel.setVisible(true);
            bandera = true;
        } else {
            nroDocErrorLabel.setVisible(false);
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

    @FXML
    private void aceptar(ActionEvent event) {
        try {
            comprobarCampos();
            titular = new TitularDTO(tipoComboBox.getValue(), Integer.parseInt(dniTextfield.getText()),
                    nombreTextfield.getText(), apellidoTextfield.getText(), fechaNacDatePicker.getValue(),
                    direccionTextfield.getText(),
                    grupoComboBox.getValue(), factorComboBox.getValue(),
                    siRadioButton.isSelected(), observacionesTextarea.getText());
            App.gestor.CrearTitular(titular);
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Sistema de licencias");
            alert.setContentText("Operacion se ha realizado con exito, el titular ha sido creado correctamente");
            alert.showAndWait();
            limpiarCampos();
        } catch (CamposIncompletosException e){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Sistema de licencias");
            alert.setContentText(
                    "Uno o mas campos estan incompletos, por favor completelos e intente nuevamente");
            alert.showAndWait();
        }
        catch(MenorDeEdadException e){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Sistema de licencias");
            alert.setContentText(
                    "El titular debe tener al menos 17 años para obtener una licencia de conducir");
            alert.showAndWait();
        }
        catch (EntityExistsException e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Sistema de licencias");
            alert.setContentText(
                    "Ya existe un titular con el numero de documento ingresado, por favor intente nuevamente");
            alert.showAndWait();
        } catch (Exception e) {
            //e.printStackTrace();
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Sistema de licencias");
            alert.setContentText("No se ha podido crear el titular, revise los campos nuevamente");
            alert.showAndWait();
        }
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
        grupoComboBox.setValue(null);
    }
}
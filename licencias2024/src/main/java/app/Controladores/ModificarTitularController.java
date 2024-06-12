package app.Controladores;

import java.io.IOException;
import java.time.LocalDate;

import app.App;
import app.DTOs.TitularDTO;
import app.Enumeradores.Clase;
import app.Enumeradores.FactorRH;
import app.Enumeradores.GrupoSanguineo;
import app.Enumeradores.TipoDocumento;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
import javafx.stage.Stage;

public class ModificarTitularController {

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
    private ComboBox<Clase> claseComboBox;
    @FXML
    private Label claseErrorLabel;
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

    @FXML
    private void buscar() {

        // try {
        //     TitularDTO titular = App.gestor.BuscarTitular(new TitularDTO(Integer.parseInt(buscarTextfield.getText())));

        //     tipoTextfield.setText(titular.tipoDocumento.toString());
        //     dniTextfield.setText(String.valueOf(titular.nroDNI));
        //     nombreTextfield.setText(titular.nombre);
        //     apellidoTextfield.setText(titular.apellido);
        //     fecNacTextfield.setText(titular.fechaDeNacimiento.toString());
        //     direccionTextfield.setText(titular.direccion);
        //     claseTextfield.setText(titular.clase.toString());
        //     grupoTextfield.setText(titular.grupoSanguineo.toString());
        //     factorTextfield.setText(titular.factorRH.toString());
        //     observacionesTextarea.setText(titular.limitacion);

        //     if(titular.donante == true)
        //         donanteTextfield.setText("SI");
        //     else
        //         donanteTextfield.setText("NO");

        //     int plazo = App.gestor.CalcularVigenciaLicencia(titular);
        //     fechaInicio = LocalDate.now();
        //     fechaFinal = LocalDate.of(fechaInicio.plusYears(plazo).getYear(), titular.fechaDeNacimiento.getMonth(), titular.fechaDeNacimiento.getDayOfMonth());
        //     vigenciaTextfield.setText(fechaFinal.toString());
        //     costoTotal=App.gestor.CalcularCostoLicencia(titular);
        //     costoTextfield.setText(String.valueOf(costoTotal));    

        // } catch (Exception e) {
        //     e.printStackTrace();
        //     limpiarCampos();
        //     Alert alert = new Alert(AlertType.ERROR);
        //     alert.setTitle("Sistema de licencias");
        //     alert.setContentText("No se ha podido encontrar el titular, reingrese el numero de documento");
        //     alert.showAndWait(); // Mostrar la alerta y esperar a que el usuario la cierre
        // }
    }

    @FXML
    void aceptar(ActionEvent event) {

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
}
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
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.ResourceBundle;

import app.App;
import app.DTOs.LicenciaDTO;
import app.DTOs.TitularDTO;
import app.Enunumenadores.Clase;
import app.Enunumenadores.FactorRH;
import app.Enunumenadores.GrupoSanguineo;
import app.Enunumenadores.TipoDocumento;

public class EmitirLicenciaController implements Initializable {

    @FXML
    private Button logoutButton;
    @FXML
    private Button volverButton;
    @FXML
    private Button buscarButton;
    @FXML
    private Button aceptarButton;
    @FXML
    private Button calcularButton;
    @FXML
    private TextField nombreTextfield;
    @FXML
    private TextField apellidoTextfield;
    @FXML
    private TextField direccionTextfield;
    @FXML
    private TextField dniTextfield;
    @FXML
    private TextField tipoTextfield;
    @FXML
    private TextField claseTextfield;
    @FXML
    private TextField grupoTextfield;
    @FXML
    private TextField factorTextfield;
    @FXML
    private TextField donanteTextfield;
    @FXML
    private TextField fecNacTextfield;
    @FXML
    private TextField buscarTextfield;
    @FXML
    private TextField vigenciaTextfield;
    @FXML
    private TextField costoTextfield;
    @FXML
    private Label nombreUsuarioLabel;
    @FXML
    private TextArea observacionesTextarea;

    private Stage stage;
    private Scene scene;
    private Parent root;
    private TitularDTO titular;
    private LocalDate fechaInicio;
    private LocalDate fechaFinal;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        nombreUsuarioLabel.setText(App.gestor.administradorLogeado.nombre);
        buscarTextfield.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    // Si el nuevo valor contiene caracteres no numéricos, reemplace el texto con el
                    // antiguo valor
                    buscarTextfield.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }

    @FXML
    private void buscar() {

        try {
            titular = App.gestor.BuscarTitular(new TitularDTO(Integer.parseInt(buscarTextfield.getText())));

            tipoTextfield.setText(titular.tipoDocumento.toString());
            dniTextfield.setText(String.valueOf(titular.nroDNI));
            nombreTextfield.setText(titular.nombre);
            apellidoTextfield.setText(titular.apellido);
            fecNacTextfield.setText(titular.fechaDeNacimiento.toString());
            direccionTextfield.setText(titular.direccion);
            claseTextfield.setText(titular.clase.toString());
            grupoTextfield.setText(titular.grupoSanguineo.toString());
            factorTextfield.setText(titular.factorRH.toString());
            observacionesTextarea.setText(titular.limitacion);

            if(titular.donante == true)
                donanteTextfield.setText("SI");
            else
                donanteTextfield.setText("NO");

            int plazo = App.gestor.CalcularVigenciaLicencia(titular);
            fechaInicio = LocalDate.now();
            fechaFinal = LocalDate.of(fechaInicio.plusYears(plazo).getYear(), titular.fechaDeNacimiento.getMonth(), titular.fechaDeNacimiento.getDayOfMonth());
            vigenciaTextfield.setText(fechaFinal.toString());
            costoTextfield.setText(String.valueOf(App.gestor.CalcularCostoLicencia(titular)));

        } catch (Exception e) {
            e.printStackTrace();
            limpiarCampos();
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Sistema de licencias");
            alert.setContentText("No se ha podido encontrar el titular, reingrese el numero de documento");
            alert.showAndWait(); // Mostrar la alerta y esperar a que el usuario la cierre
        }
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/ControladoresFXML/LoginFXML.fxml"));
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
        try {

            // Busco si existe alguna licencia para el titular en cuestion con fecha de expiracion mayor a la actual

            LicenciaDTO licencia = new LicenciaDTO(titular, App.gestor.administradorLogeado, fechaInicio, fechaFinal, true);
            App.gestor.CrearLicencia(licencia);

            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Sistema de licencias");
            alert.setContentText("Licencia generada con exito.");
            alert.showAndWait(); // Mostrar la alerta y esperar a que el usuario la cierre
        }catch(NoSuchElementException e){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Sistema de licencias");
            alert.setContentText("No se ha podido generar la licencia, ya existe una licencia activa.");
            alert.showAndWait(); // Mostrar la alerta y esperar a que el usuario la cierre
        } 
        catch (Exception e) {
            //limpiarCampos();
            e.printStackTrace();
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Sistema de licencias");
            alert.setContentText("No se ha podido generar la licencia, revise los datos nuevamente.");
            alert.showAndWait(); // Mostrar la alerta y esperar a que el usuario la cierre
        }
    }

    private void limpiarCampos(){
        tipoTextfield.setText("");
        dniTextfield.setText("");
        nombreTextfield.setText("");
        apellidoTextfield.setText("");
        fecNacTextfield.setText("");
        direccionTextfield.setText("");
        claseTextfield.setText("");
        grupoTextfield.setText("");
        factorTextfield.setText("");
        donanteTextfield.setText("");
        observacionesTextarea.setText("");
        vigenciaTextfield.setText("");
        costoTextfield.setText("");
    }
}

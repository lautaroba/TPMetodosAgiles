package app.Controladores;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

import app.DTOs.TitularDTO;
import app.Enunumenadores.FactorRH;
import app.Enunumenadores.TipoDocumento;


public class AltaTitularController{

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
    private ComboBox claseComboBox;
    
    @FXML
    private ComboBox grupoComboBox;
    
    @FXML
    private ComboBox factorComboBox;
    
    @FXML
    private ComboBox<String> tipoMenu;
    
    @FXML
    private RadioButton siRadioButton;
    
    @FXML
    private RadioButton noRadioButton;
    
    @FXML
    private TextField nombreTextfield;
    
    private Stage stage;
	private Scene scene;
	private Parent root;
    private TitularDTO titular;

    @FXML
    private void logout(ActionEvent event) throws IOException{
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

        //titular = new TitularDTO(tipoMenu, 0, null, null, null, null, null, null, null, false)

    }
    
    @FXML
    private void cargarOpcionesTipo(ActionEvent event){
    //     tipoMenu.setItems(FXCollections.observableArrayList("DNI", "LC", "LE"));
    }

}
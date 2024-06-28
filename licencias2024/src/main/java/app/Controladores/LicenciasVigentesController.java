package app.Controladores;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import app.App;
import app.DTOs.LicenciaDTO;
import app.Enumeradores.*;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.util.Callback;

public class LicenciasVigentesController implements Initializable {

    @FXML
    private TableColumn<LicenciaDTO, String> apellido;
    @FXML
    private TableColumn<LicenciaDTO, String> donante;
    @FXML
    private TableColumn<LicenciaDTO, FactorRH> factorRH;
    @FXML
    private TableColumn<LicenciaDTO, LocalDate> fechaEmision;
    @FXML
    private TableColumn<LicenciaDTO, LocalDate> fechaExpiracion;
    @FXML
    private TableColumn<LicenciaDTO, GrupoSanguineo> grupoSanguineo;
    @FXML
    private TableColumn<LicenciaDTO, String> nombre;
    @FXML
    private TableColumn<LicenciaDTO, Integer> nroDoc;
    @FXML
    private TableView<LicenciaDTO> tablaLicExp;
    @FXML
    private TableColumn<LicenciaDTO, TipoDocumento> tipo;
    @FXML
    private TableColumn<LicenciaDTO, Clase> clase;
    @FXML
    private Button volverButton;
    @FXML
    private Button logoutButton;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<LicenciaDTO> licenciasVigentes = App.gestor.ListadoLicenciasVigentes();
        ObservableList<LicenciaDTO> datosLicenciasVigentes = FXCollections.observableList(licenciasVigentes);

        apellido.setCellValueFactory(
                new Callback<CellDataFeatures<LicenciaDTO, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(CellDataFeatures<LicenciaDTO, String> l) {
                        return new ReadOnlyObjectWrapper<String>(l.getValue().titular.apellido);
                    }
                });
        nombre.setCellValueFactory(
                new Callback<CellDataFeatures<LicenciaDTO, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(CellDataFeatures<LicenciaDTO, String> l) {
                        return new ReadOnlyObjectWrapper<String>(l.getValue().titular.nombre);
                    }
                });
        nroDoc.setCellValueFactory(
                new Callback<CellDataFeatures<LicenciaDTO, Integer>, ObservableValue<Integer>>() {
                    public ObservableValue<Integer> call(CellDataFeatures<LicenciaDTO, Integer> l) {
                        return new ReadOnlyObjectWrapper<Integer>(l.getValue().titular.nroDNI);
                    }
                });
        tipo.setCellValueFactory(
                new Callback<CellDataFeatures<LicenciaDTO, TipoDocumento>, ObservableValue<TipoDocumento>>() {
                    public ObservableValue<TipoDocumento> call(CellDataFeatures<LicenciaDTO, TipoDocumento> l) {
                        return new ReadOnlyObjectWrapper<TipoDocumento>(l.getValue().titular.tipoDocumento);
                    }
                });
        fechaEmision.setCellValueFactory(
                new Callback<CellDataFeatures<LicenciaDTO, LocalDate>, ObservableValue<LocalDate>>() {
                    public ObservableValue<LocalDate> call(CellDataFeatures<LicenciaDTO, LocalDate> l) {
                        return new ReadOnlyObjectWrapper<LocalDate>(l.getValue().fechaDeEmision);
                    }
                });
        fechaExpiracion.setCellValueFactory(
                new Callback<CellDataFeatures<LicenciaDTO, LocalDate>, ObservableValue<LocalDate>>() {
                    public ObservableValue<LocalDate> call(CellDataFeatures<LicenciaDTO, LocalDate> l) {
                        return new ReadOnlyObjectWrapper<LocalDate>(l.getValue().fechaDeExpiracion);
                    }
                });
        grupoSanguineo.setCellValueFactory(
                new Callback<CellDataFeatures<LicenciaDTO, GrupoSanguineo>, ObservableValue<GrupoSanguineo>>() {
                    public ObservableValue<GrupoSanguineo> call(CellDataFeatures<LicenciaDTO, GrupoSanguineo> l) {
                        return new ReadOnlyObjectWrapper<GrupoSanguineo>(l.getValue().titular.grupoSanguineo);
                    }
                });
        factorRH.setCellValueFactory(
                new Callback<CellDataFeatures<LicenciaDTO, FactorRH>, ObservableValue<FactorRH>>() {
                    public ObservableValue<FactorRH> call(CellDataFeatures<LicenciaDTO, FactorRH> l) {
                        return new ReadOnlyObjectWrapper<FactorRH>(l.getValue().titular.factorRH);
                    }
                });
        donante.setCellValueFactory(
                new Callback<CellDataFeatures<LicenciaDTO, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(CellDataFeatures<LicenciaDTO, String> l) {
                        return new ReadOnlyObjectWrapper<String>(l.getValue().titular.donante ? "SI" : "NO");
                    }
                });
        clase.setCellValueFactory(
                new Callback<CellDataFeatures<LicenciaDTO, Clase>, ObservableValue<Clase>>() {
                    public ObservableValue<Clase> call(CellDataFeatures<LicenciaDTO, Clase> l) {
                        return new ReadOnlyObjectWrapper<Clase>(l.getValue().clase);
                    }
                });
        tablaLicExp.setItems(datosLicenciasVigentes);
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/ControladoresFXML/Login.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Ventana de inicio");
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

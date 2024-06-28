package app.Controladores;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.Node;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import app.App;
import app.DTOs.LicenciaDTO;
import app.DTOs.TitularDTO;
import app.Enumeradores.Clase;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

public class CopiaLicenciaController implements Initializable {

    @FXML
    private Button logoutButton;
    @FXML
    private Button volverButton;
    @FXML
    private Button buscarButton;
    @FXML
    private Button emitirCopiaButton;
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
    @FXML
    private TableColumn<LicenciaDTO, Integer> nroDocColumn;
    @FXML
    private TableColumn<LicenciaDTO, String> nombreColumn;
    @FXML
    private TableColumn<LicenciaDTO, String> apellidoColumn;
    @FXML
    private TableColumn<LicenciaDTO, Clase> claseColumn;
    @FXML
    private TableView<LicenciaDTO> tablaLic;

    private Stage stage;
    private Scene scene;
    private Parent root;
    private LicenciaDTO licencia;
    private Integer costoTotal;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nombreUsuarioLabel.setText(App.gestor.administradorLogeado.nombre);
        buscarTextfield.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    buscarTextfield.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }

    @FXML
    private void buscar() {
        try {
            List<LicenciaDTO> licenciasVigentes = App.gestor
                    .BuscarLicenciasTitular(new TitularDTO(Integer.parseInt(buscarTextfield.getText())),
                            LocalDate.now());
            ObservableList<LicenciaDTO> datosLicenciasVigentes = FXCollections.observableList(licenciasVigentes);

            nroDocColumn.setCellValueFactory(
                    new Callback<CellDataFeatures<LicenciaDTO, Integer>, ObservableValue<Integer>>() {
                        public ObservableValue<Integer> call(CellDataFeatures<LicenciaDTO, Integer> l) {
                            return new ReadOnlyObjectWrapper<Integer>(l.getValue().titular.nroDNI);
                        }
                    });
            nombreColumn.setCellValueFactory(
                    new Callback<CellDataFeatures<LicenciaDTO, String>, ObservableValue<String>>() {
                        public ObservableValue<String> call(CellDataFeatures<LicenciaDTO, String> l) {
                            return new ReadOnlyObjectWrapper<String>(l.getValue().titular.nombre);
                        }
                    });
            apellidoColumn.setCellValueFactory(
                    new Callback<CellDataFeatures<LicenciaDTO, String>, ObservableValue<String>>() {
                        public ObservableValue<String> call(CellDataFeatures<LicenciaDTO, String> l) {
                            return new ReadOnlyObjectWrapper<String>(l.getValue().titular.apellido);
                        }
                    });
            claseColumn.setCellValueFactory(
                    new Callback<CellDataFeatures<LicenciaDTO, Clase>, ObservableValue<Clase>>() {
                        public ObservableValue<Clase> call(CellDataFeatures<LicenciaDTO, Clase> l) {
                            return new ReadOnlyObjectWrapper<Clase>(l.getValue().clase);
                        }
                    });
            tablaLic.setItems(datosLicenciasVigentes);
        } catch (Exception e) {
            // e.printStackTrace();
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Sistema de licencias");
            alert.setContentText("No se ha encontrado ningun titular con el numero de documento ingresado.");
            alert.showAndWait();
            limpiarCampos();
        }
    }

    @FXML
    private void seleccionar(MouseEvent event) {
        try {
            if (tablaLic.getSelectionModel().getSelectedItem() != null) {
                licencia = tablaLic.getSelectionModel().getSelectedItem();
                setDatos();
            }
        } catch (Exception e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Sistema de licencias");
            alert.setContentText("Ha ocurrido un error, intente nuevamente o llame a un administrador.");
            alert.showAndWait();
            limpiarCampos();
        }
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

    @FXML
    private void emitirCopia(ActionEvent event) {
        try {
            ImprimirLicencia();

            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Sistema de licencias");
            alert.setContentText("Copia generada con exito.");
            alert.showAndWait();
        } catch (Exception e) {
            limpiarCampos();
            e.printStackTrace();
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Sistema de licencias");
            alert.setContentText(
                    "No se ha podido generar la copia de la licencia, intente nuevamente o llame a un administrador.");
            alert.showAndWait();
        }
    }

    private void setDatos() throws Exception {
        tipoTextfield.setText(licencia.titular.tipoDocumento.toString());
        dniTextfield.setText(String.valueOf(licencia.titular.nroDNI));
        nombreTextfield.setText(licencia.titular.nombre);
        apellidoTextfield.setText(licencia.titular.apellido);
        fecNacTextfield.setText(licencia.titular.fechaDeNacimiento.toString());
        direccionTextfield.setText(licencia.titular.direccion);
        claseTextfield.setText(licencia.clase.toString());
        grupoTextfield.setText(licencia.titular.grupoSanguineo.toString());
        factorTextfield.setText(licencia.titular.factorRH.toString());
        observacionesTextarea.setText(licencia.titular.limitacion);
        donanteTextfield.setText(licencia.titular.donante ? "SI" : "NO");
        vigenciaTextfield.setText(licencia.fechaDeExpiracion.toString());
        costoTotal = App.gestor.CalcularCostoLicencia(licencia.titular, licencia.clase);
        costoTextfield.setText(String.valueOf(costoTotal));
    }

    private static void drawRoundedRectangle(PDPageContentStream contentStream, float x, float y, float width,
            float height, float radius) throws IOException {
        contentStream.moveTo(x + radius, y);
        contentStream.lineTo(x + width - radius, y);
        contentStream.curveTo(x + width - radius, y, x + width, y, x + width, y + radius);
        contentStream.lineTo(x + width, y + height - radius);
        contentStream.curveTo(x + width, y + height - radius, x + width, y + height, x + width - radius, y + height);
        contentStream.lineTo(x + radius, y + height);
        contentStream.curveTo(x + radius, y + height, x, y + height, x, y + height - radius);
        contentStream.lineTo(x, y + radius);
        contentStream.curveTo(x, y + radius, x, y, x + radius, y);
        contentStream.closeAndStroke();
    }

    private void ImprimirLicencia() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Seleccionar directorio para guardar PDFs");
        File selectedDirectory = directoryChooser.showDialog(stage);
        if (selectedDirectory == null)
            return;

        File fileCarnet = new File(selectedDirectory, licencia.titular.nombre + "_licencia.pdf");
        File fileComprobante = new File(selectedDirectory, licencia.titular.nombre + "_comprobante.pdf");

        try (
                PDDocument documentLicencia = new PDDocument();
                PDDocument documentComprobante = new PDDocument()) {
            PDPage pageLicencia = new PDPage();
            documentLicencia.addPage(pageLicencia);
            PDPage pageComprobante = new PDPage(PDRectangle.A4);
            documentComprobante.addPage(pageComprobante);

            try (PDPageContentStream contentStreamLicencia = new PDPageContentStream(documentLicencia, pageLicencia)) {
                drawLicencia(contentStreamLicencia, documentLicencia);
            }
            try (PDPageContentStream contentStreamComprobante = new PDPageContentStream(documentComprobante,
                    pageComprobante)) {

                InputStream imageStream = EmitirLicenciaController.class.getResourceAsStream("/images/logo.png");
                PDImageXObject logo = PDImageXObject.createFromByteArray(documentComprobante, imageStream.readAllBytes(),
                "logo.png");
                contentStreamComprobante.drawImage(logo, 20, 700, 150, 150);
                drawComprobante(contentStreamComprobante);
            }
            documentLicencia.save(fileCarnet);
            documentComprobante.save(fileComprobante);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void drawLicencia(PDPageContentStream contentStreamLicencia, PDDocument documentLicencia)
            throws IOException {
        float marginLeft = 50;
        float marginTop = 750;
        float width = 500;
        float height = 250;
        float radius = 20;

        drawRoundedRectangle(contentStreamLicencia, marginLeft, marginTop - height, width, height, radius);

        contentStreamLicencia.beginText();
        contentStreamLicencia.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 16);
        contentStreamLicencia.newLineAtOffset(230, 725);
        contentStreamLicencia.showText("Licencia de Conducir");
        contentStreamLicencia.endText();

        addText(contentStreamLicencia, "N° Licencia: " + "ID", 200, 665, 12);
        addText(contentStreamLicencia, "Apellido: " + licencia.titular.apellido, 200, 650, 12);
        addText(contentStreamLicencia, "Nombre: " + licencia.titular.nombre, 200, 635, 12);
        addText(contentStreamLicencia, "Domicilio: " + licencia.titular.direccion, 200, 620, 12);
        addText(contentStreamLicencia, "Fecha de nac.: " + licencia.titular.fechaDeNacimiento.toString(), 200, 605, 12);
        addText(contentStreamLicencia, "Otorgamiento: " + licencia.fechaDeEmision.toString(), 200, 590, 12);
        addText(contentStreamLicencia, "Vencimiento: " + licencia.fechaDeExpiracion.toString(), 200, 575, 12);
        addText(contentStreamLicencia, "Clases: " + licencia.clase.toString(), 400, 665, 12);
        InputStream imageStream = EmitirLicenciaController.class.getResourceAsStream("/images/foto.png");
        PDImageXObject image = PDImageXObject.createFromByteArray(documentLicencia, imageStream.readAllBytes(),
                "foto.png");
        contentStreamLicencia.drawImage(image, 75, 575, 100, 100);

        // DORSO
        // Dibujar el recuadro con esquinas redondeadas
        float marginLeftDorso = 50;
        float marginTopDorso = 400;
        float widthDorso = 500;
        float heightDorso = 250;
        float radiusDorso = 20;

        drawRoundedRectangle(contentStreamLicencia, marginLeftDorso, marginTopDorso - heightDorso, widthDorso,
                heightDorso, radiusDorso);

        addText(contentStreamLicencia, "Donante: " + (licencia.titular.donante ? "Si" : "No"), 75, 350, 12);
        addText(contentStreamLicencia, "Grupo Sanguineo: " + licencia.titular.grupoSanguineo.toString(), 75, 330, 12);
        addText(contentStreamLicencia, "Tipo Doc: " + licencia.titular.tipoDocumento.toString(), 75, 310, 12);
        addText(contentStreamLicencia, "Nro Doc: " + licencia.titular.nroDNI, 75, 290, 12);
        addText(contentStreamLicencia, "Observaciones: " + licencia.titular.limitacion, 75, 200, 6);
        addText(contentStreamLicencia, "Descripcion de clase: " + licencia.clase.getNombreClase(), 75, 180, 6);
    }

    private void drawComprobante(PDPageContentStream contentStream) throws Exception {

        contentStream.setNonStrokingColor(Color.LIGHT_GRAY);
        contentStream.addRect(20, 140, 200, 360);
        contentStream.fill();
        contentStream.addRect(320, 140, 150, 360);
        contentStream.fill();

        contentStream.setNonStrokingColor(Color.BLACK);

        contentStream.beginText();
        contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 24);
        contentStream.newLineAtOffset(200, 760);
        contentStream.showText("FACTURA SIMPLIFICADA #1234");
        contentStream.endText();

        // Company details
        contentStream.beginText();
        contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 12);
        contentStream.newLineAtOffset(20, 700);
        contentStream.showText("Compania: Sistema de Licencias UTN");
        contentStream.newLineAtOffset(0, -15);
        contentStream.showText("Direccion: Lavaisse, 610");
        contentStream.newLineAtOffset(0, -15);
        contentStream.showText("Localidad: Santa Fe (3000), Santa Fe, Argentina");
        contentStream.newLineAtOffset(0, -15);
        contentStream.showText("Email: sistema.licencias@frsf.utn.edu.ar");
        contentStream.newLineAtOffset(0, -15);
        contentStream.showText("Contacto: +54 9 342 4601579");
        contentStream.newLineAtOffset(0, -15);
        contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 12);
        contentStream.showText("Fecha: " + LocalDate.now().toString());
        contentStream.endText();

        // Customers details
        contentStream.beginText();
        contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 12);
        contentStream.newLineAtOffset(20, 580);
        contentStream.showText("Datos del cliente : ");
        contentStream.newLineAtOffset(0, -15); 
        contentStream.showText("Numero de identificacion: " + licencia.titular.nroDNI);
        contentStream.newLineAtOffset(0, -15); 
        contentStream.showText("Nombre completo: " + licencia.titular.nombre + " " + licencia.titular.apellido);  
        contentStream.newLineAtOffset(0, -15);
        contentStream.showText("Direccion: " + licencia.titular.direccion);
        contentStream.endText();

        // Table header
        contentStream.setLineWidth(0.5f);
        contentStream.moveTo(20, 500);
        contentStream.lineTo(580, 500);
        contentStream.stroke();

        contentStream.beginText();
        contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 12);
        contentStream.newLineAtOffset(25, 480);
        contentStream.showText("CONCEPTO");
        contentStream.newLineAtOffset(200, 0);
        contentStream.showText("PRECIO");
        contentStream.newLineAtOffset(100, 0);
        contentStream.showText("IMPUESTOS");
        contentStream.newLineAtOffset(150, 0);
        contentStream.showText("TOTAL");
        contentStream.endText();

        int yPosition = 460;
        contentStream.beginText();
        contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 12);
        contentStream.newLineAtOffset(25, yPosition);
        contentStream.showText("Licencia clase " + licencia.clase.toString());
        contentStream.newLineAtOffset(200, 0);
        contentStream.showText("$ " + Integer.toString(costoTotal - 8));
        contentStream.newLineAtOffset(100, 0);
        contentStream.showText("$ 8");
        contentStream.newLineAtOffset(150, 0);
        contentStream.showText("$ " + Integer.toString(costoTotal));
        contentStream.endText();
        yPosition -= 20;

        contentStream.setLineWidth(0.5f);
        contentStream.moveTo(20, yPosition - 300);
        contentStream.lineTo(580, yPosition - 300);
        contentStream.stroke();

        contentStream.beginText();
        contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 14);
        contentStream.newLineAtOffset(20, yPosition - 320);
        contentStream.showText("TOTAL");
        contentStream.newLineAtOffset(450, 0);
        contentStream.showText("$ " + costoTotal);
        contentStream.endText();

        contentStream.setLineWidth(0.5f);
        contentStream.moveTo(20, yPosition - 340);
        contentStream.lineTo(580, yPosition - 340);
        contentStream.stroke();

        // Payment info
        contentStream.beginText();
        contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 10);
        contentStream.setNonStrokingColor(Color.gray);
        contentStream.newLineAtOffset(20, yPosition - 380);
        contentStream.showText("Pagar por transferencia a SISTEMAS.LICENCIAS.MERCADO.PAGO o");
        contentStream.newLineAtOffset(0, -15);
        contentStream.showText("CBU: 4889287611100039782882");
        contentStream.endText();

        contentStream.beginText();
        contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 8);
        contentStream.setNonStrokingColor(Color.lightGray);
        contentStream.newLineAtOffset(20, yPosition - 410);
        contentStream.showText("Todas las facturas poseen una vigencia de 3 [TRES] dias, pasado ese tiempo devera generarse una nueva.");
        contentStream.newLineAtOffset(0, -10);
        contentStream.showText("Este documento posee validez de @SISTEMA DE LICENCIAS SA");
        contentStream.endText();
    }

    private void addText(PDPageContentStream contentStream, String text, float x, float y, int fontSize)
            throws IOException {
        addText(contentStream, text, x, y, fontSize, false);
    }

    private void addText(PDPageContentStream contentStream, String text, float x, float y, int fontSize, boolean bold)
            throws IOException {
        contentStream.beginText();
        contentStream.setFont(
                new PDType1Font(bold ? Standard14Fonts.FontName.HELVETICA_BOLD : Standard14Fonts.FontName.HELVETICA),
                fontSize);
        contentStream.newLineAtOffset(x, y);
        contentStream.showText(text);
        contentStream.endText();
    }

    private void limpiarCampos() {
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
        tablaLic.getItems().clear();
    }
}
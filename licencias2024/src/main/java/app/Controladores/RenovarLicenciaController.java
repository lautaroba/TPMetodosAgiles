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
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.ResourceBundle;

import app.App;
import app.DTOs.LicenciaDTO;
import app.DTOs.TitularDTO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

public class RenovarLicenciaController implements Initializable {

    @FXML
    private Button logoutButton;
    @FXML
    private Button volverButton;
    @FXML
    private Button buscarButton;
    @FXML
    private Button renovarButton;
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
    private int costoTotal;
    private LicenciaDTO licencia;

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

            if (titular.donante == true)
                donanteTextfield.setText("SI");
            else
                donanteTextfield.setText("NO");

            int plazo = App.gestor.CalcularVigenciaLicencia(titular);
            fechaInicio = LocalDate.now();
            fechaFinal = LocalDate.of(fechaInicio.plusYears(plazo).getYear(), titular.fechaDeNacimiento.getMonth(),
                    titular.fechaDeNacimiento.getDayOfMonth());
            vigenciaTextfield.setText(fechaFinal.toString());
            costoTotal = App.gestor.CalcularCostoLicencia(titular);
            costoTextfield.setText(String.valueOf(costoTotal));

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
    private void renovar(ActionEvent event) {
        try {
            // Busco si existe alguna licencia para el titular en cuestion con fecha de
            // expiracion mayor a la actual
            if (App.gestor.BuscarLicenciaFecha(titular)) {
                licencia = new LicenciaDTO(titular, App.gestor.administradorLogeado, fechaInicio, fechaFinal, true);
                App.gestor.CrearLicencia(licencia);

                ImprimirLicencia();

                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Sistema de licencias");
                alert.setContentText("Licencia generada con exito.");
                alert.showAndWait(); // Mostrar la alerta y esperar a que el usuario la cierre
            } else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Sistema de licencias");
                alert.setContentText("No se ha podido generar la licencia, ya existe una licencia activa.");
                alert.showAndWait(); // Mostrar la alerta y esperar a que el usuario la cierre
            }
        } catch (NoSuchElementException e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Sistema de licencias");
            alert.setContentText("No se ha podido generar la licencia, ya existe una licencia activa.");
            alert.showAndWait(); // Mostrar la alerta y esperar a que el usuario la cierre
        } catch (Exception e) {
            // limpiarCampos();
            e.printStackTrace();
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Sistema de licencias");
            alert.setContentText("No se ha podido generar la licencia, revise los datos nuevamente.");
            alert.showAndWait(); // Mostrar la alerta y esperar a que el usuario la cierre
        }
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

        File fileCarnet = new File(selectedDirectory, titular.nombre + "_licencia.pdf");
        File fileComprobante = new File(selectedDirectory, titular.nombre + "_comprobante.pdf");

        try (PDDocument documentLicencia = new PDDocument();
                PDDocument documentComprobante = new PDDocument()) {

            PDPage pageLicencia = new PDPage();
            documentLicencia.addPage(pageLicencia);

            PDPage pageComprobante = new PDPage();
            documentComprobante.addPage(pageComprobante);

            try (PDPageContentStream contentStreamLicencia = new PDPageContentStream(documentLicencia, pageLicencia)) {
                drawLicencia(contentStreamLicencia, documentLicencia);
            }

            try (PDPageContentStream contentStreamComprobante = new PDPageContentStream(documentComprobante,
                    pageComprobante)) {
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

        // Añadir título del carnet
        contentStreamLicencia.beginText();
        contentStreamLicencia.setFont(new PDType1Font(Standard14Fonts.FontName.TIMES_ROMAN), 16);
        contentStreamLicencia.newLineAtOffset(230, 725);
        contentStreamLicencia.showText("Licencia de Conducir");
        contentStreamLicencia.endText();

        // Añadir número de licencia
        addText(contentStreamLicencia, "N° Licencia: " + "ID", 200, 665, 12);

        // Añadir apellido
        addText(contentStreamLicencia, "Apellido: " + titular.apellido, 200, 650, 12);

        // Añadir nombre
        addText(contentStreamLicencia, "Nombre: " + titular.nombre, 200, 635, 12);

        // Añadir domicilio
        addText(contentStreamLicencia, "Domicilio: " + titular.direccion, 200, 620, 12);

        // Añadir fecha de nacimiento
        addText(contentStreamLicencia, "Fecha de nac.: " + titular.fechaDeNacimiento.toString(), 200, 605, 12);

        // Añadir fecha de otorgamiento
        addText(contentStreamLicencia, "Otorgamiento: " + licencia.fechaDeEmision.toString(), 200, 590, 12);

        // Añadir fecha de vencimiento
        addText(contentStreamLicencia, "Vencimiento: " + licencia.fechaDeExpiracion.toString(), 200, 575, 12);

        // Añadir clases
        addText(contentStreamLicencia, "Clases: " + licencia.titular.clase.toString(), 400, 665, 12);

        // Añadir imagen de perfil
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

        // Añadir donante
        addText(contentStreamLicencia, "Donante: " + (licencia.titular.donante ? "Si" : "No"), 75, 350, 12);

        // Añadir grupo sanguineo
        addText(contentStreamLicencia, "Grupo Sanguineo: " + licencia.titular.grupoSanguineo.toString(), 75, 330, 12);

        // Añadir TipoDoc
        addText(contentStreamLicencia, "Tipo Doc: " + licencia.titular.tipoDocumento.toString(), 75, 310, 12);

        // Nro DNI
        addText(contentStreamLicencia, "Nro Doc: " + licencia.titular.nroDNI, 75, 290, 12);

        // Observaciones
        addText(contentStreamLicencia, "Observaciones: " + licencia.titular.limitacion, 75, 200, 6);

        // Descripción de clase
        addText(contentStreamLicencia, "Descripcion de clase: " + licencia.titular.clase.getNombreClase(), 75, 180, 6);
    }

    private void drawComprobante(PDPageContentStream contentStreamComprobante) throws IOException {
        // Añadir título del comprobante
        addText(contentStreamComprobante, "Comprobante de Pago", 230, 725, 20, true);

        // Añadir subtítulo
        addText(contentStreamComprobante, "Datos de facturación:", 50, 650, 16, true);

        contentStreamComprobante.setLineWidth(0.7f);
        contentStreamComprobante.moveTo(50, 650);
        contentStreamComprobante.lineTo(200, 649);
        contentStreamComprobante.stroke();

        // Añadir apellido
        addText(contentStreamComprobante, "Apellido: " + licencia.titular.apellido, 50, 625, 16);

        // Añadir nombre
        addText(contentStreamComprobante, "Nombres: " + licencia.titular.nombre, 50, 600, 16);

        // Añadir tipoDoc
        addText(contentStreamComprobante, "Tipo Doc: " + licencia.titular.tipoDocumento.toString(), 50, 575, 16);

        // Añadir nroDoc
        addText(contentStreamComprobante, "Nro Doc: " + licencia.titular.nroDNI, 50, 550, 16);

        // Añadir domicilio
        addText(contentStreamComprobante, "Domicilio: " + licencia.titular.direccion, 50, 525, 16);

        // Añadir monto
        addText(contentStreamComprobante, "Importe operación: $" + (costoTotal - 8), 50, 500, 16);

        addText(contentStreamComprobante, "Importe gastos administrativos: $8", 50, 475, 16);

        addText(contentStreamComprobante, "Importe total: $" + costoTotal, 50, 450, 16);
    }

    private void addText(PDPageContentStream contentStream, String text, float x, float y, int fontSize)
            throws IOException {
        addText(contentStream, text, x, y, fontSize, false);
    }

    private void addText(PDPageContentStream contentStream, String text, float x, float y, int fontSize, boolean bold)
            throws IOException {
        contentStream.beginText();
        contentStream.setFont(
                new PDType1Font(bold ? Standard14Fonts.FontName.TIMES_BOLD : Standard14Fonts.FontName.TIMES_ROMAN),
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
    }
}
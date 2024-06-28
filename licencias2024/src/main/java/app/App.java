package app;

import java.time.LocalDate;

import app.DTOs.AdministradorDTO;
import app.Enumeradores.Sexo;
import app.Enumeradores.TipoDocumento;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class App extends Application {

    public static Gestor gestor;

    public void start(Stage primaryStage) throws Exception {
        gestor = new Gestor();

        AnchorPane root = FXMLLoader.load(getClass().getResource("/ControladoresFXML/Login.fxml"));
        primaryStage.setResizable(false);
        primaryStage.setTitle("Inicio - Sistema de licencias");
        primaryStage.setScene(new Scene(root));
        Image logo = new Image("/Images/Logo.png");
        primaryStage.getIcons().add(logo);
        primaryStage.show();

        AdministradorDTO administrador1 = new AdministradorDTO(1, "admin1", "admin1", LocalDate.of(2000, 1, 1), "direccion1",
                "unemail@email.com", "a", TipoDocumento.DNI, Sexo.Masculino);
        try {
            gestor.CrearAdministrador(administrador1);
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}

package app;

import java.time.LocalDate;

import app.DTOs.AdministradorDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class App extends Application
{
    private EntityManagerFactory entityManagerFactory;
    public static EntityManager entityManager;
    public static Gestor gestor;

    public void start(Stage primaryStage) throws Exception {

        entityManagerFactory = Persistence.createEntityManagerFactory("licencias2024PU");
        entityManager = entityManagerFactory.createEntityManager();
        gestor = new Gestor();

        // Prueba // 
        //gestor.CrearAdministrador(new AdministradorDTO(1, "chinardo", "anashe", LocalDate.now(), "unaCasaLoca", "unemail@email.com", "admin"));

        AnchorPane root = FXMLLoader.load(getClass().getResource("/ControladoresFXML/Login.fxml"));

        primaryStage.setResizable(false);
        primaryStage.setTitle("Inicio - Sistema de licencias");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }
    public static void main(String[] args) {
        launch();
    }
}
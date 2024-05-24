package app;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.Stage;

public class App extends Application
{
    private EntityManagerFactory entityManagerFactory;
    public static EntityManager entityManager;

    public void start(Stage primaryStage) throws Exception {

            entityManagerFactory = Persistence.createEntityManagerFactory("licencias2024PU");
            entityManager = entityManagerFactory.createEntityManager();

        
        Parent root = FXMLLoader.load(getClass().getResource("/ControladoresFXML/LoginFXML.fxml"));
        primaryStage.setTitle("Ventana de inicio");
        primaryStage.setScene(new Scene(root, 400, 275));
        primaryStage.show();

    }
    public static void main(String[] args) {
        launch();
    }
}

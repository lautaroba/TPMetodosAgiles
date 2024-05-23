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
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("/ControladoresFXML/LoginFXML.fxml"));
        primaryStage.setTitle("Ventana de inicio");
        primaryStage.setScene(new Scene(root, 400, 275));
        primaryStage.show();

        try {
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("licencias2024PU");
            EntityManager em = entityManagerFactory.createEntityManager();
        } catch (Exception e) {
            System.out.println(e);
        }

    }
    public static void main(String[] args) {
        launch();
    }
}

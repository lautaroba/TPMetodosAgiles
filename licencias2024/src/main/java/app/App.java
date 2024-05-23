package app;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class App extends Application
{
    @Override
    public void start(Stage stage) {
        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");
        Label l = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
        Scene scene = new Scene(new StackPane(l), 640, 480);
        stage.setScene(scene);
        stage.show();
        
        try {
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("licencias2024PU");
            EntityManager em = entityManagerFactory.createEntityManager();
            em.getTransaction().begin();
            em.createNativeQuery("CREATE DATABASE licenciasdb").executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e);
        }

    }
    public static void main(String[] args) {
        launch();
    }
}

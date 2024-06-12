package app;

import java.time.LocalDate;

import app.DTOs.AdministradorDTO;
import app.DTOs.TitularDTO;
import app.Enumeradores.Clase;
import app.Enumeradores.FactorRH;
import app.Enumeradores.GrupoSanguineo;
import app.Enumeradores.Sexo;
import app.Enumeradores.TipoDocumento;
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
        try {
            gestor.CrearAdministrador(new AdministradorDTO(1, "admin1", "admin1", LocalDate.now(), "direccion1", "unemail@email.com", "a", TipoDocumento.DNI, Sexo.Masculino));
            gestor.CrearAdministrador(new AdministradorDTO(2, "admin2", "admin2", LocalDate.now(), "direccion2", "unemail@email.com", "b", TipoDocumento.DNI, Sexo.Masculino));
            
            gestor.CrearTitular(new TitularDTO(TipoDocumento.DNI, 1, "prueba", "24 años", LocalDate.parse("2000-11-11"), "direccion1", Clase.B, GrupoSanguineo.O, FactorRH.Positivo, true, "limitaciones"));
            gestor.CrearTitular(new TitularDTO(TipoDocumento.DNI, 2, "prueba", "18 años", LocalDate.parse("2006-11-11"), "direccion2", Clase.B, GrupoSanguineo.O, FactorRH.Positivo, true, "limitaciones2, limitaciones3"));
            gestor.CrearTitular(new TitularDTO(TipoDocumento.DNI, 3, "prueba", "50 años", LocalDate.parse("1975-11-11"), "direccion3", Clase.B, GrupoSanguineo.O, FactorRH.Positivo, true, "limitaciones, limitaciones2, limitaciones3"));
            gestor.CrearTitular(new TitularDTO(TipoDocumento.DNI, 4, "prueba", "60 años", LocalDate.parse("1960-11-11"), "direccion4", Clase.A, GrupoSanguineo.O, FactorRH.Positivo, true, "limitaciones, limitaciones2, limitaciones3"));
            gestor.CrearTitular(new TitularDTO(TipoDocumento.DNI, 5, "prueba", "clase D", LocalDate.parse("2000-11-11"), "direccion5", Clase.D1, GrupoSanguineo.O, FactorRH.Positivo, false,""));
            gestor.CrearTitular(new TitularDTO(TipoDocumento.DNI, 6, "prueba", "clase F", LocalDate.parse("2000-11-11"), "direccion6", Clase.F, GrupoSanguineo.O, FactorRH.Positivo, false,"limitaciones, limitaciones2, limitaciones3, limitaciones4"));
            gestor.CrearTitular(new TitularDTO(TipoDocumento.DNI, 7, "prueba", "74 años", LocalDate.parse("1950-11-11"), "direccion7", Clase.B, GrupoSanguineo.O, FactorRH.Positivo, true, "limitaciones, limitaciones2, limitaciones3"));

        } catch (Exception e) {
            System.out.println("creado");
        }

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
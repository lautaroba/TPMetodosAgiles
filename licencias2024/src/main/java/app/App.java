package app;

import java.time.LocalDate;

import app.DTOs.AdministradorDTO;
import app.DTOs.TitularDTO;
import app.Enunumenadores.Clase;
import app.Enunumenadores.FactorRH;
import app.Enunumenadores.GrupoSanguineo;
import app.Enunumenadores.Sexo;
import app.Enunumenadores.TipoDocumento;
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
            gestor.CrearAdministrador(new AdministradorDTO(1, "chinardo", "anashe", LocalDate.now(), "unaCasaLoca", "unemail@email.com", "a", TipoDocumento.DNI, Sexo.Masculino));
            gestor.CrearTitular(new TitularDTO(TipoDocumento.DNI, 1, "fernando", "normal", LocalDate.parse("2000-11-11"), "otraCasaLoca", Clase.B, GrupoSanguineo.O, FactorRH.Positivo, true));
            gestor.CrearTitular(new TitularDTO(TipoDocumento.DNI, 2, "fernando", "pebete", LocalDate.parse("2006-11-11"), "otraCasaLoca", Clase.B, GrupoSanguineo.O, FactorRH.Positivo, true));
            gestor.CrearTitular(new TitularDTO(TipoDocumento.DNI, 3, "fernando", "viejo", LocalDate.parse("1975-11-11"), "otraCasaLoca", Clase.B, GrupoSanguineo.O, FactorRH.Positivo, true));
            gestor.CrearTitular(new TitularDTO(TipoDocumento.DNI, 4, "fernando", "abuelo", LocalDate.parse("1960-11-11"), "otraCasaLoca", Clase.A, GrupoSanguineo.O, FactorRH.Positivo, true));
            gestor.CrearTitular(new TitularDTO(TipoDocumento.DNI, 5, "fernando", "milico", LocalDate.parse("2000-11-11"), "otraCasaLoca", Clase.D, GrupoSanguineo.O, FactorRH.Positivo, false));
            gestor.CrearTitular(new TitularDTO(TipoDocumento.DNI, 6, "fernando", "discapacitado", LocalDate.parse("2000-11-11"), "otraCasaLoca", Clase.F, GrupoSanguineo.O, FactorRH.Positivo, false));
            gestor.CrearTitular(new TitularDTO(TipoDocumento.DNI, 7, "fernando", "en el cajon", LocalDate.parse("1950-11-11"), "otraCasaLoca", Clase.B, GrupoSanguineo.O, FactorRH.Positivo, true));

        } catch (Exception e) {
            System.out.println("ya esta creado cabeza");
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
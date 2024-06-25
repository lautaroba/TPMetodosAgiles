package app;

import java.time.LocalDate;

import app.DTOs.AdministradorDTO;
import app.DTOs.TitularDTO;
import app.Enumeradores.FactorRH;
import app.Enumeradores.GrupoSanguineo;
import app.Enumeradores.Sexo;
import app.Enumeradores.TipoDocumento;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
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
        primaryStage.show();

        // AdministradorDTO administrador1 = new AdministradorDTO(1, "admin1", "admin1", LocalDate.of(2000, 1, 1), "direccion1",
        //         "unemail@email.com", "a", TipoDocumento.DNI, Sexo.Masculino);
        // AdministradorDTO administrador2 = new AdministradorDTO(2, "admin2", "admin2", LocalDate.of(2000, 1, 1), "direccion2",
        //         "unemail@email.com", "b", TipoDocumento.DNI, Sexo.Masculino);

        // try {
        //     gestor.CrearAdministrador(administrador1);
        //     gestor.CrearAdministrador(administrador2);
        // } catch (Exception e) {
        //     //e.printStackTrace();
        // }

        // TitularDTO titularDTO1 = new TitularDTO(TipoDocumento.DNI, 1, "Matias", "24 años",
        //         LocalDate.parse("2000-11-11"), "direccion1", GrupoSanguineo.A, FactorRH.Negativo, true, "limitaciones");
        // TitularDTO titularDTO2 = new TitularDTO(TipoDocumento.DNI, 2, "Fernando", "18 años",
        //         LocalDate.parse("2006-11-11"), "direccion2", GrupoSanguineo.AB, FactorRH.Positivo, true,
        //         "limitaciones2, limitaciones3");
        // TitularDTO titularDTO3 = new TitularDTO(TipoDocumento.DNI, 3, "Lautaro", "50 años",
        //         LocalDate.parse("1975-11-11"), "direccion3", GrupoSanguineo.B, FactorRH.Positivo, true,
        //         "limitaciones, limitaciones2, limitaciones3");
        // TitularDTO titularDTO4 = new TitularDTO(TipoDocumento.DNI, 4, "Agustin", "60 años",
        //         LocalDate.parse("1960-11-11"), "direccion4", GrupoSanguineo.B, FactorRH.Negativo, true,
        //         "limitaciones, limitaciones2, limitaciones3");
        // TitularDTO titularDTO5 = new TitularDTO(TipoDocumento.DNI, 5, "Jazmín", "clase D",
        //         LocalDate.parse("2000-11-11"), "direccion5", GrupoSanguineo.O, FactorRH.Negativo, false, "");
        // TitularDTO titularDTO6 = new TitularDTO(TipoDocumento.DNI, 6, "José", "clase F", LocalDate.parse("2000-11-11"),
        //         "direccion6", GrupoSanguineo.O, FactorRH.Positivo, false,
        //         "limitaciones, limitaciones2, limitaciones3, limitaciones4");
        // TitularDTO titularDTO7 = new TitularDTO(TipoDocumento.DNI, 7, "María", "74 años", LocalDate.parse("1950-11-11"),
        //         "direccion7", GrupoSanguineo.O, FactorRH.Positivo, true, "limitaciones, limitaciones2, limitaciones3");

        // try {
        //     gestor.CrearTitular(titularDTO1);
        //     gestor.CrearTitular(titularDTO2);
        //     gestor.CrearTitular(titularDTO3);
        //     gestor.CrearTitular(titularDTO4);
        //     gestor.CrearTitular(titularDTO5);
        //     gestor.CrearTitular(titularDTO6);
        //     gestor.CrearTitular(titularDTO7);
        // } catch (Exception e) {
        //     //e.printStackTrace();
        // }

    }

    public static void main(String[] args) {
        launch();
    }
}

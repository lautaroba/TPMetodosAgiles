package app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import app.DTOs.AdministradorDTO;
import app.DTOs.LicenciaDTO;
import app.DTOs.TitularDTO;
import app.Enumeradores.Clase;
import app.Enumeradores.FactorRH;
import app.Enumeradores.GrupoSanguineo;
import app.Enumeradores.Sexo;
import app.Enumeradores.TipoDocumento;

public class EmitirLicenciaTest {

    public static Gestor gestor;

    @BeforeAll
    public static void setUp() {
        gestor = new Gestor();
    }

    @AfterEach
    public void close() {
        gestor.dropDB();
    }

    @Test
    public void TestCrearLicenciaConTitularMenorDe17Anos() {
        AdministradorDTO administrador1 = new AdministradorDTO(1, "Jhon", "Doe", LocalDate.now(),
                "direccion",
                "unemail@email.com", "a", TipoDocumento.DNI, Sexo.Masculino);
        TitularDTO titularMenorDe17 = new TitularDTO(TipoDocumento.DNI, 1, "Juan", "Gonzalez",
                LocalDate.of(2008, 1, 1), "direccion", GrupoSanguineo.A, FactorRH.Negativo, true,
                "limitaciones1");
        LicenciaDTO licencia1 = new LicenciaDTO(titularMenorDe17, administrador1,
                LocalDate.now(), LocalDate.of(2025, 1, 1), Clase.A);
        try {
            gestor.CrearLicencia(licencia1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(null, gestor.BuscarLicenciasTitular(titularMenorDe17, LocalDate.of(1, 1, 1)));
    }

    @Test
    public void TestCrearLicenciaProfesionalConTitularMenorDe21Anos() {
        AdministradorDTO administrador1 = new AdministradorDTO(1, "Jhon", "Doe", LocalDate.now(),
                "direccion",
                "unemail@email.com", "a", TipoDocumento.DNI, Sexo.Masculino);
        TitularDTO titularMenorDe21 = new TitularDTO(TipoDocumento.DNI, 1, "Juan", "Gonzalez",
                LocalDate.of(2006, 1, 1), "direccion", GrupoSanguineo.A, FactorRH.Negativo, true,
                "limitaciones1");
        LicenciaDTO licencia1 = new LicenciaDTO(titularMenorDe21, administrador1,
                LocalDate.now(), LocalDate.of(2025, 1, 1), Clase.C);
        try {
            gestor.CrearLicencia(licencia1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertTrue(gestor.BuscarLicenciasTitular(titularMenorDe21, LocalDate.of(1, 1, 1)).isEmpty());
    }

    @Test
    public void TestCrearLicenciaProfesionalConTitularMayorDe65Anos() {
        AdministradorDTO administrador1 = new AdministradorDTO(1, "Jhon", "Doe", LocalDate.now(),
                "direccion",
                "unemail@email.com", "a", TipoDocumento.DNI, Sexo.Masculino);
        TitularDTO titularMayorDe65 = new TitularDTO(TipoDocumento.DNI, 1, "Juan", "Gonzalez",
                LocalDate.of(1958, 1, 1), "direccion", GrupoSanguineo.A, FactorRH.Negativo, true,
                "limitaciones1");
        LicenciaDTO licencia1 = new LicenciaDTO(titularMayorDe65, administrador1,
                LocalDate.now(), LocalDate.of(2025, 1, 1), Clase.C);
        try {
            gestor.CrearLicencia(licencia1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertTrue(gestor.BuscarLicenciasTitular(titularMayorDe65, LocalDate.of(1, 1, 1)).isEmpty());
    }

    @Test
    public void TestCrearLicenciaProfesionalConTitularSinBPrevia() {
        AdministradorDTO administrador1 = new AdministradorDTO(1, "Jhon", "Doe", LocalDate.now(),
                "direccion",
                "unemail@email.com", "a", TipoDocumento.DNI, Sexo.Masculino);
        TitularDTO titularSinBPrevia = new TitularDTO(TipoDocumento.DNI, 1, "Juan", "Gonzalez",
                LocalDate.of(2000, 1, 1), "direccion", GrupoSanguineo.A, FactorRH.Negativo, true,
                "limitaciones1");
        LicenciaDTO licencia1 = new LicenciaDTO(titularSinBPrevia, administrador1,
                LocalDate.now(), LocalDate.of(2025, 1, 1), Clase.C);
        try {
            gestor.CrearLicencia(licencia1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertTrue(gestor.BuscarLicenciasTitular(titularSinBPrevia, LocalDate.of(1, 1, 1)).isEmpty());
    }

    @Test
    public void TestCrearLicenciaProfesionalConTitularConBPreviaSin1AnoDeVigencia() {
        AdministradorDTO administrador1 = new AdministradorDTO(1, "Jhon", "Doe", LocalDate.of(2000, 1, 1),
                "direccion",
                "unemail@email.com", "a", TipoDocumento.DNI, Sexo.Masculino);
        TitularDTO titularConBPreviaSin1anio = new TitularDTO(TipoDocumento.DNI, 1, "Juan", "Gonzalez",
                LocalDate.of(2000, 1, 1), "direccion", GrupoSanguineo.A, FactorRH.Negativo, true,
                "limitaciones1");
        LicenciaDTO licencia1 = new LicenciaDTO(titularConBPreviaSin1anio, administrador1,
                LocalDate.now(), LocalDate.of(2025, 1, 1), Clase.B);

        LicenciaDTO licencia2 = new LicenciaDTO(titularConBPreviaSin1anio, administrador1,
                LocalDate.now(), LocalDate.of(2026, 1, 1), Clase.C);
        try {
            gestor.CrearAdministrador(administrador1);
            gestor.CrearTitular(titularConBPreviaSin1anio);
            gestor.CrearLicencia(licencia1);
            gestor.CrearLicencia(licencia2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(1, gestor.BuscarLicenciasTitular(titularConBPreviaSin1anio, LocalDate.of(1, 1, 1)).size());
    }

    @Test
    public void TestCrearLicenciaConUnaYaExistente() {
        AdministradorDTO administrador1 = new AdministradorDTO(1, "Jhon", "Doe", LocalDate.of(2000, 1, 1),
                "direccion",
                "unemail@email.com", "a", TipoDocumento.DNI, Sexo.Masculino);
        TitularDTO titularConBIntentaSacarlaNuevamente = new TitularDTO(TipoDocumento.DNI, 1, "Juan", "Gonzalez",
                LocalDate.of(2000, 1, 1), "direccion", GrupoSanguineo.A, FactorRH.Negativo, true,
                "limitaciones1");
        LicenciaDTO licencia1 = new LicenciaDTO(titularConBIntentaSacarlaNuevamente, administrador1,
                LocalDate.of(2022, 1, 1), LocalDate.of(2023, 1, 1), Clase.B);
        LicenciaDTO licencia2 = new LicenciaDTO(titularConBIntentaSacarlaNuevamente, administrador1,
                LocalDate.now(), LocalDate.of(2026, 1, 1), Clase.B);
        try {
            gestor.CrearAdministrador(administrador1);
            gestor.CrearTitular(titularConBIntentaSacarlaNuevamente);
            gestor.CrearLicencia(licencia1);
            gestor.CrearLicencia(licencia2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(1,
                gestor.BuscarLicenciasTitular(titularConBIntentaSacarlaNuevamente, LocalDate.of(1, 1, 1)).size());
    }

    @Test
    public void TestCrearTodasLasLicenciasPosibles() {
        AdministradorDTO administrador1 = new AdministradorDTO(1, "Jhon", "Doe", LocalDate.of(2000, 1, 1),
                "direccion",
                "unemail@email.com", "a", TipoDocumento.DNI, Sexo.Masculino);
        TitularDTO titularConTodasLasLicencias = new TitularDTO(TipoDocumento.DNI, 1, "Juan", "Gonzalez",
                LocalDate.of(2000, 1, 1), "direccion", GrupoSanguineo.A, FactorRH.Negativo, true,
                "limitaciones1");
        LicenciaDTO licencia1 = new LicenciaDTO(titularConTodasLasLicencias, administrador1,
                LocalDate.of(2024, 1, 1), LocalDate.of(2025, 1, 1), Clase.A);
        LicenciaDTO licencia2 = new LicenciaDTO(titularConTodasLasLicencias, administrador1,
                LocalDate.of(2020, 1, 1), LocalDate.of(2021, 1, 1), Clase.B);
        LicenciaDTO licencia3 = new LicenciaDTO(titularConTodasLasLicencias, administrador1,
                LocalDate.of(2021, 1, 1), LocalDate.of(2022, 1, 1), Clase.C);
        LicenciaDTO licencia4 = new LicenciaDTO(titularConTodasLasLicencias, administrador1,
                LocalDate.of(2022, 1, 1), LocalDate.of(2023, 1, 1), Clase.D1);
        LicenciaDTO licencia5 = new LicenciaDTO(titularConTodasLasLicencias, administrador1,
                LocalDate.of(2024, 1, 1), LocalDate.of(2025, 1, 1), Clase.D2);
        LicenciaDTO licencia6 = new LicenciaDTO(titularConTodasLasLicencias, administrador1,
                LocalDate.of(2024, 1, 1), LocalDate.of(2025, 1, 1), Clase.E);
        LicenciaDTO licencia7 = new LicenciaDTO(titularConTodasLasLicencias, administrador1,
                LocalDate.of(2024, 1, 1), LocalDate.of(2025, 1, 1), Clase.F);
        LicenciaDTO licencia8 = new LicenciaDTO(titularConTodasLasLicencias, administrador1,
                LocalDate.of(2024, 1, 1), LocalDate.of(2025, 1, 1), Clase.G);
        try {
            gestor.CrearAdministrador(administrador1);
            gestor.CrearTitular(titularConTodasLasLicencias);
            gestor.CrearLicencia(licencia1);
            gestor.CrearLicencia(licencia2);
            gestor.CrearLicencia(licencia3);
            gestor.CrearLicencia(licencia4);
            gestor.CrearLicencia(licencia5);
            gestor.CrearLicencia(licencia6);
            gestor.CrearLicencia(licencia7);
            gestor.CrearLicencia(licencia8);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(8, gestor.BuscarLicenciasTitular(titularConTodasLasLicencias, LocalDate.of(1, 1, 1)).size());
    }
}

package app;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

public class RenovarLicenciaTest {

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
    public void TestRenovarLicenciaVencida() {
        AdministradorDTO administrador1 = new AdministradorDTO(1, "Jhon", "Doe", LocalDate.of(2000, 1, 1),
                "direccion",
                "unemail@email.com", "a", TipoDocumento.DNI, Sexo.Masculino);
        TitularDTO titular = new TitularDTO(TipoDocumento.DNI, 1, "Juan", "Gonzalez",
                LocalDate.of(2000, 1, 1), "direccion", GrupoSanguineo.A, FactorRH.Negativo, true,
                "limitaciones1");
        LicenciaDTO licencia1 = new LicenciaDTO(titular, administrador1,
                LocalDate.of(2019, 1, 1), LocalDate.of(2020, 1, 1), Clase.A);
        LicenciaDTO licencia2 = new LicenciaDTO(titular, administrador1,
                LocalDate.of(2022, 1, 1), LocalDate.of(2024, 1, 1), Clase.A);
        try {
            gestor.CrearAdministrador(administrador1);
            gestor.CrearTitular(titular);

            gestor.CrearLicencia(licencia1);
            gestor.RenovarLicencia(licencia2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(2, gestor.BuscarLicenciasTitular(titular, LocalDate.of(1, 1, 1)).size());
    }

    @Test
    public void TestRenovarLicenciaVigenteConMasDe3Meses() {
        AdministradorDTO administrador1 = new AdministradorDTO(1, "Jhon", "Doe", LocalDate.of(2000, 1, 1),
                "direccion",
                "unemail@email.com", "a", TipoDocumento.DNI, Sexo.Masculino);
        TitularDTO titular = new TitularDTO(TipoDocumento.DNI, 1, "Juan", "Gonzalez",
                LocalDate.of(2000, 1, 1), "direccion", GrupoSanguineo.A, FactorRH.Negativo, true,
                "limitaciones1");
        LicenciaDTO licencia1 = new LicenciaDTO(titular, administrador1,
                LocalDate.of(2020, 1, 1), LocalDate.of(2025, 1, 1), Clase.A);
        LicenciaDTO licencia2 = new LicenciaDTO(titular, administrador1,
                LocalDate.of(2024, 1, 1), LocalDate.of(2026, 1, 1), Clase.A);
        try {
            gestor.CrearAdministrador(administrador1);
            gestor.CrearTitular(titular);

            gestor.CrearLicencia(licencia1);
            gestor.RenovarLicencia(licencia2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(1, gestor.BuscarLicenciasTitular(titular, LocalDate.of(1, 1, 1)).size());
    }

    @Test
    public void TestRenovarLicenciaVigenteConMenosDe3Meses() {
        AdministradorDTO administrador1 = new AdministradorDTO(1, "Jhon", "Doe", LocalDate.of(2000, 1, 1),
                "direccion",
                "unemail@email.com", "a", TipoDocumento.DNI, Sexo.Masculino);
        TitularDTO titular = new TitularDTO(TipoDocumento.DNI, 1, "Juan", "Gonzalez",
                LocalDate.of(2000, 1, 1), "direccion", GrupoSanguineo.A, FactorRH.Negativo, true,
                "limitaciones1");
        LicenciaDTO licencia1 = new LicenciaDTO(titular, administrador1,
                LocalDate.of(2020, 1, 1), LocalDate.of(2024, 7, 1), Clase.A);
        LicenciaDTO licencia2 = new LicenciaDTO(titular, administrador1,
                LocalDate.of(2024, 1, 1), LocalDate.of(2026, 1, 1), Clase.A);
        try {
            gestor.CrearAdministrador(administrador1);
            gestor.CrearTitular(titular);

            gestor.CrearLicencia(licencia1);
            gestor.RenovarLicencia(licencia2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(2, gestor.BuscarLicenciasTitular(titular, LocalDate.of(1, 1, 1)).size());
    }

    @Test
    public void TestRenovarLicenciaSuperiorTeniendoUnaInferiorVigente() {
        AdministradorDTO administrador1 = new AdministradorDTO(1, "Jhon", "Doe", LocalDate.of(2000, 1, 1),
                "direccion",
                "unemail@email.com", "a", TipoDocumento.DNI, Sexo.Masculino);
        TitularDTO titular = new TitularDTO(TipoDocumento.DNI, 1, "Juan", "Gonzalez",
                LocalDate.of(2000, 1, 1), "direccion", GrupoSanguineo.A, FactorRH.Negativo, true,
                "limitaciones1");
        LicenciaDTO licencia1 = new LicenciaDTO(titular, administrador1,
                LocalDate.of(2020, 1, 1), LocalDate.of(2024, 7, 1), Clase.B);
        LicenciaDTO licencia2 = new LicenciaDTO(titular, administrador1,
                LocalDate.of(2024, 1, 1), LocalDate.of(2026, 1, 1), Clase.C);
        try {
            gestor.CrearAdministrador(administrador1);
            gestor.CrearTitular(titular);

            gestor.CrearLicencia(licencia1);
            gestor.RenovarLicencia(licencia2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(2, gestor.BuscarLicenciasTitular(titular, LocalDate.of(1, 1, 1)).size());
        assertEquals(1, gestor.BuscarLicenciasTitular(titular, LocalDate.now().plusDays(1)).size());
    }
}

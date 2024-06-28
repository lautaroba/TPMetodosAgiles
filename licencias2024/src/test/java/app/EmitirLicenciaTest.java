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

/*
 *          * Test Cases Emitir Licencia
 * 
 * 1- menor de 21 años intenta sacar licencia profesional
 * 2- mayor de 65 años intenta sacar licencia profesional
 * 3- persona sin B previa intenta sacar profesional
 * 4- persona con B previa sin un año intenta sacar profesional
 * 5- persona con *(Clase X) obtenida y vencida intenta sacarla nuevamente.
 * 6- persona con *(Clase X) obtenida y vigente intenta sacarla nuevamente.
 * 7- persona con todas las licencias posibles (A, B(vencida), C(vencida), D1(vencida) D2, E, F, G)
 * 8- persona mejora una licencia, clase B a clase C
 * 9- persona mejora una licencia 2, clase B a clase D1
 * 10- persona mejora una licencia 3, clase B a clase C a clase E
 * 
 * 
 *          * Test Cases Calcular costo
 * 1- Clase A
 * 2- Clase B
 * 3- Clase C
 * 4- Clase D1
 * 5- Clase D2
 * 6- Clase E
 * 7- Clase F
 * 8- Clase G
 * 
 * 
 *          * Test Cases Calcular vigencia
 * 
 * 
 */


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

    @Test
    public void TestMejorarUnaLicencia() {
        AdministradorDTO administrador1 = new AdministradorDTO(1, "Jhon", "Doe", LocalDate.of(2000, 1, 1),
                "direccion",
                "unemail@email.com", "a", TipoDocumento.DNI, Sexo.Masculino);
        TitularDTO titularConTodasLasLicencias = new TitularDTO(TipoDocumento.DNI, 1, "Juan", "Gonzalez",
                LocalDate.of(2000, 1, 1), "direccion", GrupoSanguineo.A, FactorRH.Negativo, true,
                "limitaciones1");
        LicenciaDTO licencia1 = new LicenciaDTO(titularConTodasLasLicencias, administrador1,
                LocalDate.of(2023, 1, 1), LocalDate.of(2028, 1, 1), Clase.B);
        LicenciaDTO licencia2 = new LicenciaDTO(titularConTodasLasLicencias, administrador1,
                LocalDate.of(2025, 1, 1), LocalDate.of(2030, 1, 1), Clase.C);
        try {
            gestor.CrearAdministrador(administrador1);
            gestor.CrearTitular(titularConTodasLasLicencias);
            gestor.CrearLicencia(licencia1);
            gestor.CrearLicencia(licencia2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(1, gestor.BuscarLicenciasTitular(titularConTodasLasLicencias, LocalDate.now().plusDays(1)).size());
    }

    @Test
    public void TestMejorarUnaLicencia2() {
        AdministradorDTO administrador1 = new AdministradorDTO(1, "Jhon", "Doe", LocalDate.of(2000, 1, 1),
                "direccion",
                "unemail@email.com", "a", TipoDocumento.DNI, Sexo.Masculino);
        TitularDTO titularConTodasLasLicencias = new TitularDTO(TipoDocumento.DNI, 1, "Juan", "Gonzalez",
                LocalDate.of(2000, 1, 1), "direccion", GrupoSanguineo.A, FactorRH.Negativo, true,
                "limitaciones1");
        LicenciaDTO licencia1 = new LicenciaDTO(titularConTodasLasLicencias, administrador1,
                LocalDate.of(2023, 1, 1), LocalDate.of(2028, 1, 1), Clase.B);
        LicenciaDTO licencia2 = new LicenciaDTO(titularConTodasLasLicencias, administrador1,
                LocalDate.of(2025, 1, 1), LocalDate.of(2030, 1, 1), Clase.D1);
        try {
            gestor.CrearAdministrador(administrador1);
            gestor.CrearTitular(titularConTodasLasLicencias);
            gestor.CrearLicencia(licencia1);
            gestor.CrearLicencia(licencia2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(1, gestor.BuscarLicenciasTitular(titularConTodasLasLicencias, LocalDate.now().plusDays(1)).size());
    }

    @Test
    public void TestMejorarUnaLicencia3() {
        AdministradorDTO administrador1 = new AdministradorDTO(1, "Jhon", "Doe", LocalDate.of(2000, 1, 1),
                "direccion",
                "unemail@email.com", "a", TipoDocumento.DNI, Sexo.Masculino);
        TitularDTO titularConTodasLasLicencias = new TitularDTO(TipoDocumento.DNI, 1, "Juan", "Gonzalez",
                LocalDate.of(2000, 1, 1), "direccion", GrupoSanguineo.A, FactorRH.Negativo, true,
                "limitaciones1");
        LicenciaDTO licencia1 = new LicenciaDTO(titularConTodasLasLicencias, administrador1,
                LocalDate.of(2023, 1, 1), LocalDate.of(2028, 1, 1), Clase.B);
        LicenciaDTO licencia2 = new LicenciaDTO(titularConTodasLasLicencias, administrador1,
                LocalDate.of(2025, 1, 1), LocalDate.of(2030, 1, 1), Clase.C);
        LicenciaDTO licencia3 = new LicenciaDTO(titularConTodasLasLicencias, administrador1,
                LocalDate.of(2025, 1, 1), LocalDate.of(2030, 1, 1), Clase.E);
        try {
            gestor.CrearAdministrador(administrador1);
            gestor.CrearTitular(titularConTodasLasLicencias);
            gestor.CrearLicencia(licencia1);
            gestor.CrearLicencia(licencia2);
            gestor.CrearLicencia(licencia3);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(1, gestor.BuscarLicenciasTitular(titularConTodasLasLicencias, LocalDate.now().plusDays(1)).size());
    }


    @Test
    public void TestCalcularCostoClaseA() {

        Clase clase = Clase.A;
        AdministradorDTO administrador1 = new AdministradorDTO(1, "Jhon", "Doe", LocalDate.of(2000, 1, 1),
                "direccion",
                "unemail@email.com", "a", TipoDocumento.DNI, Sexo.Masculino);
        TitularDTO titular = new TitularDTO(TipoDocumento.DNI, 1, "Juan", "Gonzalez",
                LocalDate.of(2000, 1, 1), "direccion", GrupoSanguineo.A, FactorRH.Negativo, true,
                "limitaciones1");
        LicenciaDTO licencia1 = new LicenciaDTO(titular, administrador1,
                LocalDate.of(2020, 1, 1), LocalDate.of(2021, 1, 1), clase);
        
        // MENOR A 21 PRIMERA VEZ
        titular.fechaDeNacimiento = LocalDate.of(2005, 1, 1);
        assertEquals(20+8, gestor.CalcularCostoLicencia(titular, clase));
        // MENOR A 21 SEGUNDA VEZ
        try {
                gestor.CrearAdministrador(administrador1);
                gestor.CrearTitular(titular);
                gestor.CrearLicencia(licencia1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        assertEquals(25+8, gestor.CalcularCostoLicencia(titular, clase));
        // MAYOR A 21
        titular.fechaDeNacimiento = LocalDate.of(2000, 1, 1);
        assertEquals(40+8, gestor.CalcularCostoLicencia(titular, clase));
        // MAYOR A 46
        titular.fechaDeNacimiento = LocalDate.of(1977, 1, 1);
        assertEquals(30+8, gestor.CalcularCostoLicencia(titular, clase));
        // MAYOR A 60
        titular.fechaDeNacimiento = LocalDate.of(1963, 1, 1);
        assertEquals(25+8, gestor.CalcularCostoLicencia(titular, clase));
        // MAYOR A 70
        titular.fechaDeNacimiento = LocalDate.of(1953, 1, 1);
        assertEquals(20+8, gestor.CalcularCostoLicencia(titular, clase));
    }

    @Test
    public void TestCalcularCostoClaseB() {

        Clase clase = Clase.B;
        AdministradorDTO administrador1 = new AdministradorDTO(1, "Jhon", "Doe", LocalDate.of(2000, 1, 1),
                "direccion",
                "unemail@email.com", "a", TipoDocumento.DNI, Sexo.Masculino);
        TitularDTO titular = new TitularDTO(TipoDocumento.DNI, 1, "Juan", "Gonzalez",
                LocalDate.of(2000, 1, 1), "direccion", GrupoSanguineo.A, FactorRH.Negativo, true,
                "limitaciones1");
        LicenciaDTO licencia1 = new LicenciaDTO(titular, administrador1,
                LocalDate.of(2020, 1, 1), LocalDate.of(2021, 1, 1), clase);
        
        // MENOR A 21 PRIMERA VEZ
        titular.fechaDeNacimiento = LocalDate.of(2005, 1, 1);
        assertEquals(20+8, gestor.CalcularCostoLicencia(titular, clase));
        // MENOR A 21 SEGUNDA VEZ
        try {
                gestor.CrearAdministrador(administrador1);
                gestor.CrearTitular(titular);
                gestor.CrearLicencia(licencia1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        assertEquals(25+8, gestor.CalcularCostoLicencia(titular, clase));
        // MAYOR A 21
        titular.fechaDeNacimiento = LocalDate.of(2000, 1, 1);
        assertEquals(40+8, gestor.CalcularCostoLicencia(titular, clase));
        // MAYOR A 46
        titular.fechaDeNacimiento = LocalDate.of(1977, 1, 1);
        assertEquals(30+8, gestor.CalcularCostoLicencia(titular, clase));
        // MAYOR A 60
        titular.fechaDeNacimiento = LocalDate.of(1963, 1, 1);
        assertEquals(25+8, gestor.CalcularCostoLicencia(titular, clase));
        // MAYOR A 70
        titular.fechaDeNacimiento = LocalDate.of(1953, 1, 1);
        assertEquals(20+8, gestor.CalcularCostoLicencia(titular, clase));
    }

    @Test
    public void TestCalcularCostoClaseC() {

        Clase clase = Clase.C;
        TitularDTO titular = new TitularDTO(TipoDocumento.DNI, 1, "Juan", "Gonzalez",
                LocalDate.of(2000, 1, 1), "direccion", GrupoSanguineo.A, FactorRH.Negativo, true,
                "limitaciones1");
        // MAYOR A 21
        titular.fechaDeNacimiento = LocalDate.of(2000, 1, 1);
        assertEquals(47+8, gestor.CalcularCostoLicencia(titular, clase));
        // MAYOR A 46
        titular.fechaDeNacimiento = LocalDate.of(1977, 1, 1);
        assertEquals(35+8, gestor.CalcularCostoLicencia(titular, clase));
        // MAYOR A 60
        titular.fechaDeNacimiento = LocalDate.of(1963, 1, 1);
        assertEquals(30+8, gestor.CalcularCostoLicencia(titular, clase));
        // MAYOR A 70
        titular.fechaDeNacimiento = LocalDate.of(1953, 1, 1);
        assertEquals(23+8, gestor.CalcularCostoLicencia(titular, clase));
    }

    @Test
    public void TestCalcularCostoClaseD1() {

        Clase clase = Clase.D1;
        TitularDTO titular = new TitularDTO(TipoDocumento.DNI, 1, "Juan", "Gonzalez",
                LocalDate.of(2000, 1, 1), "direccion", GrupoSanguineo.A, FactorRH.Negativo, true,
                "limitaciones1");
        // MAYOR A 21
        titular.fechaDeNacimiento = LocalDate.of(2000, 1, 1);
        assertEquals(40+8, gestor.CalcularCostoLicencia(titular, clase));
        // MAYOR A 46
        titular.fechaDeNacimiento = LocalDate.of(1977, 1, 1);
        assertEquals(30+8, gestor.CalcularCostoLicencia(titular, clase));
        // MAYOR A 60
        titular.fechaDeNacimiento = LocalDate.of(1963, 1, 1);
        assertEquals(25+8, gestor.CalcularCostoLicencia(titular, clase));
        // MAYOR A 70
        titular.fechaDeNacimiento = LocalDate.of(1953, 1, 1);
        assertEquals(20+8, gestor.CalcularCostoLicencia(titular, clase));
    }

    @Test
    public void TestCalcularCostoClaseD2() {

        Clase clase = Clase.D2;
        TitularDTO titular = new TitularDTO(TipoDocumento.DNI, 1, "Juan", "Gonzalez",
                LocalDate.of(2000, 1, 1), "direccion", GrupoSanguineo.A, FactorRH.Negativo, true,
                "limitaciones1");
        
        // MAYOR A 21
        titular.fechaDeNacimiento = LocalDate.of(2000, 1, 1);
        assertEquals(47+8, gestor.CalcularCostoLicencia(titular, clase));
        // MAYOR A 46
        titular.fechaDeNacimiento = LocalDate.of(1977, 1, 1);
        assertEquals(35+8, gestor.CalcularCostoLicencia(titular, clase));
        // MAYOR A 60
        titular.fechaDeNacimiento = LocalDate.of(1963, 1, 1);
        assertEquals(30+8, gestor.CalcularCostoLicencia(titular, clase));
        // MAYOR A 70
        titular.fechaDeNacimiento = LocalDate.of(1953, 1, 1);
        assertEquals(23+8, gestor.CalcularCostoLicencia(titular, clase));
    }

    @Test
    public void TestCalcularCostoClaseE() {

        Clase clase = Clase.E;
        TitularDTO titular = new TitularDTO(TipoDocumento.DNI, 1, "Juan", "Gonzalez",
                LocalDate.of(2000, 1, 1), "direccion", GrupoSanguineo.A, FactorRH.Negativo, true,
                "limitaciones1");
        
        // MAYOR A 21
        titular.fechaDeNacimiento = LocalDate.of(2000, 1, 1);
        assertEquals(59+8, gestor.CalcularCostoLicencia(titular, clase));
        // MAYOR A 46
        titular.fechaDeNacimiento = LocalDate.of(1977, 1, 1);
        assertEquals(44+8, gestor.CalcularCostoLicencia(titular, clase));
        // MAYOR A 60
        titular.fechaDeNacimiento = LocalDate.of(1963, 1, 1);
        assertEquals(39+8, gestor.CalcularCostoLicencia(titular, clase));
        // MAYOR A 70
        titular.fechaDeNacimiento = LocalDate.of(1953, 1, 1);
        assertEquals(29+8, gestor.CalcularCostoLicencia(titular, clase));
    }

    @Test
    public void TestCalcularCostoClaseF() {

        Clase clase = Clase.F;
        AdministradorDTO administrador1 = new AdministradorDTO(1, "Jhon", "Doe", LocalDate.of(2000, 1, 1),
                "direccion",
                "unemail@email.com", "a", TipoDocumento.DNI, Sexo.Masculino);
        TitularDTO titular = new TitularDTO(TipoDocumento.DNI, 1, "Juan", "Gonzalez",
                LocalDate.of(2000, 1, 1), "direccion", GrupoSanguineo.A, FactorRH.Negativo, true,
                "limitaciones1");
        LicenciaDTO licencia1 = new LicenciaDTO(titular, administrador1,
                LocalDate.of(2020, 1, 1), LocalDate.of(2021, 1, 1), Clase.F);
        
        // MENOR A 21 PRIMERA VEZ
        titular.fechaDeNacimiento = LocalDate.of(2005, 1, 1);
        assertEquals(0+8, gestor.CalcularCostoLicencia(titular, clase));
        // MENOR A 21 SEGUNDA VEZ
        try {
                gestor.CrearAdministrador(administrador1);
                gestor.CrearTitular(titular);
                gestor.CrearLicencia(licencia1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        assertEquals(0+8, gestor.CalcularCostoLicencia(titular, clase));

        // MAYOR A 21
        titular.fechaDeNacimiento = LocalDate.of(2000, 1, 1);
        assertEquals(0+8, gestor.CalcularCostoLicencia(titular, clase));
        // MAYOR A 46
        titular.fechaDeNacimiento = LocalDate.of(1977, 1, 1);
        assertEquals(0+8, gestor.CalcularCostoLicencia(titular, clase));
        // MAYOR A 60
        titular.fechaDeNacimiento = LocalDate.of(1963, 1, 1);
        assertEquals(0+8, gestor.CalcularCostoLicencia(titular, clase));
        // MAYOR A 70
        titular.fechaDeNacimiento = LocalDate.of(1953, 1, 1);
        assertEquals(0+8, gestor.CalcularCostoLicencia(titular, clase));
    }

    @Test
    public void TestCalcularCostoClaseG() {

        Clase clase = Clase.G;
        AdministradorDTO administrador1 = new AdministradorDTO(1, "Jhon", "Doe", LocalDate.of(2000, 1, 1),
                "direccion",
                "unemail@email.com", "a", TipoDocumento.DNI, Sexo.Masculino);
        TitularDTO titular = new TitularDTO(TipoDocumento.DNI, 1, "Juan", "Gonzalez",
                LocalDate.of(2000, 1, 1), "direccion", GrupoSanguineo.A, FactorRH.Negativo, true,
                "limitaciones1");
        LicenciaDTO licencia1 = new LicenciaDTO(titular, administrador1,
                LocalDate.of(2020, 1, 1), LocalDate.of(2021, 1, 1), Clase.G);
        
        // MENOR A 21 PRIMERA VEZ
        titular.fechaDeNacimiento = LocalDate.of(2005, 1, 1);
        assertEquals(20+8, gestor.CalcularCostoLicencia(titular, clase));
        // MENOR A 21 SEGUNDA VEZ
        try {
                gestor.CrearAdministrador(administrador1);
                gestor.CrearTitular(titular);
                gestor.CrearLicencia(licencia1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        assertEquals(25+8, gestor.CalcularCostoLicencia(titular, clase));

        // MAYOR A 21
        titular.fechaDeNacimiento = LocalDate.of(2000, 1, 1);
        assertEquals(40+8, gestor.CalcularCostoLicencia(titular, clase));
        // MAYOR A 46
        titular.fechaDeNacimiento = LocalDate.of(1977, 1, 1);
        assertEquals(30+8, gestor.CalcularCostoLicencia(titular, clase));
        // MAYOR A 60
        titular.fechaDeNacimiento = LocalDate.of(1963, 1, 1);
        assertEquals(25+8, gestor.CalcularCostoLicencia(titular, clase));
        // MAYOR A 70
        titular.fechaDeNacimiento = LocalDate.of(1953, 1, 1);
        assertEquals(20+8, gestor.CalcularCostoLicencia(titular, clase));
    }


    @Test
    public void TestCalcularVigencia() {
        AdministradorDTO administrador1 = new AdministradorDTO(1, "Jhon", "Doe", LocalDate.of(2000, 1, 1),
                "direccion",
                "unemail@email.com", "a", TipoDocumento.DNI, Sexo.Masculino);
        TitularDTO titular = new TitularDTO(TipoDocumento.DNI, 1, "Juan", "Gonzalez",
                LocalDate.of(2000, 1, 1), "direccion", GrupoSanguineo.A, FactorRH.Negativo, true,
                "limitaciones1");
        LicenciaDTO licencia1 = new LicenciaDTO(titular, administrador1,
                LocalDate.of(2020, 1, 1), LocalDate.of(2021, 1, 1), Clase.B);
        
        // MENOR A 21 PRIMERA VEZ
        titular.fechaDeNacimiento = LocalDate.of(2005, 1, 1);
        assertEquals(1, gestor.CalcularVigenciaLicencia(titular, Clase.B));
        // MENOR A 21 SEGUNDA VEZ
        try {
                gestor.CrearAdministrador(administrador1);
                gestor.CrearTitular(titular);
                gestor.CrearLicencia(licencia1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        assertEquals(3, gestor.CalcularVigenciaLicencia(titular, Clase.B));

        // MAYOR A 21
        titular.fechaDeNacimiento = LocalDate.of(2000, 1, 1);
        assertEquals(5, gestor.CalcularVigenciaLicencia(titular, Clase.B));
        // MAYOR A 46
        titular.fechaDeNacimiento = LocalDate.of(1977, 1, 1);
        assertEquals(4, gestor.CalcularVigenciaLicencia(titular, Clase.B));
        // MAYOR A 60
        titular.fechaDeNacimiento = LocalDate.of(1963, 1, 1);
        assertEquals(3, gestor.CalcularVigenciaLicencia(titular, Clase.B));
        // MAYOR A 70
        titular.fechaDeNacimiento = LocalDate.of(1953, 1, 1);
        assertEquals(1, gestor.CalcularVigenciaLicencia(titular, Clase.B));
    }
}

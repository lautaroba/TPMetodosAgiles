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

public class AppTest {
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
        public void TestCrearAdministrador() {
                AdministradorDTO administrador1 = new AdministradorDTO(1, "admin1", "admin1", LocalDate.now(),
                                "direccion1",
                                "unemail@email.com", "a", TipoDocumento.DNI, Sexo.Masculino);
                try {
                        gestor.CrearAdministrador(administrador1);
                } catch (Exception e) {
                        e.printStackTrace();
                }
                assertEquals(administrador1, gestor.BuscarAdministrador(administrador1));
        }

        @Test
        public void TestCrearTitular() {
                TitularDTO titularDTO1 = new TitularDTO(TipoDocumento.DNI, 1, "Matias", "24 años",
                                LocalDate.of(2000,11,11), "direccion1", GrupoSanguineo.A, FactorRH.Negativo, true,
                                "limitaciones");
                TitularDTO titularDTO2 = new TitularDTO(TipoDocumento.DNI, 2, "Fernando", "18 años",
                                LocalDate.of(2006,11,11), "direccion2", GrupoSanguineo.AB, FactorRH.Positivo, true,
                                "limitaciones2, limitaciones3");
                TitularDTO titularDTO3 = new TitularDTO(TipoDocumento.DNI, 3, "Lautaro", "50 años",
                                LocalDate.of(1975,11,11), "direccion3", GrupoSanguineo.B, FactorRH.Positivo, true,
                                "limitaciones, limitaciones2, limitaciones3");
                TitularDTO titularDTO4 = new TitularDTO(TipoDocumento.DNI, 4, "Agustin", "60 años",
                                LocalDate.of(1960,11,11), "direccion4", GrupoSanguineo.B, FactorRH.Negativo, true,
                                "limitaciones, limitaciones2, limitaciones3");
                TitularDTO titularDTO5 = new TitularDTO(TipoDocumento.DNI, 5, "Jazmín", "clase D",
                                LocalDate.of(2000,11,11), "direccion5", GrupoSanguineo.O, FactorRH.Negativo, false,
                                "");
                TitularDTO titularDTO6 = new TitularDTO(TipoDocumento.DNI, 6, "José", "clase F",
                                LocalDate.of(2000,11,11),
                                "direccion6", GrupoSanguineo.O, FactorRH.Positivo, false,
                                "limitaciones, limitaciones2, limitaciones3, limitaciones4");
                TitularDTO titularDTO7 = new TitularDTO(TipoDocumento.DNI, 7, "María", "74 años",
                                LocalDate.of(1950,11,11),
                                "direccion7", GrupoSanguineo.O, FactorRH.Positivo, true,
                                "limitaciones, limitaciones2, limitaciones3");

                try {
                        gestor.CrearTitular(titularDTO1);
                        gestor.CrearTitular(titularDTO2);
                        gestor.CrearTitular(titularDTO3);
                        gestor.CrearTitular(titularDTO4);
                        gestor.CrearTitular(titularDTO5);
                        gestor.CrearTitular(titularDTO6);
                        gestor.CrearTitular(titularDTO7);
                } catch (Exception e) {
                        e.printStackTrace();
                }

                assertEquals(titularDTO1, gestor.BuscarTitular(titularDTO1));
                assertEquals(titularDTO2, gestor.BuscarTitular(titularDTO2));
                assertEquals(titularDTO3, gestor.BuscarTitular(titularDTO3));
                assertEquals(titularDTO4, gestor.BuscarTitular(titularDTO4));
                assertEquals(titularDTO5, gestor.BuscarTitular(titularDTO5));
                assertEquals(titularDTO6, gestor.BuscarTitular(titularDTO6));
                assertEquals(titularDTO7, gestor.BuscarTitular(titularDTO7));
        }

        /*
         * Fallos
         * 1, menor de 17 años intenta sacar licencia
         * 2, menor de 21 años intenta sacar licencia profesional
         * 3, mayor de 65 años intenta sacar licencia profesional
         * 4, persona sin B previa intenta sacar profesional
         * 5, persona con B previa sin un año intenta sacar profesional
         * 6, persona con F activa intenta sacar cualquier otro tipo de licencia
         * 7, persona con *(Clase X) obtenida y vencida intenta sacarla nuevamente.
         * 8, persona con *(Clase X) obtenida y vigente intenta sacarla nuevamente.
         * 9, persona con *(Clase X) superior y vigente intenta sacar una inferior.
         */
        @Test
        public void TestCrearLicenciaFallos() {
                AdministradorDTO administrador1 = new AdministradorDTO(1, "Jhon", "Doe", LocalDate.now(),
                                "direccion",
                                "unemail@email.com", "a", TipoDocumento.DNI, Sexo.Masculino);
                TitularDTO titularMenorDe17 = new TitularDTO(TipoDocumento.DNI, 1, "Juan", "Gonzalez",
                                LocalDate.of(2008, 1, 1), "direccion", GrupoSanguineo.A, FactorRH.Negativo, true,
                                "limitaciones1");
                LicenciaDTO licencia1 = new LicenciaDTO(titularMenorDe17, administrador1,
                                LocalDate.now(), LocalDate.of(2025,1,1), Clase.A);
                try {
                        gestor.CrearLicencia(licencia1);
                } catch (Exception e) {
                        e.printStackTrace();
                }
                assertEquals(null, gestor.BuscarLicenciasTitular(titularMenorDe17, LocalDate.of(1,1,1)));
        }

        @Test
        public void TestCrearLicenciaFallos2() {
                AdministradorDTO administrador1 = new AdministradorDTO(1, "Jhon", "Doe", LocalDate.now(),
                                "direccion",
                                "unemail@email.com", "a", TipoDocumento.DNI, Sexo.Masculino);
                TitularDTO titularMenorDe21 = new TitularDTO(TipoDocumento.DNI, 1, "Juan", "Gonzalez",
                                LocalDate.of(2006,1,1), "direccion", GrupoSanguineo.A, FactorRH.Negativo, true,
                                "limitaciones1");
                LicenciaDTO licencia1 = new LicenciaDTO(titularMenorDe21, administrador1,
                                LocalDate.now(), LocalDate.of(2025,1,1), Clase.C);
                try {
                        gestor.CrearLicencia(licencia1);
                } catch (Exception e) {
                        e.printStackTrace();
                }
                assertTrue(gestor.BuscarLicenciasTitular(titularMenorDe21, LocalDate.of(1,1,1)).isEmpty());
        }

        @Test
        public void TestCrearLicenciaFallos3() {
                AdministradorDTO administrador1 = new AdministradorDTO(1, "Jhon", "Doe", LocalDate.now(),
                                "direccion",
                                "unemail@email.com", "a", TipoDocumento.DNI, Sexo.Masculino);
                TitularDTO titularMayorDe65 = new TitularDTO(TipoDocumento.DNI, 1, "Juan", "Gonzalez",
                                LocalDate.of(1958,1,1), "direccion", GrupoSanguineo.A, FactorRH.Negativo, true,
                                "limitaciones1");
                LicenciaDTO licencia1 = new LicenciaDTO(titularMayorDe65, administrador1,
                                LocalDate.now(), LocalDate.of(2025,1,1), Clase.C);
                try {
                        gestor.CrearLicencia(licencia1);
                } catch (Exception e) {
                        e.printStackTrace();
                }
                assertTrue(gestor.BuscarLicenciasTitular(titularMayorDe65, LocalDate.of(1,1,1)).isEmpty());
        }
        
        @Test
        public void TestCrearLicenciaFallos4() {
                AdministradorDTO administrador1 = new AdministradorDTO(1, "Jhon", "Doe", LocalDate.now(),
                                "direccion",
                                "unemail@email.com", "a", TipoDocumento.DNI, Sexo.Masculino);
                TitularDTO titularSinBPrevia = new TitularDTO(TipoDocumento.DNI, 1, "Juan", "Gonzalez",
                                LocalDate.of(2000,1,1), "direccion", GrupoSanguineo.A, FactorRH.Negativo, true,
                                "limitaciones1");
                LicenciaDTO licencia1 = new LicenciaDTO(titularSinBPrevia, administrador1,
                                LocalDate.now(), LocalDate.of(2025,1,1), Clase.C);
                try {
                        gestor.CrearLicencia(licencia1);
                } catch (Exception e) {
                        e.printStackTrace();
                }
                assertTrue(gestor.BuscarLicenciasTitular(titularSinBPrevia, LocalDate.of(1,1,1)).isEmpty());
        }

        @Test
        public void TestCrearLicenciaFallos5() {
                AdministradorDTO administrador1 = new AdministradorDTO(1, "Jhon", "Doe", LocalDate.now(),
                                "direccion",
                                "unemail@email.com", "a", TipoDocumento.DNI, Sexo.Masculino);
                TitularDTO titularConBPreviaSin1anio = new TitularDTO(TipoDocumento.DNI, 1, "Juan", "Gonzalez",
                                LocalDate.of(2000,1,1), "direccion", GrupoSanguineo.A, FactorRH.Negativo, true,
                                "limitaciones1");
                LicenciaDTO licencia1 = new LicenciaDTO(titularConBPreviaSin1anio, administrador1,
                                LocalDate.now(), LocalDate.of(2025,1,1), Clase.B);

                LicenciaDTO licencia2 = new LicenciaDTO(titularConBPreviaSin1anio, administrador1,
                                LocalDate.now(), LocalDate.of(2026,1,1), Clase.C);
                try {
                        gestor.CrearLicencia(licencia1);
                        gestor.CrearLicencia(licencia2);
                } catch (Exception e) {
                        e.printStackTrace();
                }
                assertEquals(1, gestor.BuscarLicenciasTitular(titularConBPreviaSin1anio, LocalDate.of(1,1,1)).size());
        }

        @Test
        public void TestCrearLicenciaFallos6() {
                AdministradorDTO administrador1 = new AdministradorDTO(1, "Jhon", "Doe", LocalDate.now(),
                                "direccion",
                                "unemail@email.com", "a", TipoDocumento.DNI, Sexo.Masculino);
                TitularDTO titularConFIntentaCualquierLicencia = new TitularDTO(TipoDocumento.DNI, 1, "Juan", "Gonzalez",
                                LocalDate.of(2000,1,1), "direccion", GrupoSanguineo.A, FactorRH.Negativo, true,
                                "limitaciones1");
                LicenciaDTO licencia1 = new LicenciaDTO(titularConFIntentaCualquierLicencia, administrador1,
                                LocalDate.now(), LocalDate.of(2025,1,1), Clase.F);

                LicenciaDTO licencia2 = new LicenciaDTO(titularConFIntentaCualquierLicencia, administrador1,
                                LocalDate.now(), LocalDate.of(2026,1,1), Clase.A);

                LicenciaDTO licencia3 = new LicenciaDTO(titularConFIntentaCualquierLicencia, administrador1,
                                LocalDate.now(), LocalDate.of(2026,1,1), Clase.B);
                LicenciaDTO licencia4 = new LicenciaDTO(titularConFIntentaCualquierLicencia, administrador1,
                                LocalDate.now(), LocalDate.of(2026,1,1), Clase.G);      
                try {
                        gestor.CrearLicencia(licencia1);
                        gestor.CrearLicencia(licencia2);
                        gestor.CrearLicencia(licencia3);
                        gestor.CrearLicencia(licencia4);
                } catch (Exception e) {
                        e.printStackTrace();
                }
                assertEquals(1, gestor.BuscarLicenciasTitular(titularConFIntentaCualquierLicencia, LocalDate.of(1,1,1)).size());
        }

        @Test
        public void TestCrearLicenciaFallos7() {
                AdministradorDTO administrador1 = new AdministradorDTO(1, "Jhon", "Doe", LocalDate.now(),
                                "direccion",
                                "unemail@email.com", "a", TipoDocumento.DNI, Sexo.Masculino);
                TitularDTO titularConBIntentaSacarlaNuevamente = new TitularDTO(TipoDocumento.DNI, 1, "Juan", "Gonzalez",
                                LocalDate.of(2000,1,1), "direccion", GrupoSanguineo.A, FactorRH.Negativo, true,
                                "limitaciones1");
                LicenciaDTO licencia1 = new LicenciaDTO(titularConBIntentaSacarlaNuevamente, administrador1,
                                LocalDate.of(2022,1,1), LocalDate.of(2023,1,1), Clase.B);
                LicenciaDTO licencia2 = new LicenciaDTO(titularConBIntentaSacarlaNuevamente, administrador1,
                                LocalDate.now(), LocalDate.of(2026,1,1), Clase.B);
                try {
                        gestor.CrearLicencia(licencia1);
                        gestor.CrearLicencia(licencia2);
                } catch (Exception e) {
                        e.printStackTrace();
                }
                assertEquals(1, gestor.BuscarLicenciasTitular(titularConBIntentaSacarlaNuevamente, LocalDate.of(1,1,1)).size());
        }

        @Test
        public void TestCrearLicenciaFallos8() {
                AdministradorDTO administrador1 = new AdministradorDTO(1, "Jhon", "Doe", LocalDate.now(),
                                "direccion",
                                "unemail@email.com", "a", TipoDocumento.DNI, Sexo.Masculino);
                TitularDTO titularConBIntentaSacarlaNuevamente = new TitularDTO(TipoDocumento.DNI, 1, "Juan", "Gonzalez",
                                LocalDate.of(2000,1,1), "direccion", GrupoSanguineo.A, FactorRH.Negativo, true,
                                "limitaciones1");
                LicenciaDTO licencia1 = new LicenciaDTO(titularConBIntentaSacarlaNuevamente, administrador1,
                                LocalDate.of(2024,1,1), LocalDate.of(2025,1,1), Clase.B);
                LicenciaDTO licencia2 = new LicenciaDTO(titularConBIntentaSacarlaNuevamente, administrador1,
                                LocalDate.now(), LocalDate.of(2026,1,1), Clase.B);
                try {
                        gestor.CrearLicencia(licencia1);
                        gestor.CrearLicencia(licencia2);
                } catch (Exception e) {
                        e.printStackTrace();
                }
                assertEquals(1, gestor.BuscarLicenciasTitular(titularConBIntentaSacarlaNuevamente, LocalDate.of(1,1,1)).size());
        }

}

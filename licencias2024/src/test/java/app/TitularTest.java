package app;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import app.DTOs.TitularDTO;
import app.Enumeradores.FactorRH;
import app.Enumeradores.GrupoSanguineo;
import app.Enumeradores.TipoDocumento;

public class TitularTest {

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
    public void TestCrearTitular() {
        TitularDTO titularDTO1 = new TitularDTO(TipoDocumento.DNI, 1, "Matias", "24 años",
                LocalDate.of(2000, 11, 11), "direccion1", GrupoSanguineo.A, FactorRH.Negativo, true,
                "limitaciones");
        TitularDTO titularDTO2 = new TitularDTO(TipoDocumento.DNI, 2, "Fernando", "18 años",
                LocalDate.of(2006, 11, 11), "direccion2", GrupoSanguineo.AB, FactorRH.Positivo, true,
                "limitaciones2, limitaciones3");
        TitularDTO titularDTO3 = new TitularDTO(TipoDocumento.DNI, 3, "Lautaro", "50 años",
                LocalDate.of(1975, 11, 11), "direccion3", GrupoSanguineo.B, FactorRH.Positivo, true,
                "limitaciones, limitaciones2, limitaciones3");
        TitularDTO titularDTO4 = new TitularDTO(TipoDocumento.DNI, 4, "Agustin", "60 años",
                LocalDate.of(1960, 11, 11), "direccion4", GrupoSanguineo.B, FactorRH.Negativo, true,
                "limitaciones, limitaciones2, limitaciones3");
        TitularDTO titularDTO5 = new TitularDTO(TipoDocumento.DNI, 5, "Jazmín", "clase D",
                LocalDate.of(2000, 11, 11), "direccion5", GrupoSanguineo.O, FactorRH.Negativo, false,
                "");
        TitularDTO titularDTO6 = new TitularDTO(TipoDocumento.DNI, 6, "José", "clase F",
                LocalDate.of(2000, 11, 11),
                "direccion6", GrupoSanguineo.O, FactorRH.Positivo, false,
                "limitaciones, limitaciones2, limitaciones3, limitaciones4");
        TitularDTO titularDTO7 = new TitularDTO(TipoDocumento.DNI, 7, "María", "74 años",
                LocalDate.of(1950, 11, 11),
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

        assertEquals(titularDTO1.nroDNI, gestor.BuscarTitular(titularDTO1).nroDNI);
        assertEquals(titularDTO2.nroDNI, gestor.BuscarTitular(titularDTO2).nroDNI);
        assertEquals(titularDTO3.nroDNI, gestor.BuscarTitular(titularDTO3).nroDNI);
        assertEquals(titularDTO4.nroDNI, gestor.BuscarTitular(titularDTO4).nroDNI);
        assertEquals(titularDTO5.nroDNI, gestor.BuscarTitular(titularDTO5).nroDNI);
        assertEquals(titularDTO6.nroDNI, gestor.BuscarTitular(titularDTO6).nroDNI);
        assertEquals(titularDTO7.nroDNI, gestor.BuscarTitular(titularDTO7).nroDNI);
    }
}

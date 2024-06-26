package app;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import app.DTOs.AdministradorDTO;
import app.Enumeradores.Sexo;
import app.Enumeradores.TipoDocumento;

public class AdministradorTest {
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
        AdministradorDTO administrador1 = new AdministradorDTO(1, "admin1", "admin1", LocalDate.of(2000, 1, 1),
                "direccion1",
                "unemail@email.com", "a", TipoDocumento.DNI, Sexo.Masculino);
        try {
            gestor.CrearAdministrador(administrador1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(administrador1.dni, gestor.BuscarAdministrador(administrador1).dni);
    }
}

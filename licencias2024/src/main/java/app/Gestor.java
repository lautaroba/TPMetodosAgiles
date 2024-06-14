package app;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import app.DAOs.*;
import app.DTOs.*;
import app.Entidades.Administrador;
import app.Entidades.Licencia;
import app.Entidades.Titular;
import app.Enumeradores.Clase;

public class Gestor {

    public AdministradorDTO administradorLogeado;
    private AdministradorDAO gestorAdministrador;
    private TitularDAO gestorTitular;
    private LicenciaDAO gestorLicencia;
    private static int[][] costos = { { 40, 30, 25, 20 },
            { 40, 30, 25, 20 },
            { 47, 35, 30, 23 },
            { 59, 44, 39, 29 },
            { 40, 30, 25, 20 } };
    public int costo;
    public int vigencia;

    public Gestor() {
        gestorAdministrador = new AdministradorDAO();
        gestorTitular = new TitularDAO();
        gestorLicencia = new LicenciaDAO();
    }

    public TitularDTO BuscarTitular(TitularDTO titular) {
        try {
            return new TitularDTO(gestorTitular.getTitular(titular.nroDNI));
        } catch (Exception e) {
            throw e;
        }

    }

    public void CrearTitular(TitularDTO titular) {
        try {
            gestorTitular.CrearTitular(new Titular(titular));
        } catch (Exception e) {
            throw e;
        }
    }

    public void ModificarTitular(TitularDTO titular) {
        try {
            gestorTitular.ModificarTitular(new Titular(titular));
        } catch (Exception e) {
            throw e;
        }
    }

    public AdministradorDTO BuscarAdministrador(AdministradorDTO administrador) {

        try {
            administradorLogeado = new AdministradorDTO(gestorAdministrador.getAdministrador(administrador.dni));
            return administradorLogeado;
        } catch (Exception e) {
            throw e;
        }
    }

    public void CrearAdministrador(AdministradorDTO administrador) {
        try {
            gestorAdministrador.CrearAdministrador(new Administrador(administrador));
        } catch (Exception e) {
            throw e;
        }

    }

    public void ModificarAdministrador(AdministradorDTO administrador) {
        try {
            gestorAdministrador.ModificarAdministrador(new Administrador(administrador));
        } catch (Exception e) {
            throw e;
        }
    }

    public void CrearLicencia(LicenciaDTO licencia) {

        try {

            gestorLicencia.CrearLicencia(new Licencia(gestorTitular.getTitular(licencia.titular.nroDNI),
                    gestorAdministrador.getAdministrador(administradorLogeado.dni),
                    licencia.fechaDeEmision, licencia.fechaDeExpiracion, licencia.activa));

        } catch (Exception e) {
            throw e;
        }
    }

    public int CalcularCostoLicencia(TitularDTO titular) {
        // la idea es que la clase de la licencia se en mayuscula: A B C E G
        int edad = Period.between(titular.fechaDeNacimiento, LocalDate.now()).getYears();
        int costo = 8;
        int categoria = titular.clase.getNro();

        if (titular.clase == Clase.D1 || titular.clase == Clase.D2) {
            return costo;
        } else if (titular.clase == Clase.F) {
            return costo;
        } else if (edad < 21) {
            if (primeraVez(titular))
                return costo += costos[categoria][3];
            else
                return costo += costos[categoria][2];
        } else if (edad > 21 && edad <= 46) {
            return costo += costos[categoria][0];
        } else if (edad > 46 && edad <= 60) {
            return costo += costos[categoria][1];
        } else if (edad > 60 && edad <= 70) {
            return costo += costos[categoria][2];
        } else {
            return costo += costos[categoria][3];
        }
    }

    public int CalcularVigenciaLicencia(TitularDTO titular) {

        int edad = Period.between(titular.fechaDeNacimiento, LocalDate.now()).getYears();
        if (edad < 21) {
            if (primeraVez(titular))
                return 1;
            else
                return 3;
        } else if (edad > 21 && edad <= 46) {
            return 5;
        } else if (edad > 46 && edad <= 60) {
            return 4;
        } else if (edad > 60 && edad <= 70) {
            return 3;
        } else {
            return 1;
        }
    }

    private boolean primeraVez(TitularDTO titular) {
        // busco en las licencias si existe alguna licencia cn el titular, y si no
        // existe god
        if (gestorLicencia.getPrimeraVez(titular) == null)
            return true;
        else
            return false;
    }

    public boolean BuscarLicenciaFecha(TitularDTO titular) {

        try {
            if (gestorLicencia.getLicenciaByTitularYFecha(titular.nroDNI, LocalDate.now()).isEmpty())
                return true;
            else
                return false;
        } catch (Exception e) {
            throw e;
        }
    }

    public List<LicenciaDTO> ListadoLicenciasExpiradas() {
        try {
            List<LicenciaDTO> lista = new ArrayList<LicenciaDTO>();
            for (Licencia l : gestorLicencia.getLicenciasExpiradas()) {
                lista.add(new LicenciaDTO(l));
            }
            return lista;
        } catch (Exception e) {
            throw e;
        }
    }

    public List<LicenciaDTO> ListadoLicenciasVigentes() {
        try {
            List<LicenciaDTO> lista = new ArrayList<LicenciaDTO>();
            for (Licencia l : gestorLicencia.getLicenciasVigentes()) {
                lista.add(new LicenciaDTO(l));
            }
            return lista;
        } catch (Exception e) {
            throw e;
        }
    }
}
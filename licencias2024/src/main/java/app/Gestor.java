package app;

import java.time.LocalDate;
import java.time.Period;

import app.DAOs.*;
import app.DTOs.*;
import app.Entidades.Administrador;
import app.Entidades.Titular;
import app.Enunumenadores.Clase;

public class Gestor {

    private AdministradorDAO gestorAdministrador;
    private TitularDAO gestorTitular;
    private LicenciaDAO gestorLicencia;
    private static int[][] costos = {{40, 30, 25, 20}, {40, 30, 25, 20}, {47, 35, 30, 23}, {59, 44, 39, 29}, {40, 30, 25, 20}};

    public Gestor(){
        gestorAdministrador = new AdministradorDAO();
        gestorTitular = new TitularDAO();
        gestorLicencia = new LicenciaDAO();
    }

    public TitularDTO BuscarTitular(TitularDTO titular){
        try {
            return new TitularDTO(gestorTitular.getTitular(titular.nroDNI));
        } catch (Exception e) {
            throw e;
        }
        
    }

    public void CrearTitular(TitularDTO titular){
        try{
            gestorTitular.CrearTitular(new Titular(titular));
        }catch(Exception e){
           throw e;
        }
    }

    public void ModificarTitular(TitularDTO titular){
        try{
            gestorTitular.ModificarTitular(new Titular(titular));
        }catch(Exception e){
            throw e;
        }
    }

    public AdministradorDTO BuscarAdministrador(AdministradorDTO administrador){
        
        try{
            return new AdministradorDTO(gestorAdministrador.getAdministrador(administrador.dni));
        }
        catch(Exception e){
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

    // public LicenciaDTO BuscarLicencia(LicenciaDTO licencia){
    //     return new LicenciaDTO(gestorLicencia.getLicencia(licencia));
    // }

    // public void CrearLicencia(LicenciaDTO licencia){
    //     try{
    //         gestorLicencia.CrearLicencia(licencia);
    //     }catch(Exception e){
    //         System.out.println("Error");
    //     }
    // }

    // public void ModificarLicencia(LicenciaDTO licencia){
    //     try{
    //         gestorLicencia.ModificarLicencia(licencia);
    //     }catch(Exception e){
    //         System.out.println("Error");
    //     }
    // }

    // public int CalcularCostoLicencia(LicenciaDTO licencia){
    //     // la idea es que la clase de la licencia se en mayuscula: A B C E G
    //     int edad = Period.between(licencia.titular.fechaDeNacimiento, LocalDate.now()).getYears();
    //     int costo;
    //     int categoria = licencia.titular.clase.getNro();

    //     if(edad < 21){
    //         if(primeraVez(licencia.titular))
    //             costo = costos[categoria][3];
    //         else
    //             costo = costos[categoria][2];
    //     }
    //     else if(edad > 21 && edad <= 46){
    //         costo = costos[categoria][0];
    //     }
    //     else if(edad > 46 && edad <= 60){
    //         costo = costos[categoria][1];
    //     }
    //     else if(edad > 60 && edad <= 70){
    //         costo = costos[categoria][2];
    //     }
    //     else {
    //         if(licencia.titular.clase == Clase.D)
    //             return 0;
    //         else if(licencia.titular.clase == Clase.F)
    //             return 0;
    //         else
    //             costo = costos[categoria][3]; 
    //     }
    //     return costo+8;
    // }

    // private boolean primeraVez(Titular titular){
    //     // busco en las licencias si existe alguna licencia cn el titular, y si no existe god
    //     if(gestorLicencia.getPrimeraVez(titular) == null)
    //         return true;
    //     else
    //         return false;
    // }

    // public int CalcularVigenciaLicencia(LicenciaDTO licencia){

    //     int edad = Period.between(licencia.titular.fechaDeNacimiento, LocalDate.now()).getYears();

    //     if(edad < 21){
    //         if(primeraVez(licencia.titular))
    //             return 1;
    //         else
    //             return 3;
    //     }
    //     else if(edad > 21 && edad <= 46) {
    //         return 5;
    //     }
    //     else if(edad > 46 && edad <= 60){
    //         return 4;
    //     }
    //     else if(edad > 60 && edad <= 70){
    //         return 3;
    //     }
    //     else {
    //         return 1;
    //     }
    // }
}
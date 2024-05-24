package app;

import java.time.LocalDate;
import java.time.Period;

import app.DAOs.*;
import app.DTOs.*;
import app.Entidades.*;

public class Gestor {
    
    private AdministradorDAO gestorAdministrador;
    private TitularDAO gestorTitular;
    private LicenciaDAO gestorLicencia;
    private static int[][] costos = {{40, 30, 25, 20}, {40, 30, 25, 20}, {47, 35, 30, 23}, {59, 44, 39, 29}, {40, 30, 25, 20}};

    public Titular BuscarTitular(TitularDTO titular){
        return gestorTitular.getTitular(titular);
    }
    
    public void CrearTitular(TitularDTO titular){
        try{
            gestorTitular.CrearTitular(titular);
        }catch(Exception e){
            System.out.println("Error");
        }
    }
    
    public void ModificarTitular(TitularDTO titular){
        try{
            gestorTitular.ModificarTitular(titular);
        }catch(Exception e){
            System.out.println("Error");
        }
    }

    public void EliminarTitular(TitularDTO titular){
        gestorTitular.EliminarTitular(titular);
    }

    public Administrador BuscarAdministrador(AdministradorDTO administrador){
        return gestorAdministrador.getAdministrador(administrador);
    }
    
    public void CrearAdministrador(AdministradorDTO administrador){
        try{
            gestorAdministrador.CrearAdministrador(administrador);
        }catch(Exception e){
            System.out.println("Error");
        }
    }
    
    public void ModificarAdministrador(AdministradorDTO administrador){
        try{
            gestorAdministrador.ModificarAdministrador(administrador);
        }catch(Exception e){
            System.out.println("Error");
        }
    }

    public void EliminarAdministrador(AdministradorDTO administrador){
        gestorAdministrador.EliminarAdministrador(administrador);
    }

    public Licencia BuscarLicencia(LicenciaDTO licencia){
        return gestorLicencia.getLicencia(licencia);
    }
    
    public void CrearLicencia(LicenciaDTO licencia){
        try{
            gestorLicencia.CrearLicencia(licencia);
        }catch(Exception e){
            System.out.println("Error");
        }
    }
    
    public void ModificarLicencia(LicenciaDTO licencia){
        try{
            gestorLicencia.ModificarLicencia(licencia);
        }catch(Exception e){
            System.out.println("Error");
        }
    }

    public void EliminarLicencia(LicenciaDTO licencia){
        gestorLicencia.EliminarLicencia(licencia);
    }

    public int CalcularCostoLicencia(LicenciaDTO licencia){
        // la idea es que la clase de la licencia se en mayuscula: A B C E G 
        int edad = Period.between(licencia.titular.getFechaDeNacimiento(), LocalDate.now()).getYears();
        int costo;
        int categoria = 0;

        if(licencia.clase == 'A')
            categoria = 0;
        else if(licencia.clase == 'B')
            categoria = 1;
        else if(licencia.clase == 'C')
            categoria = 1;
        else if(licencia.clase == 'E')
            categoria = 2;
        else if(licencia.clase == 'G')
            categoria = 3;

        if(edad < 21){
            if(primeraVez(licencia))
                costo = costos[categoria][3];
            else
                costo = costos[categoria][2];
        }
        else if(edad > 21 && edad <= 46) {
            costo = costos[categoria][0];
        }
        else if(edad > 46 && edad <= 60){
            costo = costos[categoria][1];
        }
        else if(edad > 60 && edad <= 70){
            costo = costos[categoria][2];
        }
        else {
            if(licencia.clase == 'D')
                return 0;
            else
                costo = costos[categoria][3]; 
        }
        return costo+8;
    }

    private boolean primeraVez(LicenciaDTO licencia){
        if(gestorLicencia.getLicencia(licencia) == null)
            return true;
        else
            return false;
    }

    public int CalcularVigenciaLicencia(LicenciaDTO licencia){

        int edad = Period.between(licencia.titular.getFechaDeNacimiento(), LocalDate.now()).getYears();
        
        if(edad < 21){
            if(primeraVez(licencia))
                return 1;
            else
                return 3;
        }
        else if(edad > 21 && edad <= 46) {
            return 5;
        }
        else if(edad > 46 && edad <= 60){
            return 4;
        }
        else if(edad > 60 && edad <= 70){
            return 3;
        }
        else {
            return 1;
        }
    }
}
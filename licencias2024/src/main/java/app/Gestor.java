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

        int edad = Period.between(licencia.titular.getFechaDeNacimiento(), LocalDate.now()).getYears();
        int costo;
        if(edad < 21){
            if(primeraVez(licencia))
                costo = costos[licencia.clase - 65][3];
            else
                costo = costos[licencia.clase - 65][2];
        }
        else if(edad > 21 && edad <= 46) {
            costo = costos[licencia.clase - 65][0];
        }
        else if(edad > 46 && edad <= 60){
            costo = costos[licencia.clase - 65][1];
        }
        else if(edad > 60 && edad <= 70){
            costo = costos[licencia.clase - 65][2];
        }
        else {
            costo = costos[licencia.clase - 65][3]; 
        }
        return costo;
    }

    public boolean primeraVez(LicenciaDTO licencia){
        return true;
    }

}
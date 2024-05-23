package app.DAOs;

import app.App;
import app.DTOs.LicenciaDTO;
import app.Entidades.Licencia;

public class LicenciaDAO {
    
    Licencia licencia;
    
    public Licencia getLicencia(LicenciaDTO l){  
        return App.entityManager.find(Licencia.class, l);
    }

    public void CrearLicencia(LicenciaDTO l){

        if(getLicencia(l) == null){
            App.entityManager.getTransaction().begin();
            App.entityManager.merge(l);
            App.entityManager.getTransaction().commit();
        }
        else
            System.out.println("ya existe un licencia");
    }

    public void ModificarLicencia(LicenciaDTO l){

        if(getLicencia(l) == null){
            System.out.println("no existe un licencia");
        }
        else{
            App.entityManager.getTransaction().begin();
            App.entityManager.merge(l);
            App.entityManager.getTransaction().commit();
        }
    }

    public void EliminarLicencia(LicenciaDTO l){

        // deberia marcarlo como eliminado, no eliminarlo como tal

        if(getLicencia(l) == null){
            System.out.println("no se puede");
        }
        else{
            App.entityManager.getTransaction().begin();
            App.entityManager.merge(l);
            App.entityManager.getTransaction().commit();
        }
    }
}

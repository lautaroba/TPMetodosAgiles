package app.DAOs;

import app.App;
import app.DTOs.AdministradorDTO;
import app.Entidades.Administrador;

public class AdministradorDAO {
    
    Administrador administrador;
    
    public Administrador getAdministrador(AdministradorDTO admin){  
        return App.entityManager.find(Administrador.class, admin);
    }

    public void CrearUsuarioAdministrador(AdministradorDTO admin){

        if(getAdministrador(admin) == null){
            App.entityManager.getTransaction().begin();
            App.entityManager.merge(admin);
            App.entityManager.getTransaction().commit();
        }
        else
            System.out.println("ya existe un usuario");
        
    }

    public void ModificarUsuarioAdministrador(AdministradorDTO admin){

        if(getAdministrador(admin) == null){
            System.out.println("no existe un usuario");
        }
        else{
            App.entityManager.getTransaction().begin();
            App.entityManager.merge(admin);
            App.entityManager.getTransaction().commit();
        }
    }

    public void EliminarUsuarioAdministrador(AdministradorDTO admin){

        // deberia marcarlo como eliminado, no eliminarlo como tal

        if(getAdministrador(admin) == null){
            System.out.println("no se puede");
        }
        else{
            App.entityManager.getTransaction().begin();
            App.entityManager.merge(admin);
            App.entityManager.getTransaction().commit();
        }
    }
}

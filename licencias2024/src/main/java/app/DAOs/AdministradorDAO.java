package app.DAOs;

import app.App;
import app.Entidades.Administrador;

public class AdministradorDAO {
    
    public Administrador getAdministrador(Integer nro){
        try {
            return App.entityManager.find(Administrador.class, nro);
        } catch (Exception e) {
            throw e;
        }
    }

    public void CrearAdministrador(Administrador admin) {
        try {
            App.entityManager.getTransaction().begin();
            App.entityManager.persist(admin);
        } catch (Exception e) {
            throw e;
        } finally{
            App.entityManager.getTransaction().commit();
        }
    }

    public void ModificarAdministrador(Administrador admin) {
        try {
            App.entityManager.getTransaction().begin();
            App.entityManager.merge(admin);
        } catch (Exception e) {
            throw e;
        } finally{
            App.entityManager.getTransaction().commit();
        }
    }
}

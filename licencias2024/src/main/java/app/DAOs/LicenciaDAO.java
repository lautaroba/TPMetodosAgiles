package app.DAOs;

import app.App;
import app.Entidades.Licencia;
import app.Entidades.Titular;

public class LicenciaDAO {
    
    public Licencia getLicencia(Licencia licencia){
        try {
            return App.entityManager.find(Licencia.class, licencia);
        } catch (Exception e) {
            throw e;
        }
    }

    public void CrearLicencia(Licencia licencia){
        try {
            App.entityManager.getTransaction().begin();
            App.entityManager.persist(licencia);
        } catch (Exception e) {
            throw e;
        }finally{
            App.entityManager.getTransaction().commit();
        }
    }

    public void ModificarLicencia(Licencia licencia){
        try {
            App.entityManager.getTransaction().begin();
            App.entityManager.merge(licencia);
        } catch (Exception e) {
            throw e;
        }finally{
            App.entityManager.getTransaction().commit();
        }
    }

    public Licencia getPrimeraVez(Titular titular) {
        try {
            return App.entityManager.find(Licencia.class, titular);
        } catch (Exception e) {
            throw e;
        }
    }
}

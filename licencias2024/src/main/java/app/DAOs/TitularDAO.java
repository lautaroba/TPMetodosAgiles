package app.DAOs;

import app.App;
import app.Entidades.Titular;

public class TitularDAO {
    
    public Titular getTitular(Integer dni){ 
        try {
            return App.entityManager.find(Titular.class, dni);
        } catch (Exception e) {
            throw e;
        }
    }

    public void CrearTitular(Titular titular) {
        try {
            App.entityManager.getTransaction().begin();
            App.entityManager.persist(titular);
        } catch (Exception e) {
            throw e;
        } finally{
            App.entityManager.getTransaction().commit();
        }
    }

    public void ModificarTitular(Titular titular){
        try {
            App.entityManager.getTransaction().begin();
            App.entityManager.merge(titular);
        } catch (Exception e) {
            throw e;
        } finally{
            App.entityManager.getTransaction().commit();
        }
    }
}

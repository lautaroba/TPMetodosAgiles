package app.DAOs;

import app.Entidades.Titular;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class TitularDAO {

    private EntityManagerFactory entityManagerFactory;

    public TitularDAO(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public Titular getTitular(Integer dni) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager.find(Titular.class, dni);
        } catch (Exception e) {
            throw e;
        } finally {
            entityManager.close();
        }
    }

    public void CrearTitular(Titular titular) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(titular);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw e;
        } finally {
            entityManager.close();
        }
    }

    public void ModificarTitular(Titular titular) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(titular);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw e;
        } finally {
            entityManager.close();
        }
    }
}

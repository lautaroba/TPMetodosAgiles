package app.DAOs;

import app.Entidades.Administrador;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class AdministradorDAO {

    private EntityManagerFactory entityManagerFactory;

    public AdministradorDAO(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public Administrador getAdministrador(Integer nro) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager.find(Administrador.class, nro);
        } catch (Exception e) {
            throw e;
        } finally {
            entityManager.close();
        }
    }

    public void CrearAdministrador(Administrador admin) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(admin);
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

    public void ModificarAdministrador(Administrador admin) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(admin);
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

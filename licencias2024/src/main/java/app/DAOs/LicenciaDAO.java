package app.DAOs;

import java.time.LocalDate;
import java.util.List;

import app.DTOs.TitularDTO;
import app.Entidades.Licencia;
import app.Enumeradores.Clase;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class LicenciaDAO {
    
    private EntityManagerFactory entityManagerFactory;

    public LicenciaDAO(EntityManagerFactory entityManagerFactory){
        this.entityManagerFactory = entityManagerFactory;
    }

    public List<Licencia> getLicenciaByTitular(int nroDNI){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager.createQuery("FROM Licencia L WHERE L.titular.nroDNI = :dni", Licencia.class)
                                    .setParameter("dni", nroDNI)
                                    .getResultList();

        } catch (Exception e) {
            throw e;
        } finally {
            entityManager.close();
        }
    }
    
    public List<Licencia> getLicenciaByTitularYFecha(int dni, LocalDate fecha){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager.createQuery("SELECT L FROM Licencia L WHERE L.titular.nroDNI = :dni AND L.fechaDeExpiracion > :fecha", Licencia.class)
                                    .setParameter("dni", dni)
                                    .setParameter("fecha", fecha)
                                    .getResultList();
        } catch (Exception e) {
            throw e;
        } finally {
            entityManager.close();
        }
    }

    public List<Licencia> getLicenciasExpiradas(){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager.createQuery("SELECT L FROM Licencia L WHERE L.fechaDeExpiracion < :fecha", Licencia.class)
                                    .setParameter("fecha", LocalDate.now())
                                    .getResultList();
        } catch (Exception e) {
            throw e;
        } finally {
            entityManager.close();
        }
    }

    public List<Licencia> getLicenciasVigentes(){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager.createQuery("SELECT L FROM Licencia L WHERE L.fechaDeExpiracion > :fecha", Licencia.class)
                                    .setParameter("fecha", LocalDate.now())
                                    .getResultList();
        } catch (Exception e) {
            throw e;
        } finally {
            entityManager.close();
        }
    }
    
    public void CrearLicencia(Licencia licencia){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(licencia);
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

    public void ModificarLicencia(Licencia licencia){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(licencia);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw e;
        } finally{
            entityManager.close();
        }
    }

    public boolean isPrimeraVez(TitularDTO titular, Clase clase) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager.createQuery("SELECT L FROM Licencia L WHERE L.titular.nroDNI = :dni AND L.clase = :clase", Licencia.class)
                    .setParameter("dni", titular.nroDNI)
                    .setParameter("clase", clase)
                    .getResultList().isEmpty();
        } catch (Exception e) {
            throw e;
        } finally {
            entityManager.close();
        }
    }
}

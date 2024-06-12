package app.DAOs;

import java.time.LocalDate;
import java.util.List;

import app.App;
import app.DTOs.TitularDTO;
import app.Entidades.Licencia;

public class LicenciaDAO {
    
    public List<Licencia> getLicenciaByTitular(int nroDNI){
        try {
            return App.entityManager.createQuery("FROM Licencia L WHERE L.dni_titular = :dni", Licencia.class)
                                    .setParameter("dni", nroDNI)
                                    .getResultList();

        } catch (Exception e) {
            throw e;
        }
    }
    
    public List<Licencia> getLicenciaByTitularYFecha(int dni, LocalDate fecha){
        try {
            return App.entityManager.createQuery("SELECT L FROM Licencia L WHERE L.titular.nroDNI = :dni AND L.fechaDeExpiracion > :fecha", Licencia.class)
                                    .setParameter("dni", dni)
                                    .setParameter("fecha", fecha)
                                    .getResultList();
        } catch (Exception e) {
            throw e;
        }
    }

    public List<Licencia> getLicenciasExpiradas(){
        try {
            return App.entityManager.createQuery("SELECT L FROM Licencia L WHERE L.fechaDeExpiracion < :fecha", Licencia.class)
                                    .setParameter("fecha", LocalDate.now())
                                    .getResultList();
        } catch (Exception e) {
            throw e;
        }
    }

    public List<Licencia> getLicenciasVigentes(){
        try {
            return App.entityManager.createQuery("SELECT L FROM Licencia L WHERE L.fechaDeExpiracion > :fecha", Licencia.class)
                                    .setParameter("fecha", LocalDate.now())
                                    .getResultList();
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

    public Licencia getPrimeraVez(TitularDTO titular) {
        try {
            return App.entityManager.find(Licencia.class, titular.nroDNI);
        } catch (Exception e) {
            throw e;
        }
    }
}

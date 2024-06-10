package app.DAOs;

import app.App;
import app.DTOs.LicenciaDTO;
import app.DTOs.TitularDTO;
import app.Entidades.Licencia;

public class LicenciaDAO {
    
    public Licencia getLicencia(LicenciaDTO licencia){
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

    public Licencia getPrimeraVez(TitularDTO titular) {
        try {
            return App.entityManager.find(Licencia.class, titular.nroDNI);
        } catch (Exception e) {
            throw e;
        }
    }
}

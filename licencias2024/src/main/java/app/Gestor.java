package app;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import app.DAOs.*;
import app.DTOs.*;
import app.Entidades.*;
import app.Enumeradores.*;
import app.Excepciones.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Gestor {

    private static final int COSTO_TRAMITE_ADMINISTRATIVO = 8;
    private static final int[][] COSTOS_POR_CLASE_Y_EDAD = { { 40, 30, 25, 20 },
            { 40, 30, 25, 20 },
            { 47, 35, 30, 23 },
            { 59, 44, 39, 29 },
            { 40, 30, 25, 20 } };

    private EntityManagerFactory entityManagerFactory;
    public AdministradorDAO gestorAdministrador;
    public TitularDAO gestorTitular;
    public LicenciaDAO gestorLicencia;
    public AdministradorDTO administradorLogeado;

    public Gestor() {
        entityManagerFactory = Persistence.createEntityManagerFactory("licencias2024PU");
        gestorAdministrador = new AdministradorDAO(entityManagerFactory);
        gestorTitular = new TitularDAO(entityManagerFactory);
        gestorLicencia = new LicenciaDAO(entityManagerFactory);
    }

    public TitularDTO BuscarTitular(TitularDTO titular) {
        try {
            return new TitularDTO(gestorTitular.getTitular(titular.nroDNI));
        } catch (Exception e) {
            throw e;
        }

    }

    public void CrearTitular(TitularDTO titular) throws Exception {
        int edad = Period.between(titular.fechaDeNacimiento, LocalDate.now()).getYears();
        if (edad < 17)
            throw new MenorDeEdadException();
        else
            gestorTitular.CrearTitular(new Titular(titular));
    }

    public void ModificarTitular(TitularDTO titular) {
        try {
            gestorTitular.ModificarTitular(new Titular(titular));
        } catch (Exception e) {
            throw e;
        }
    }

    public AdministradorDTO BuscarAdministrador(AdministradorDTO administrador) {
        try {
            administradorLogeado = new AdministradorDTO(gestorAdministrador.getAdministrador(administrador.dni));
            return administradorLogeado;
        } catch (Exception e) {
            throw e;
        }
    }

    public void CrearAdministrador(AdministradorDTO administrador) throws Exception {
        int edad = Period.between(administrador.fechaDeNacimiento, LocalDate.now()).getYears();
        if (edad < 17)
            throw new MenorDeEdadException();
        else
            gestorAdministrador.CrearAdministrador(new Administrador(administrador));
    }

    public void ModificarAdministrador(AdministradorDTO administrador) {
        try {
            gestorAdministrador.ModificarAdministrador(new Administrador(administrador));
        } catch (Exception e) {
            throw e;
        }
    }

    public void CrearLicencia(LicenciaDTO licencia) throws Exception {
        try {
            verificarLicenciaEmitida(licencia);
            if (esProfesional(licencia)) {
                verificarLicenciaProfesional(licencia);
                desactivarLicenciasMenores(licencia);
            }
            gestorLicencia.CrearLicencia(new Licencia(gestorTitular.getTitular(licencia.titular.nroDNI),
                    gestorAdministrador.getAdministrador(licencia.administrativo.dni),
                    licencia.fechaDeEmision, licencia.fechaDeExpiracion, licencia.clase));
        } catch (Exception e) {
            throw e;
        }
    }

    public void ModificarLicencia(LicenciaDTO licencia) {
        try {
            gestorLicencia.ModificarLicencia(new Licencia(gestorTitular.getTitular(licencia.titular.nroDNI),
                    gestorAdministrador.getAdministrador(licencia.administrativo.dni),
                    licencia.fechaDeEmision, licencia.fechaDeExpiracion, licencia.clase));
        } catch (Exception e) {
            throw e;
        }
    }

    private boolean esProfesional(LicenciaDTO licencia) {
        if (licencia.clase == Clase.C || licencia.clase == Clase.D1 || licencia.clase == Clase.D2
                || licencia.clase == Clase.E)
            return true;
        else
            return false;
    }

    public void RenovarLicencia(LicenciaDTO licencia) throws Exception {
        // se puede renovar hasta cn 3 meses antes que caduque la nueva
        try {
            verificarLicenciaEmitida(licencia, LocalDate.now().plusMonths(3));
            desactivarLicenciasMenores(licencia);

            gestorLicencia.CrearLicencia(new Licencia(gestorTitular.getTitular(licencia.titular.nroDNI),
                    gestorAdministrador.getAdministrador(licencia.administrativo.dni),
                    licencia.fechaDeEmision, licencia.fechaDeExpiracion, licencia.clase));
        } catch (Exception e) {
            throw e;
        }
    }

    public void verificarLicenciaEmitida(LicenciaDTO licencia, LocalDate fechaLimite) throws Exception {
        for (Licencia l : gestorLicencia.getLicenciaByTitular(licencia.titular.nroDNI)) {
            if (l.getFechaDeExpiracion().isAfter(fechaLimite)) {
                if (l.getClase() == licencia.clase) {
                    throw new YaPoseeLicenciaException();
                } else if (licencia.clase == Clase.B) {
                    if (l.getClase() == Clase.C)
                        throw new YaPoseeLicenciaException();
                    else if (l.getClase() == Clase.D1)
                        throw new YaPoseeLicenciaException();
                    else if (l.getClase() == Clase.D2)
                        throw new YaPoseeLicenciaException();
                    else if (l.getClase() == Clase.E)
                        throw new YaPoseeLicenciaException();
                } else if (licencia.clase == Clase.C) {
                    if (l.getClase() == Clase.D2)
                        throw new YaPoseeLicenciaException();
                    else if (l.getClase() == Clase.E)
                        throw new YaPoseeLicenciaException();
                }
            }
        }
    }

    public void verificarLicenciaEmitida(LicenciaDTO licencia) throws Exception {
        for (Licencia l : gestorLicencia.getLicenciaByTitular(licencia.titular.nroDNI)) {
            if (l.getClase() == licencia.clase) {
                throw new YaPoseeLicenciaException();
            } else if (licencia.clase == Clase.B) {
                if (l.getClase() == Clase.C)
                    throw new YaPoseeLicenciaException();
                else if (l.getClase() == Clase.D1)
                    throw new YaPoseeLicenciaException();
                else if (l.getClase() == Clase.D2)
                    throw new YaPoseeLicenciaException();
                else if (l.getClase() == Clase.E)
                    throw new YaPoseeLicenciaException();
            } else if (licencia.clase == Clase.C) {
                if (l.getClase() == Clase.D2)
                    throw new YaPoseeLicenciaException();
                else if (l.getClase() == Clase.E)
                    throw new YaPoseeLicenciaException();
            }
        }
    }

    public void desactivarLicenciasMenores(LicenciaDTO licencia) {
        if (licencia.clase == Clase.C)
            desactivarLicencia(licencia.titular, Clase.B);
        else if (licencia.clase == Clase.D1)
            desactivarLicencia(licencia.titular, Clase.B);
        else if (licencia.clase == Clase.D2) {
            desactivarLicencia(licencia.titular, Clase.B);
            desactivarLicencia(licencia.titular, Clase.C);
            desactivarLicencia(licencia.titular, Clase.D1);
        } else if (licencia.clase == Clase.E) {
            desactivarLicencia(licencia.titular, Clase.B);
            desactivarLicencia(licencia.titular, Clase.C);
        }
    }

    public void verificarLicenciaProfesional(LicenciaDTO licencia) throws Exception {
        // Verifico edad
        int edad = Period.between(licencia.titular.fechaDeNacimiento, LocalDate.now()).getYears();
        if (edad < 21 || edad > 65)
            throw new MenorDeEdadProfesionalException();
        if (!licenciaBprevia(licencia))
            throw new NoObtuvoLicenciaBPreviaException();
    }

    public boolean licenciaBprevia(LicenciaDTO licencia) {
        for (Licencia l : gestorLicencia.getLicenciaByTitular(licencia.titular.nroDNI)) {
            if (l.getClase() == Clase.B) {
                if (Period.between(l.getFechaDeEmision(), LocalDate.now()).getYears() >= 1)
                    return true;
            }
        }
        return false;
    }

    // public void verificarLicenciaMayorActiva(LicenciaDTO licencia, LocalDate
    // fecha) throws Exception {
    // if (licencia.clase == Clase.B) {
    // checkLicenciaMayor(licencia, Clase.C, fecha);
    // checkLicenciaMayor(licencia, Clase.D1, fecha);
    // checkLicenciaMayor(licencia, Clase.D2, fecha);
    // checkLicenciaMayor(licencia, Clase.E, fecha);
    // } else if (licencia.clase == Clase.C) {
    // checkLicenciaMayor(licencia, Clase.D2, fecha);
    // checkLicenciaMayor(licencia, Clase.E, fecha);
    // } else if (licencia.clase == Clase.D1) {
    // checkLicenciaMayor(licencia, Clase.D2, fecha);
    // }
    // }

    public void checkLicenciaMayor(LicenciaDTO licencia, Clase clase, LocalDate fecha) throws Exception {
        for (Licencia l : gestorLicencia.getLicenciaByTitularYFecha(licencia.titular.nroDNI, fecha)) {
            if (l.getClase() == clase) {
                throw new LicenciaMayorActivaException();
            }
        }
    }

    public void desactivarLicencia(TitularDTO titular, Clase clase) {
        for (Licencia l : gestorLicencia.getLicenciaByTitularYFecha(titular.nroDNI, LocalDate.now())) {
            if (l.getClase() == clase) {
                l.setFechaDeExpiracion(LocalDate.now());
                gestorLicencia.ModificarLicencia(l);
            }
        }
    }

    public int CalcularCostoLicencia(TitularDTO titular, Clase clase) throws Exception {
        int edad = Period.between(titular.fechaDeNacimiento, LocalDate.now()).getYears();
        int costo = COSTO_TRAMITE_ADMINISTRATIVO;
        int categoria = clase.getNro();

        if (edad < 21) {
            if (gestorLicencia.isPrimeraVez(titular, clase))
                return costo += COSTOS_POR_CLASE_Y_EDAD[categoria][3];
            else
                return costo += COSTOS_POR_CLASE_Y_EDAD[categoria][2];
        } else if (edad <= 46) {
            return costo += COSTOS_POR_CLASE_Y_EDAD[categoria][0];
        } else if (edad <= 60) {
            return costo += COSTOS_POR_CLASE_Y_EDAD[categoria][1];
        } else if (edad <= 70) {
            return costo += COSTOS_POR_CLASE_Y_EDAD[categoria][2];
        } else {
            return costo += COSTOS_POR_CLASE_Y_EDAD[categoria][3];
        }
    }

    public int CalcularVigenciaLicencia(TitularDTO titular, Clase clase) {
        int edad = Period.between(titular.fechaDeNacimiento, LocalDate.now()).getYears();

        if (edad < 21) {
            if (gestorLicencia.isPrimeraVez(titular, clase))
                return 1;
            else
                return 3;
        } else if (edad <= 46) {
            return 5;
        } else if (edad <= 60) {
            return 4;
        } else if (edad <= 70) {
            return 3;
        } else {
            return 1;
        }
    }

    public List<LicenciaDTO> ListadoLicenciasExpiradas() {
        try {
            List<LicenciaDTO> lista = new ArrayList<>();
            for (Licencia l : gestorLicencia.getLicenciasExpiradas()) {
                lista.add(new LicenciaDTO(l));
            }
            return lista;
        } catch (Exception e) {
            throw e;
        }
    }

    public List<LicenciaDTO> ListadoLicenciasVigentes() {
        try {
            List<LicenciaDTO> lista = new ArrayList<>();
            for (Licencia l : gestorLicencia.getLicenciasVigentes()) {
                lista.add(new LicenciaDTO(l));
            }
            return lista;
        } catch (Exception e) {
            throw e;
        }
    }

    public List<LicenciaDTO> BuscarLicenciasTitular(TitularDTO titular, LocalDate fecha) {
        try {
            List<LicenciaDTO> lista = new ArrayList<>();
            for (Licencia l : gestorLicencia.getLicenciaByTitularYFecha(titular.nroDNI, fecha)) {
                lista.add(new LicenciaDTO(l));
            }
            return lista;
        } catch (Exception e) {
            throw e;
        }
    }

    // TEMPORAL, BORRAR!!
    public void dropDB() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createNativeQuery("DROP DATABASE licenciasdb").executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
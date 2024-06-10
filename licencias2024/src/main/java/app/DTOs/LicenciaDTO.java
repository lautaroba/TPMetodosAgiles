package app.DTOs;

import java.time.LocalDate;
import app.Entidades.Licencia;

public class LicenciaDTO {
    
    public TitularDTO titular;
    public AdministradorDTO administrativo;
    public LocalDate fechaDeEmision;
    public LocalDate fechaDeExpiracion;
    public boolean activa;

    public LicenciaDTO(TitularDTO titular, AdministradorDTO administrativo,
            LocalDate fechaDeEmision, LocalDate fechaDeExpiracion, boolean vigencia) {
        this.titular = titular;
        this.administrativo = administrativo;
        this.fechaDeEmision = fechaDeEmision;
        this.fechaDeExpiracion = fechaDeExpiracion;
        this.activa = vigencia;
    }

    public LicenciaDTO(Licencia licencia) {
        this.titular = new TitularDTO(licencia.getTitular());
        this.administrativo = new AdministradorDTO(licencia.getAdministrativo());
        this.fechaDeEmision = licencia.getFechaDeEmision();
        this.fechaDeExpiracion = licencia.getFechaDeExpiracion();
        this.activa = licencia.isActiva();
    }
    
}

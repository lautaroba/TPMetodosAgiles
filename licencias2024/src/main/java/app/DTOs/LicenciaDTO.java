package app.DTOs;

import java.time.LocalDate;
import app.Entidades.Licencia;

public class LicenciaDTO {
    
    public TitularDTO titular;
    public AdministradorDTO administrativo;
    public String limitacion;
    public LocalDate fechaDeEmision;
    public LocalDate fechaDeExpiracion;

    public LicenciaDTO(TitularDTO titular, AdministradorDTO administrativo, String limitacion,
            LocalDate fechaDeEmision, LocalDate fechaDeExpiracion) {
        this.titular = titular;
        this.administrativo = administrativo;
        this.limitacion = limitacion;
        this.fechaDeEmision = fechaDeEmision;
        this.fechaDeExpiracion = fechaDeExpiracion;
    }

    public LicenciaDTO(Licencia licencia) {
        this.titular = new TitularDTO(licencia.getTitular());
        this.administrativo = new AdministradorDTO(licencia.getAdministrativo());
        this.limitacion = licencia.getLimitacion();
        this.fechaDeEmision = licencia.getFechaDeEmision();
        this.fechaDeExpiracion = licencia.getFechaDeExpiracion();
}
    
}

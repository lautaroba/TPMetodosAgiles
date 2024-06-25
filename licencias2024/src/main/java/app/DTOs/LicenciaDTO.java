package app.DTOs;

import java.time.LocalDate;
import java.time.Period;

import app.Entidades.Licencia;
import app.Enumeradores.Clase;

public class LicenciaDTO {

    public TitularDTO titular;
    public AdministradorDTO administrativo;
    public LocalDate fechaDeEmision;
    public LocalDate fechaDeExpiracion;
    public Clase clase;

    public LicenciaDTO(TitularDTO titular, AdministradorDTO administrativo,
            LocalDate fechaDeEmision, LocalDate fechaDeExpiracion, Clase clase) {
        this.titular = titular;
        this.administrativo = administrativo;
        this.fechaDeEmision = fechaDeEmision;
        this.fechaDeExpiracion = fechaDeExpiracion;
        this.clase = clase;
    }

    public LicenciaDTO(Licencia licencia) {
        this.titular = new TitularDTO(licencia.getTitular());
        this.administrativo = new AdministradorDTO(licencia.getAdministrativo());
        this.fechaDeEmision = licencia.getFechaDeEmision();
        this.fechaDeExpiracion = licencia.getFechaDeExpiracion();
        this.clase = licencia.getClase();
    }

    public String calcularTiempoVigencia() {
        Period periodo;
        String result;
        // Supone que fechaEmision < fechaExpiracion por lo que result > 0
        periodo = Period.between(this.fechaDeEmision, this.fechaDeExpiracion);
        result = Integer.toString(periodo.getYears()) + " años " + Integer.toString(periodo.getMonths()) + " meses "
                + Integer.toString(periodo.getDays()) + " dias";

        return result;
    }

}

package app.DTOs;

import java.time.LocalDate;

import app.Entidades.Administrador;
import app.Entidades.Titular;

public class LicenciaDTO {
    
    public Titular titular;
    public Administrador administrativo;
    public String limitacion;
    public LocalDate fechaDeEmision;
    public LocalDate fechaDeExpiracion;
    public Character clase;
    public Character grupoSanguineo;
    public Character factorRH;
    public boolean donante;

    public LicenciaDTO(Titular titular, Administrador administrativo, String limitacion,
            LocalDate fechaDeEmision, LocalDate fechaDeExpiracion, Character clase, Character grupoSanguineo,
            Character factorRH, boolean donante) {
        this.titular = titular;
        this.administrativo = administrativo;
        this.limitacion = limitacion;
        this.fechaDeEmision = fechaDeEmision;
        this.fechaDeExpiracion = fechaDeExpiracion;
        this.clase = clase;
        this.grupoSanguineo = grupoSanguineo;
        this.factorRH = factorRH;
        this.donante = donante;
    }

    
}

package app.DTOs;

import java.time.LocalDate;

import app.Entidades.Administrador;
import app.Entidades.Titular;

public class LicenciaDTO {
    
    public int id;
    public Titular titular;
    public Administrador administrativo;
    public String limitacion;
    public LocalDate fechaDeEmision;
    public LocalDate fechaDeExpiracion;

    public LicenciaDTO(int id, Titular titular, Administrador administrativo, String limitacion, LocalDate fechaDeEmision,
            LocalDate fechaDeExpiracion) {
        this.id = id;
        this.titular = titular;
        this.administrativo = administrativo;
        this.limitacion = limitacion;
        this.fechaDeEmision = fechaDeEmision;
        this.fechaDeExpiracion = fechaDeExpiracion;
    }
}

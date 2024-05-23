package app.Entidades;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "Licencia")
public class Licencia {
    @Id
    @GeneratedValue
    private int id;
    private Titular titular;
    private Administrador administrativo;
    private String limitacion;
    private LocalDate fechaDeEmision;
    private LocalDate fechaDeExpiracion;

    public Licencia(int id, Titular titular, Administrador administrativo, String limitacion, LocalDate fechaDeEmision,
            LocalDate fechaDeExpiracion) {
        this.id = id;
        this.titular = titular;
        this.administrativo = administrativo;
        this.limitacion = limitacion;
        this.fechaDeEmision = fechaDeEmision;
        this.fechaDeExpiracion = fechaDeExpiracion;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Titular getTitular() {
        return titular;
    }
    public void setTitular(Titular titular) {
        this.titular = titular;
    }
    public Administrador getAdministrativo() {
        return administrativo;
    }
    public void setAdministrativo(Administrador administrativo) {
        this.administrativo = administrativo;
    }
    public String getLimitacion() {
        return limitacion;
    }
    public void setLimitacion(String limitacion) {
        this.limitacion = limitacion;
    }
    public LocalDate getFechaDeEmision() {
        return fechaDeEmision;
    }
    public void setFechaDeEmision(LocalDate fechaDeEmision) {
        this.fechaDeEmision = fechaDeEmision;
    }
    public LocalDate getFechaDeExpiracion() {
        return fechaDeExpiracion;
    }
    public void setFechaDeExpiracion(LocalDate fechaDeExpiracion) {
        this.fechaDeExpiracion = fechaDeExpiracion;
    }


    
}

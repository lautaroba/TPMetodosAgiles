package app.Entidades;

import java.time.LocalDate;

import app.DTOs.LicenciaDTO;
import jakarta.persistence.*;

@Entity
@Table(name = "Licencia")
public class Licencia {
    @Id
    @GeneratedValue
    private int id;

    @OneToOne
    @JoinColumn(name = "dni_titular")
    private Titular titular;

    @OneToOne
    @JoinColumn(name = "dni_administrador")
    private Administrador administrativo;

    @Column(name = "limitacion")
    private String limitacion;

    @Column(name = "fechaDeEmision", nullable = false)
    private LocalDate fechaDeEmision;

    @Column(name = "fechaDeExpiracion", nullable = false)
    private LocalDate fechaDeExpiracion;

    public Licencia() {

    }

    public Licencia(Titular titular, Administrador administrativo, String limitacion, LocalDate fechaDeEmision,
    LocalDate fechaDeExpiracion) {
        this.titular = titular;
        this.administrativo = administrativo;
        this.limitacion = limitacion;
        this.fechaDeEmision = fechaDeEmision;
        this.fechaDeExpiracion = fechaDeExpiracion;
    }

    public Licencia(LicenciaDTO licencia) {
        this.titular = new Titular(licencia.titular);
        this.administrativo = new Administrador(licencia.administrativo);
        this.limitacion = licencia.limitacion;
        this.fechaDeEmision = licencia.fechaDeEmision;
        this.fechaDeExpiracion = licencia.fechaDeExpiracion;
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

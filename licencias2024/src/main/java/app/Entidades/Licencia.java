package app.Entidades;

import java.time.LocalDate;

import app.DTOs.LicenciaDTO;
import jakarta.persistence.*;

@Entity
@Table(name = "Licencia")
public class Licencia {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_licencia")
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dni_titular", referencedColumnName = "dni_titular")
    private Titular titular;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dni_administrador", referencedColumnName = "dni_administrador")
    private Administrador administrativo;

    @Column(name = "fechaDeEmision", nullable = false)
    private LocalDate fechaDeEmision;

    @Column(name = "fechaDeExpiracion", nullable = false)
    private LocalDate fechaDeExpiracion;

    @Column(name = "activa")
    private boolean activa;

    public Licencia() {
        
    }

    public Licencia(Titular titular, Administrador administrativo, LocalDate fechaDeEmision,
    LocalDate fechaDeExpiracion, boolean activa) {
        this.titular = titular;
        this.administrativo = administrativo;
        this.fechaDeEmision = fechaDeEmision;
        this.fechaDeExpiracion = fechaDeExpiracion;
        this.activa = activa;
    }

    public Licencia(LicenciaDTO licencia) {
        this.titular = new Titular(licencia.titular);
        this.administrativo = new Administrador(licencia.administrativo);
        this.fechaDeEmision = licencia.fechaDeEmision;
        this.fechaDeExpiracion = licencia.fechaDeExpiracion;
        this.activa = licencia.activa;
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
    public boolean isActiva() {
        return activa;
    }
    public void setActiva(boolean activa) {
        this.activa = activa;
    }
    
}

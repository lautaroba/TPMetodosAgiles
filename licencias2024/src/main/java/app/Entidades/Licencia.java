package app.Entidades;

import java.time.LocalDate;

import app.Enumeradores.Clase;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Licencia")
public class Licencia {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_licencia")
    private int id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "dni_titular", referencedColumnName = "dni_titular")
    private Titular titular;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "dni_administrador", referencedColumnName = "dni_administrador")
    private Administrador administrativo;

    @Column(name = "fechaDeEmision", nullable = false)
    private LocalDate fechaDeEmision;

    @Column(name = "fechaDeExpiracion", nullable = false)
    private LocalDate fechaDeExpiracion;

    @Column(name = "clase", nullable = false)
    private Clase clase;

    public Licencia() {
        
    }

    public Licencia(Titular titular, Administrador administrativo, LocalDate fechaDeEmision,
    LocalDate fechaDeExpiracion, Clase clase) {
        this.titular = titular;
        this.administrativo = administrativo;
        this.fechaDeEmision = fechaDeEmision;
        this.fechaDeExpiracion = fechaDeExpiracion;
        this.clase = clase;
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
    public Clase getClase() {
        return clase;
    }
    public void setClase(Clase clase) {
        this.clase = clase;
    }
    
}

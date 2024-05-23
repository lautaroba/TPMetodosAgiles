package app.Entidades;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "Titular")
public class Titular {
    @Id
    @Column(name = "dni", unique = true, nullable = false)
    private int dni;
    @Column(name = "nombre", unique = true, nullable = false)
    private String nombre;
    @Column(name = "apellido", unique = true, nullable = false)
    private String apellido;
    @Column(name = "fechaDeNacimiento", unique = true, nullable = false)
    private LocalDate fechaDeNacimiento;
    @Column(name = "direccion", unique = true, nullable = false)
    private String direccion;
    @Column(name = "clase", unique = true, nullable = false)
    private Character clase;
    @Column(name = "grupoSanguineo", unique = true, nullable = false)
    private Character grupoSanguineo;
    @Column(name = "factorRH", unique = true, nullable = false)
    private Character factorRH;
    @Column(name = "donante", unique = true, nullable = false)
    private boolean donante;

    public Titular(int dni, String nombre, String apellido, LocalDate fechaDeNacimiento, String direccion,
            Character clase, Character grupoSanguineo, Character factorRH, boolean donante) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaDeNacimiento = fechaDeNacimiento;
        this.direccion = direccion;
        this.clase = clase;
        this.grupoSanguineo = grupoSanguineo;
        this.factorRH = factorRH;
        this.donante = donante;
    }

    public int getDni() {
        return dni;
    }
    public void setDni(int dni) {
        this.dni = dni;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public LocalDate getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }
    public void setFechaDeNacimiento(LocalDate fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }
    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public Character getClase() {
        return clase;
    }
    public void setClase(Character clase) {
        this.clase = clase;
    }
    public Character getGrupoSanguineo() {
        return grupoSanguineo;
    }
    public void setGrupoSanguineo(Character grupoSanguineo) {
        this.grupoSanguineo = grupoSanguineo;
    }
    public Character getFactorRH() {
        return factorRH;
    }
    public void setFactorRH(Character factorRH) {
        this.factorRH = factorRH;
    }
    public boolean isDonante() {
        return donante;
    }
    public void setDonante(boolean donante) {
        this.donante = donante;
    }

    
}

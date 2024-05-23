package app.Entidades;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
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

    public Titular(int dni, String nombre, String apellido, LocalDate fechaDeNacimiento, String direccion) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaDeNacimiento = fechaDeNacimiento;
        this.direccion = direccion;
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

    
}

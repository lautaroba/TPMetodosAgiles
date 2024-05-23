package app.Entidades;

import java.time.LocalDate;
import jakarta.persistence.*;

@Entity
@Table(name = "Administrador")
public class Administrador {
    
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
    @Column(name = "email", unique = true, nullable = false)
    private String email;
    @Column(name = "contraseña", unique = true, nullable = false)
    private String contraseña;

    public Administrador(int dni, String nombre, String apellido, LocalDate fechaDeNacimiento, String direccion,
            String email, String contraseña) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaDeNacimiento = fechaDeNacimiento;
        this.direccion = direccion;
        this.email = email;
        this.contraseña = contraseña;
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
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getContraseña() {
        return contraseña;
    }
    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    

}

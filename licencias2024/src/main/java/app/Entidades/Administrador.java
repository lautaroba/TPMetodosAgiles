package app.Entidades;

import java.time.LocalDate;

import app.DTOs.AdministradorDTO;
import app.Enumeradores.Sexo;
import app.Enumeradores.TipoDocumento;
import jakarta.persistence.*;

@Entity
@Table(name = "Administrador")
public class Administrador {
    
    @Id
    @Column(name = "dni_administrador", nullable = false)
    private int dni;
    
    @Column(name = "tipoDocumento", nullable = false)
    private TipoDocumento tipoDocumento;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "apellido", nullable = false)
    private String apellido;

    @Column(name = "fechaDeNacimiento", nullable = false)
    private LocalDate fechaDeNacimiento;

    @Column(name = "direccion", nullable = false)
    private String direccion;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "contraseña", nullable = false)
    private String contraseña;
    
    @Column(name = "sexo", nullable = false)
    private Sexo sexo;

    public Administrador() {
        
    }

    public Administrador(int dni, String nombre, String apellido, LocalDate fechaDeNacimiento, String direccion,
            String email, String contraseña, TipoDocumento tipoDocumento, Sexo sexo) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaDeNacimiento = fechaDeNacimiento;
        this.direccion = direccion;
        this.email = email;
        this.contraseña = contraseña;
        this.sexo = sexo;
        this.tipoDocumento = tipoDocumento;
    }

    public Administrador(AdministradorDTO administrador) {
        this.dni = administrador.dni;
        this.nombre = administrador.nombre;
        this.apellido = administrador.apellido;
        this.fechaDeNacimiento = administrador.fechaDeNacimiento;
        this.direccion = administrador.direccion;
        this.email = administrador.email;
        this.contraseña = administrador.contraseña;
        this.sexo = administrador.sexo;
        this.tipoDocumento = administrador.tipoDocumento;
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
    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }
    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }
    public Sexo getSexo() {
        return sexo;
    }
    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

}

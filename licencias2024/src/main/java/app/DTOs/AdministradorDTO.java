package app.DTOs;

import java.time.LocalDate;

import app.Entidades.Administrador;
import app.Enumeradores.Sexo;
import app.Enumeradores.TipoDocumento;

public class AdministradorDTO {
    
    public int dni;
    public String nombre;
    public String apellido;
    public LocalDate fechaDeNacimiento;
    public String direccion;
    public String email;
    public String contraseña;
    public TipoDocumento tipoDocumento;
    public Sexo sexo;

    public AdministradorDTO(int dni, String nombre, String apellido, LocalDate fechaDeNacimiento, String direccion,
            String email, String contraseña, TipoDocumento tipoDocumento, Sexo sexo) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaDeNacimiento = fechaDeNacimiento;
        this.direccion = direccion;
        this.email = email;
        this.contraseña = contraseña;
        this.tipoDocumento = tipoDocumento;
        this.sexo = sexo;
    }

    public AdministradorDTO(Administrador administrador) {
        this.dni = administrador.getDni();
        this.nombre = administrador.getNombre();
        this.apellido = administrador.getApellido();
        this.fechaDeNacimiento = administrador.getFechaDeNacimiento();
        this.direccion = administrador.getDireccion();
        this.email = administrador.getEmail();
        this.contraseña = administrador.getContraseña();
        this.tipoDocumento = administrador.getTipoDocumento();
        this.sexo = administrador.getSexo();
    }

    public AdministradorDTO(int dni){
        this.dni = dni;
    }

    public AdministradorDTO(){}
    
}

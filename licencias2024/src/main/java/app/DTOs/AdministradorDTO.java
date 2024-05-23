package app.DTOs;

import java.time.LocalDate;

public class AdministradorDTO {
    
    public int dni;
    public String nombre;
    public String apellido;
    public LocalDate fechaDeNacimiento;
    public String direccion;
    public String email;
    public String contraseña;

    public AdministradorDTO(int dni, String nombre, String apellido, LocalDate fechaDeNacimiento, String direccion,
            String email, String contraseña) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaDeNacimiento = fechaDeNacimiento;
        this.direccion = direccion;
        this.email = email;
        this.contraseña = contraseña;
    }
}

package app.DTOs;

import java.time.LocalDate;

public class TitularDTO {
    public int dni;
    public String nombre;
    public String apellido;
    public LocalDate fechaDeNacimiento;
    public String direccion;

    public TitularDTO(int dni, String nombre, String apellido, LocalDate fechaDeNacimiento, String direccion) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaDeNacimiento = fechaDeNacimiento;
        this.direccion = direccion;
    }
}

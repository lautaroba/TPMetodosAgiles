package app.DTOs;

import java.time.LocalDate;

public class TitularDTO {
    public int dni;
    public String nombre;
    public String apellido;
    public LocalDate fechaDeNacimiento;
    public String direccion;
    public Character clase;
    public Character grupoSanguineo;
    public Character factorRH;
    public boolean donante;

    public TitularDTO(int dni, String nombre, String apellido, LocalDate fechaDeNacimiento, String direccion,
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
}

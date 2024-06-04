package app.Entidades;

import java.time.LocalDate;

import app.DTOs.TitularDTO;
import app.Enunumenadores.Clase;
import app.Enunumenadores.FactorRH;
import app.Enunumenadores.GrupoSanguineo;
import app.Enunumenadores.TipoDocumento;
import jakarta.persistence.*;

@Entity
public class Titular {
    @Id
    @Column(name = "nroDNI")
    private int nroDNI;

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
    
    @Column(name = "clase", nullable = false)
    private Clase clase;

    @Column(name = "grupoSanguineo", nullable = false)
    private GrupoSanguineo grupoSanguineo;

    @Column(name = "factorRH", nullable = false)
    private FactorRH factorRH;

    @Column(name = "donante", nullable = false)
    private boolean donante;

    public Titular() {
    }

    public Titular(int nroDNI, TipoDocumento tipoDocumento, String nombre, String apellido, LocalDate fechaDeNacimiento,String direccion, Clase clase, GrupoSanguineo grupoSanguineo, FactorRH factorRH, boolean donante) {
        this.nroDNI = nroDNI;
        this.tipoDocumento = tipoDocumento;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaDeNacimiento = fechaDeNacimiento;
        this.direccion = direccion;
        this.clase = clase;
        this.grupoSanguineo = grupoSanguineo;
        this.factorRH = factorRH;
        this.donante = donante;
    }

    public Titular(TitularDTO titular) {
        this.nroDNI = titular.nroDNI;
        this.tipoDocumento = titular.tipoDocumento;
        this.nombre = titular.nombre;
        this.apellido = titular.apellido;
        this.fechaDeNacimiento = titular.fechaDeNacimiento;
        this.direccion = titular.direccion;
        this.clase = titular.clase;
        this.grupoSanguineo = titular.grupoSanguineo;
        this.factorRH = titular.factorRH;
        this.donante = titular.donante;
    }
    public int getDni() {
        return nroDNI;
    }

    public void setDni(int dni) {
        this.nroDNI = dni;
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

    public Clase getClase() {
        return clase;
    }

    public void setClase(Clase clase) {
        this.clase = clase;
    }

    public GrupoSanguineo getGrupoSanguineo() {
        return grupoSanguineo;
    }

    public void setGrupoSanguineo(GrupoSanguineo grupoSanguineo) {
        this.grupoSanguineo = grupoSanguineo;
    }

    public FactorRH getFactorRH() {
        return factorRH;
    }

    public void setFactorRH(FactorRH factorRH) {
        this.factorRH = factorRH;
    }

    public boolean isDonante() {
        return donante;
    }

    public void setDonante(boolean donante) {
        this.donante = donante;
    }

}

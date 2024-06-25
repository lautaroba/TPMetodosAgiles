package app.Entidades;

import java.time.LocalDate;

import app.DTOs.TitularDTO;
import app.Enumeradores.FactorRH;
import app.Enumeradores.GrupoSanguineo;
import app.Enumeradores.TipoDocumento;
import jakarta.persistence.*;

@Entity
public class Titular {
    @Id
    @Column(name = "dni_titular")
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

    @Column(name = "grupoSanguineo", nullable = false)
    private GrupoSanguineo grupoSanguineo;

    @Column(name = "factorRH", nullable = false)
    private FactorRH factorRH;

    @Column(name = "donante", nullable = false)
    private boolean donante;

    @Column(name = "limitacion")
    private String limitacion;

    public Titular() {
    }

    public Titular(int nroDNI, TipoDocumento tipoDocumento, String nombre, String apellido, LocalDate fechaDeNacimiento,
            String direccion, GrupoSanguineo grupoSanguineo, FactorRH factorRH, boolean donante, String limitacion) {
        this.nroDNI = nroDNI;
        this.tipoDocumento = tipoDocumento;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaDeNacimiento = fechaDeNacimiento;
        this.direccion = direccion;
        this.grupoSanguineo = grupoSanguineo;
        this.factorRH = factorRH;
        this.donante = donante;
        this.limitacion = limitacion;
    }

    public Titular(TitularDTO titular) {
        this.nroDNI = titular.nroDNI;
        this.tipoDocumento = titular.tipoDocumento;
        this.nombre = titular.nombre;
        this.apellido = titular.apellido;
        this.fechaDeNacimiento = titular.fechaDeNacimiento;
        this.direccion = titular.direccion;
        this.grupoSanguineo = titular.grupoSanguineo;
        this.factorRH = titular.factorRH;
        this.donante = titular.donante;
        this.limitacion = titular.limitacion;
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

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getLimitacion() {
        return limitacion;
    }

    public void setLimitacion(String limitacion) {
        this.limitacion = limitacion;
    }

}

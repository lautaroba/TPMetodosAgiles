package app.DTOs;

import java.time.LocalDate;

import app.Entidades.Titular;
import app.Enumeradores.FactorRH;
import app.Enumeradores.GrupoSanguineo;
import app.Enumeradores.TipoDocumento;

public class TitularDTO {

    public TipoDocumento tipoDocumento;
    public int nroDNI;
    public String nombre;
    public String apellido;
    public LocalDate fechaDeNacimiento;
    public String direccion;
    public GrupoSanguineo grupoSanguineo;
    public FactorRH factorRH;
    public boolean donante;
    public String limitacion;

    public TitularDTO(TipoDocumento tipoDocumento, int nroDni, String nombre, String apellido,
            LocalDate fechaDeNacimiento, String direccion, GrupoSanguineo grupoSanguineo,
            FactorRH factorRH, boolean donante, String limitacion) {
        this.tipoDocumento = tipoDocumento;
        this.nroDNI = nroDni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaDeNacimiento = fechaDeNacimiento;
        this.direccion = direccion;
        this.grupoSanguineo = grupoSanguineo;
        this.factorRH = factorRH;
        this.donante = donante;
        this.limitacion = limitacion;
    }

    public TitularDTO(Titular titular) {
        this.tipoDocumento = titular.getTipoDocumento();
        this.nroDNI = titular.getDni();
        this.nombre = titular.getNombre();
        this.apellido = titular.getApellido();
        this.fechaDeNacimiento = titular.getFechaDeNacimiento();
        this.direccion = titular.getDireccion();
        this.grupoSanguineo = titular.getGrupoSanguineo();
        this.factorRH = titular.getFactorRH();
        this.donante = titular.isDonante();
        this.limitacion = titular.getLimitacion();
    }

    public TitularDTO(int nroDni) {
        this.nroDNI = nroDni;
    }

}

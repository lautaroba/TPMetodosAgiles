package app.DTOs;

import java.time.LocalDate;

import app.Entidades.Titular;
import app.Enumeradores.Clase;
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
    public Clase clase;
    public GrupoSanguineo grupoSanguineo;
    public FactorRH factorRH;
    public boolean donante;
    public String limitacion;

    public TitularDTO(TipoDocumento tipoDocumento, int nroDni, String nombre, String apellido, LocalDate fechaDeNacimiento, String direccion, Clase clase, GrupoSanguineo grupoSanguineo,
    FactorRH factorRH, boolean donante, String limitacion) {
        this.tipoDocumento = tipoDocumento;
        this.nroDNI = nroDni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaDeNacimiento = fechaDeNacimiento;
        this.direccion = direccion;
        this.clase = clase;
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
        this.clase = titular.getClase();
        this.grupoSanguineo = titular.getGrupoSanguineo();
        this.factorRH = titular.getFactorRH();
        this.donante = titular.isDonante();
        this.limitacion = titular.getLimitacion();
    }
    public TitularDTO(int dni) {
        this.nroDNI = dni;
    }
}

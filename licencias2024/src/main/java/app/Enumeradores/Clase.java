package app.Enumeradores;

public enum Clase {
    A("Ciclomotores, motocicletas y triciclos motorizados.", 0),
    B("Automóviles y camionetas con acoplado.", 1),
    C("Automóviles, camionetas con acoplado y camiones sin acoplado.", 1),
    D1("Servicio de transporte de pasajeros, emergencia, seguridad, automóviles y camionetas con acoplado. ", 0),
    D2("Servicio de transporte de pasajeros, emergencia, seguridad, Automóviles, camionetas con acoplado y camiones sin acoplado.", 0),
    E("Camiones articulados o con acoplado, maquinaria especial no agrícola, automóviles, camionetas con acoplado y camiones sin acoplado.", 2),
    F("Automotores especialmente adaptados para discapacitados.", 0),
    G("Tractores agrícolas y maquinaria especial agrícola.", 3);

    private String nombreClase;
	private int nro;
	
	private Clase (String nombreClase, int nro){
		this.nombreClase = nombreClase;
		this.nro = nro;
	}

    public String getNombreClase() {
        return nombreClase;
    }

    public int getNro() {
        return nro;
    }
    
}
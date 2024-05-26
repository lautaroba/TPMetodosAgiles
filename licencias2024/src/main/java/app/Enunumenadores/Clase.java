package app.Enunumenadores;

public enum Clase {
    A("CLASE A", 0),
    B("CLASE B", 1),
    C("CLASE C", 1),
    D("CLASE D", 0),
    E("CLASE E", 2),
    F("CLASE F", 0),
    G("CLASE G", 3);

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

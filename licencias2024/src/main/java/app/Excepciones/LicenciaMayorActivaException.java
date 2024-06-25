package app.Excepciones;

public class LicenciaMayorActivaException extends Exception {

    public LicenciaMayorActivaException() {
        super("Error: El titular posee una licencia de mayor rango activa");
    }
}

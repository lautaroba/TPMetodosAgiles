package app.Excepciones;

public class YaPoseeLicenciaException extends Exception {

    public YaPoseeLicenciaException() {
        super("Error: El titular ya obtuvo una licencia con la clase especificada");
    }
}

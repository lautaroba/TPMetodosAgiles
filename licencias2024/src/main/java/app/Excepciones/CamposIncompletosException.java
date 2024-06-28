package app.Excepciones;

public class CamposIncompletosException extends Exception {

    public CamposIncompletosException() {
        super("Error: Uno o mas campos estan vacios");
    }
}

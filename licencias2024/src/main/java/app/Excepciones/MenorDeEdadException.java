package app.Excepciones;

public class MenorDeEdadException extends Exception {

    public MenorDeEdadException() {
        super("Error: El titular no tiene la edad adecuada para obtener una licencia");
    }
}

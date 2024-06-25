package app.Excepciones;

public class MenorDeEdadProfesionalException extends Exception {

    public MenorDeEdadProfesionalException() {
        super("Error: El titular no tiene la edad adecuada para obtener una licencia");
    }
}

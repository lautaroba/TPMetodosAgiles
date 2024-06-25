package app.Excepciones;

public class NoObtuvoLicenciaBPreviaException extends Exception {

    public NoObtuvoLicenciaBPreviaException() {
        super("Error: El titular no obtuvo licencia B previa a su licencia profesional.");
    }
}

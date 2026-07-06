package sistema.reserva.clases.excepciones;

public class CorreoYaRegistradoException extends Exception {
    public CorreoYaRegistradoException(String mensaje) {
        super(mensaje);
    }
}

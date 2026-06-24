package sistema.reserva.clases.excepciones;

public class MatriculaDuplicadaException extends Exception {
    public MatriculaDuplicadaException(String mensaje) {
        super(mensaje);
    }
}
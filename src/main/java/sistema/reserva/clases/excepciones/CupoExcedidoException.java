package sistema.reserva.clases.excepciones;

public class CupoExcedidoException extends Exception {
    public CupoExcedidoException(String mensaje) {
        super(mensaje);
    }
}
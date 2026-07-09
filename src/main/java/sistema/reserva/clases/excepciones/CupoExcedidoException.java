package sistema.reserva.clases.excepciones;

/**
 * Excepción que se lanza cuando se intenta agendar una reserva en un bloque
 * horario donde el tutor ya ha alcanzado su límite máximo de alumnos permitidos.
 */
public class CupoExcedidoException extends Exception {
    public CupoExcedidoException(String mensaje) {
        super(mensaje);
    }
}
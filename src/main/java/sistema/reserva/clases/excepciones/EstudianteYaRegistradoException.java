package sistema.reserva.clases.excepciones;

/**
 * Excepción que se lanza cuando un estudiante intenta agendar una nueva clase
 * en una fecha y horario en los que ya tiene una reserva previa activa.
 */
public class EstudianteYaRegistradoException extends Exception {
    public EstudianteYaRegistradoException(String mensaje) {
        super(mensaje);
    }
}
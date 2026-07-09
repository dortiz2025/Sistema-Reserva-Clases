package sistema.reserva.clases.excepciones;

/**
 * Excepción que se lanza cuando un tutor ya tiene programada una clase
 * de una materia diferente en el mismo bloque horario.
 */
public class ConflictoMateriaException extends Exception {
    public ConflictoMateriaException(String mensaje) {
        super(mensaje);
    }
}

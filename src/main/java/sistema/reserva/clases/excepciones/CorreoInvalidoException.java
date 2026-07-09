package sistema.reserva.clases.excepciones;

/**
 * Excepción que se lanza cuando el formato de un correo electrónico proporcionado
 * no es válido (No tiene @ o está vacío).
 */
public class CorreoInvalidoException extends Exception {
  public CorreoInvalidoException(String mensaje) {
    super(mensaje);
  }
}

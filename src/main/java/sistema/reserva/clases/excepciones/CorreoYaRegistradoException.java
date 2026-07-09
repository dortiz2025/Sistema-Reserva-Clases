package sistema.reserva.clases.excepciones;

/**
 * Excepción que se lanza al intentar registrar o modificar un perfil
 * utilizando un correo electrónico que ya se encuentra asociado a otro usuario en el sistema.
 */
public class CorreoYaRegistradoException extends Exception {
    public CorreoYaRegistradoException(String mensaje) {
        super(mensaje);
    }
}

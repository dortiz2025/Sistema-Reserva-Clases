package sistema.reserva.clases.logica;

public class Administrador {
import sistema.reserva.clases.excepciones.CorreoInvalidoException;
import sistema.reserva.clases.logica.reserva.GestorReserva;
import sistema.reserva.clases.logica.reserva.Reserva;
import java.util.UUID;
import java.util.List;
import java.util.ArrayList;

/**
 * Clase que representa a un Administrador del sistema.
 * Tiene la capacidad de supervisar reservas y gestionar usuarios.
 */
public class Administrador extends Perfil {
    private final String idAdministrador;

    /**
     * Inicializa un administrador con sus datos básicos.
     * @param nombre Nombre del administrador.
     * @param email Correo electrónico.
     */
    public Administrador(String nombre, String email) throws CorreoInvalidoException {
        super(nombre, email);
        this.idAdministrador = "ADM-" + UUID.randomUUID().toString().substring(0, 4).toUpperCase();
    }
}

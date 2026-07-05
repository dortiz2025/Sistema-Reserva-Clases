package sistema.reserva.clases.logica;

import sistema.reserva.clases.excepciones.CorreoInvalidoException;
import sistema.reserva.clases.logica.estrategias.FiltrarStrategy;
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

    public String getIdAdministrador() {
        return this.idAdministrador;
    }

    /**
    * Permite al administrador filtrar reservas de todo el sistema usando el patrón Strategy que ya creaste.
    * @param gestorReservas El gestor que contiene los datos.
    * @param criterio El algoritmo de filtrado (Strategy).
    * @return Lista de reservas que cumplen la condición.
    */
    public List<Reserva> filtrarTodasLasReservas(GestorReservas gestorReservas, FiltrarStrategy<Reserva> criterio) {
        List<Reserva> resultado = new ArrayList<>();
        for (Reserva r : gestorReservas.obtenerTodasLasReservas()) {
            if (criterio.cumpleCondicion(r)) {
                resultado.add(r);
            }
        }
        return resultado;
    }
    @Override
    public String toString() {
        return "Administrador{" +
                "nombre='" + getNombre() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", idAdministrador='" + this.idAdministrador + '\'' +
                '}';
    }
}

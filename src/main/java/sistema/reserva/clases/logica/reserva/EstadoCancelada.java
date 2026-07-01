package sistema.reserva.clases.logica.reserva;

import sistema.reserva.clases.logica.BloqueHorario;
import sistema.reserva.clases.logica.Tutor;

/**
 * Clase que representa el estado de una reserva cancelada.
 * Esta no puede ser modificada, ni completada, ni cancelada otra vez.
 */
public class EstadoCancelada implements EstadoReserva {

    /**
     * Intenta modificar la reserva.
     * En este caso se lanza excepción porque
     * la reserva ya fue cancelada.
     * @param reserva Referencia de la reserva.
     * @param nuevoTutor Referencia del tutor nuevo.
     * @param nuevaMateria Nueva materia.
     * @param nuevoHorario Nuevo horario.
     */
    @Override
    public void modificarReserva(Reserva reserva, Tutor nuevoTutor, String nuevaMateria, BloqueHorario nuevoHorario) {
        throw new IllegalStateException("No se puede modificar una reserva que ya ha sido cancelada.");
    }

    /**
     * Se intenta cancelar la reserva.
     * En este caso se lanza excepción porque
     * la reserva ya fue cancelada.
     * @param reserva Referencia de la reserva.
     */
    @Override
    public void cancelarReserva(Reserva reserva) {
        throw new IllegalStateException("La reserva ya se encuentra cancelada, no es necesario cancelarla de nuevo.");
    }

    /**
     * Se intenta completar la reserva.
     * En este caso se lanza excepción porque
     * la reserva ya fue cancelada.
     * @param reserva Referencia de la reserva.
     */
    @Override
    public void completarReserva(Reserva reserva) {
        throw new IllegalStateException("La reserva se ha cancelado, no puede completarse.");
    }

    /**
     * Devuelve el nombre de este estado.
     * @return String con el nombre de este estado.
     */
    @Override
    public String getNombreEstado() {
        return "Cancelada";
    }
}
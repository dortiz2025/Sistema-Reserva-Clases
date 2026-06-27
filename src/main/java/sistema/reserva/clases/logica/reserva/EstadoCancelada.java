package sistema.reserva.clases.logica.reserva;

import sistema.reserva.clases.logica.Tutor;

/**
 * Clase que representa el estado de una reserva cancelada.
 * Esta no puede ser modificada, ni completada, ni cancelada otra vez.
 */
public class EstadoCancelada implements EstadoReserva {

    @Override
    public void modificar(Reserva reserva, Tutor nuevoTutor, String nuevaMateria, String nuevoHorario) {
        throw new IllegalStateException("No se puede modificar una reserva que ya ha sido cancelada.");
    }

    @Override
    public void cancelar(Reserva reserva) {
        throw new IllegalStateException("La reserva ya se encuentra cancelada, no es necesario cancelarla de nuevo.");
    }

    @Override
    public void completar(Reserva reserva) {
        throw new IllegalStateException("La reserva se ha cancelado, no puede completarse.");
    }

    @Override
    public String getNombreEstado() {
        return "Cancelada";
    }
}
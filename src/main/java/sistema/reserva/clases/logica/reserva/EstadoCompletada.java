package sistema.reserva.clases.logica.reserva;

import sistema.reserva.clases.logica.Tutor;

/**
 * Clase que representa el estado de una reserva que ha sido completada.
 * Esta no puede ser modificada, ni cancelada, ni completada otra vez.
 */
public class EstadoCompletada implements EstadoReserva {

    @Override
    public void modificar(Reserva reserva, Tutor nuevoTutor, String nuevaMateria, String nuevoHorario) {
        throw new IllegalStateException("La clase ya se realizó, la reserva no puede ser modificada.");
    }

    @Override
    public void cancelar(Reserva reserva) {
        throw new IllegalStateException("La clase ya se realizó, no puede ser cancelada.");
    }

    @Override
    public void completar(Reserva reserva) {
        throw new IllegalStateException("La clase ya se realizó, no es necesario completarla de nuevo.");
    }

    @Override
    public String getNombreEstado() {
        return "Completada";
    }
}
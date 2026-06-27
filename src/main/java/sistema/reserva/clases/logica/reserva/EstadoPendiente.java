package sistema.reserva.clases.logica.reserva;

import sistema.reserva.clases.logica.Tutor;

/**
 * Clase que representa el estado de una reserva que no se ha completado.
 * La reserva en este estado puede ser modificada,
 * cancelada o completada.
 */
public class EstadoPendiente implements EstadoReserva {

    @Override
    public void modificar(Reserva reserva, Tutor nuevoTutor, String nuevaMateria, String nuevoHorario) {
        if (nuevoTutor == null || nuevaMateria == null || nuevaMateria.trim().isEmpty() || nuevoHorario == null || nuevoHorario.trim().isEmpty()) {
            throw new IllegalArgumentException("No se pueden dejar campos vacíos al modificar una reserva.");
        }
        reserva.setTutor(nuevoTutor);
        reserva.setMateria(nuevaMateria);
        reserva.setHorario(nuevoHorario);
    }

    @Override
    public void cancelar(Reserva reserva) {
        // Cambiamos el estado de la reserva a Cancelada
        reserva.setEstado(new EstadoCancelada());
    }

    @Override
    public void completar(Reserva reserva) {
        // Cambiamos el estado a Completada
        reserva.setEstado(new EstadoCompletada());
    }

    @Override
    public String getNombreEstado() {
        return "Pendiente";
    }
}
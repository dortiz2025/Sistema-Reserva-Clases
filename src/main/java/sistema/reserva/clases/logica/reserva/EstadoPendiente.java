package sistema.reserva.clases.logica.reserva;

import sistema.reserva.clases.logica.Tutor;

/**
 * Clase que representa el estado de una reserva que no se ha completado.
 * La reserva en este estado puede ser modificada,
 * cancelada o completada.
 */
public class EstadoPendiente implements EstadoReserva {

    /**
     * Modifica la reserva.
     * @param reserva Referencia de la reserva.
     * @param nuevoTutor Referencia del tutor nuevo.
     * @param nuevaMateria Nueva materia.
     * @param nuevoHorario Nuevo horario.
     */
    @Override
    public void modificarReserva(Reserva reserva, Tutor nuevoTutor, String nuevaMateria, String nuevoHorario) {
        if (nuevoTutor == null || nuevaMateria == null || nuevaMateria.trim().isEmpty() || nuevoHorario == null || nuevoHorario.trim().isEmpty()) {
            throw new IllegalArgumentException("No se pueden dejar campos vacíos al modificar una reserva.");
        }
        reserva.setTutor(nuevoTutor);
        reserva.setMateria(nuevaMateria);
        reserva.setHorario(nuevoHorario);
    }

    /**
     * Cancela la reserva.
     * @param reserva Referencia de la reserva.
     */
    @Override
    public void cancelarReserva(Reserva reserva) {
        // Cambiamos el estado de la reserva a Cancelada
        reserva.setEstado(new EstadoCancelada());
    }

    /**
     * Confirma la reserva, es decir,
     * la clase ya se realizó, o el plazo para
     * modificarla o cancelarla terminó.
     * @param reserva Referencia de la reserva.
     */
    @Override
    public void completarReserva(Reserva reserva) {
        // Cambiamos el estado a Completada
        reserva.setEstado(new EstadoCompletada());
    }

    /**
     * Devuelve el nombre de este estado.
     * @return String del nombre de este estado.
     */
    @Override
    public String getNombreEstado() {
        return "Pendiente";
    }
}
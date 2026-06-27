package sistema.reserva.clases.logica;

/**
 * Interfaz que implementa el Patrón State en Reserva.
 * Dependiendo de los estados de la reserva, la lógica cambiará.
 * En este caso, las llamadas a los métodos presentes en esta clase,
 * responderán distinto dependiendo del estado de la reserva.
 */
public interface EstadoReserva {

    // Permite modificar la reserva. Lanzará excepción si el estado no lo permite.
    void modificar(Reserva reserva, Tutor nuevoTutor, String nuevaMateria, String nuevoHorario);

    // Cancela la reserva.
    void cancelar(Reserva reserva);

    // Marca la reserva como realizada/completada.
    void completar(Reserva reserva);

    // Retorna el nombre del estado.
    String getNombreEstado();
}
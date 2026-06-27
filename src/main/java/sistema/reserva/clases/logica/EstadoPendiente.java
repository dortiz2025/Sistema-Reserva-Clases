package sistema.reserva.clases.logica;

public class EstadoPendiente implements EstadoReserva {

    @Override
    public void modificar(Reserva reserva, Tutor nuevoTutor, String nuevaMateria, String nuevoHorario) {
        // Como está pendiente, sí podemos modificar los datos
        if (nuevoTutor != null) reserva.setTutor(nuevoTutor);
        if (nuevaMateria != null) reserva.setMateria(nuevaMateria);
        if (nuevoHorario != null) reserva.setHorario(nuevoHorario);
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
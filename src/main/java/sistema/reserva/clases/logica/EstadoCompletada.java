package sistema.reserva.clases.logica;

public class EstadoCompletada implements EstadoReserva {

    @Override
    public void modificar(Reserva reserva, Tutor nuevoTutor, String nuevaMateria, String nuevoHorario) {
        throw new IllegalStateException("El registro de una clase ya impartida (completada) no puede ser modificado.");
    }

    @Override
    public void cancelar(Reserva reserva) {
        throw new IllegalStateException("No se puede cancelar una clase que ya fue impartida.");
    }

    @Override
    public void completar(Reserva reserva) {
        throw new IllegalStateException("La reserva ya se encuentra completada.");
    }

    @Override
    public String getNombreEstado() {
        return "Completada";
    }
}
package sistema.reserva.clases.logica;

public class EstadoCancelada implements EstadoReserva {

    @Override
    public void modificar(Reserva reserva, Tutor nuevoTutor, String nuevaMateria, String nuevoHorario) {
        throw new IllegalStateException("No se puede modificar una reserva que ya ha sido cancelada.");
    }

    @Override
    public void cancelar(Reserva reserva) {
        throw new IllegalStateException("La reserva ya se encuentra cancelada.");
    }

    @Override
    public void completar(Reserva reserva) {
        throw new IllegalStateException("No se puede completar una clase que fue cancelada.");
    }

    @Override
    public String getNombreEstado() {
        return "Cancelada";
    }
}
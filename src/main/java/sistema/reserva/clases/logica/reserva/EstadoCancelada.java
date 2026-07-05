package sistema.reserva.clases.logica.reserva;

import sistema.reserva.clases.logica.bloquehorario.BloqueHorario;
import sistema.reserva.clases.logica.Tutor;

import java.time.LocalDate;

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
     * @param nuevaFecha Nueva fecha.
     */
    @Override
    public void modificarReserva(Reserva reserva, Tutor nuevoTutor, String nuevaMateria, BloqueHorario nuevoHorario, LocalDate nuevaFecha) {
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
        return NombreEstado.CANCELADA.toString();
    }

    /**
     * Booleano que depende de si la reserva se debe contar o no.
     * @return False porque la reserva se canceló.
     */
    @Override
    public boolean ocupaCupo(){
        return false;
    }
}
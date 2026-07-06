package sistema.reserva.clases.logica.reserva;

import sistema.reserva.clases.logica.bloquehorario.BloqueHorario;
import sistema.reserva.clases.logica.Tutor;

import java.time.LocalDate;

/**
 * Clase que representa el estado de una reserva que ha sido completada.
 * Esta no puede ser modificada, ni cancelada, ni completada otra vez.
 */
public class EstadoCompletada implements EstadoReserva {

    /**
     * Intenta modificar la reserva.
     * En este caso se lanza excepción debido a
     * que la reserva ha sido confirmada
     * o el plazo para modificarla ya se acabó.
     * @param reserva Referencia de la reserva.
     * @param nuevoTutor Referencia del tutor nuevo.
     * @param nuevaMateria Nueva materia.
     * @param nuevoHorario Nuevo horario.
     * @param nuevaFecha Nueva fecha.
     */
    @Override
    public void modificarReserva(Reserva reserva, Tutor nuevoTutor, String nuevaMateria, BloqueHorario nuevoHorario, LocalDate nuevaFecha) {
        throw new IllegalStateException("La clase ya se realizó, la reserva no puede ser modificada.");
    }

    /**
     * Intenta cancelar la reserva.
     * En este caso se lanza excepción porque la reserva
     * ha sido confirmada o el plazo para cancelarla terminó.
     * @param reserva Referencia de la reserva.
     */
    @Override
    public void cancelarReserva(Reserva reserva) {
        throw new IllegalStateException("La clase ya se realizó, no puede ser cancelada.");
    }

    /**
     * Se intenta completar la reserva.
     * En este caso se lanza excepción porque
     * la reserva ya se completó antes.
     * @param reserva Referencia de la reserva.
     */
    @Override
    public void completarReserva(Reserva reserva) {
        throw new IllegalStateException("La clase ya se realizó, no es necesario completarla de nuevo.");
    }

    /**
     * Devuelve el estado actual.
     * @return Estado actual.
     */
    @Override
    public NombreEstado getEstado() {
        return NombreEstado.COMPLETADA;
    }

    /**
     * Booleano que depende de si la reserva se debe contar o no.
     * @return True porque la reserva se completó.
     */
    @Override
    public boolean ocupaCupo(){
        return true;
    }
}
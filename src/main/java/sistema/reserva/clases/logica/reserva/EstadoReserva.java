package sistema.reserva.clases.logica.reserva;

import sistema.reserva.clases.logica.bloquehorario.BloqueHorario;
import sistema.reserva.clases.logica.Tutor;

/**
 * Interfaz que implementa el Patrón State en Reserva.
 * Dependiendo de los estados de la reserva, la lógica cambiará.
 * En este caso, las llamadas a los métodos presentes en esta clase,
 * responderán distinto dependiendo del estado de la reserva.
 */
public interface EstadoReserva {

    /**
     * Modifica la reserva.
     * @param reserva Referencia de la reserva.
     * @param nuevoTutor Referencia del tutor nuevo.
     * @param nuevaMateria Nueva materia.
     * @param nuevoHorario Nuevo horario.
     */
    void modificarReserva(Reserva reserva, Tutor nuevoTutor, String nuevaMateria, BloqueHorario nuevoHorario);

    /**
     * Cancela la reserva.
     * @param reserva Referencia de la reserva.
     */
    void cancelarReserva(Reserva reserva);

    /**
     * Completa la reserva, es decir,
     * confirma que la clase ya se realizó
     * o que el plazo para modificarla o cancelarla terminó.
     * @param reserva Referencia de la reserva.
     */
    void completarReserva(Reserva reserva);

    /**
     * Entrega el nombre del estado.
     * @return String del nombre del estado.
     */
    String getNombreEstado();

    /**
     * Booleano que depende de si la reserva se debe contar o no.
     */
    boolean ocupaCupo();
}
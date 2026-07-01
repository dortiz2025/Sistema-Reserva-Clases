package sistema.reserva.clases.logica.reserva;

import sistema.reserva.clases.logica.BloqueHorario;
import sistema.reserva.clases.logica.Estudiante;
import sistema.reserva.clases.logica.Tutor;

/**
 * Clase que representa una reserva de una clase.
 * Está asociada a un estudiante y a un tutor que imparte cierta materia.
 * La reserva tiene un horario.
 * Puede ser modificada, cancelada o completada.
 */
public class Reserva {
    private final Estudiante estudiante;
    private Tutor tutor;
    private String materia;
    private BloqueHorario horario;
    private EstadoReserva estado;

    /**
     * Crea una reserva asignando datos asociados.
     * @param estudiante Estudiante que reserva.
     * @param tutor Tutor que imparte la clase.
     * @param materia Materia que imparte el tutor.
     * @param horario Horario agendado.
     */
    public Reserva (Estudiante estudiante, Tutor tutor, String materia, BloqueHorario horario) {
        this.estudiante = estudiante;
        this.tutor = tutor;
        this.materia = materia;
        this.horario = horario;
        this.estado = new EstadoPendiente();
    }

    public void modificarReserva(Tutor nuevoTutor, String nuevaMateria, BloqueHorario nuevoHorario) {
        // La reserva no decide si se puede modificar, le pregunta a su estado actual
        this.estado.modificarReserva(this, nuevoTutor, nuevaMateria, nuevoHorario);
    }

    public void cancelarReserva() {
        this.estado.cancelarReserva(this);
    }

    public void completarReserva() {
        this.estado.completarReserva(this);
    }

    // Setter protected para que solo los estados puedan cambiar el estado actual.
    protected void setEstado(EstadoReserva nuevoEstado) {
        this.estado = nuevoEstado;
    }

    public String getNombreEstado() {
        return this.estado.getNombreEstado();
    }

    /**
     * Getter de estudiante.
     * @return Referencia del estudiante.
     */
    public Estudiante getEstudiante() {
        return estudiante;
    }

    /**
     * Getter de tutor.
     * @return Referencia del tutor.
     */
    public Tutor getTutor() {
        return tutor;
    }

    /**
     * Getter de materia.
     * @return Materia que se imparte.
     */
    public String getMateria(){
        return materia;
    }

    /**
     * Getter de horario.
     * @return Horario agendado.
     */
    public BloqueHorario getHorario(){
        return horario;
    }

    /**
     * Modifica el tutor asociado a la reserva.
     * @param tutor Nuevo tutor.
     */
    protected void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }

    /**
     * Modifica la materia asociada a la reserva.
     * @param materia Nueva materia.
     */
    protected void setMateria(String materia) {
        this.materia = materia;
    }

    /**
     * Modifica el horario agendado.
     * @param horario Nuevo Horario.
     */
    protected void setHorario(BloqueHorario horario) {
        this.horario = horario;
    }

}
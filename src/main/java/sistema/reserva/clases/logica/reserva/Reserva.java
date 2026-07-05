package sistema.reserva.clases.logica.reserva;

import sistema.reserva.clases.logica.bloquehorario.BloqueHorario;
import sistema.reserva.clases.logica.Estudiante;
import sistema.reserva.clases.logica.Tutor;
import java.time.LocalDate;
import java.util.UUID;
import java.util.Objects;

/**
 * Clase que representa una reserva de una clase.
 * Está asociada a un estudiante y a un tutor que imparte cierta materia.
 * La reserva tiene un horario.
 * Puede ser modificada, cancelada o completada.
 */
public class Reserva {
    private final String idReserva;
    private final Estudiante estudiante;
    private Tutor tutor;
    private String materia;
    private BloqueHorario horario;
    private LocalDate fecha;
    private EstadoReserva estado;

    /**
     * Crea una reserva asignando datos asociados.
     * @param estudiante Estudiante que reserva.
     * @param tutor Tutor que imparte la clase.
     * @param materia Materia que imparte el tutor.
     * @param horario Horario agendado.
     * @param fecha Fecha de la clase.
     */
    public Reserva (Estudiante estudiante, Tutor tutor, String materia, BloqueHorario horario, LocalDate fecha) {
        //Asigna un id para la reserva.
        this.idReserva = "RES-" + UUID.randomUUID().toString().substring(0, 4).toUpperCase();
        this.estudiante = estudiante;
        this.tutor = tutor;
        this.materia = materia;
        this.horario = horario;
        this.fecha = fecha;
        this.estado = new EstadoPendiente();
    }

    /**
     * Modifica la reserva.
     * @param nuevoTutor Nuevo tutor.
     * @param nuevaMateria Nueva materia.
     * @param nuevoHorario Nuevo Horario.
     * @param nuevaFecha Nueva fecha.
     */
    public void modificarReserva(Tutor nuevoTutor, String nuevaMateria, BloqueHorario nuevoHorario, LocalDate nuevaFecha) {
        // La reserva no decide si se puede modificar, le pregunta a su estado actual
        this.estado.modificarReserva(this, nuevoTutor, nuevaMateria, nuevoHorario, nuevaFecha);
    }

    /**
     * Cancela la reserva.
     */
    public void cancelarReserva() {
        this.estado.cancelarReserva(this);
    }

    /**
     * Marca la reserva como completada.
     */
    public void completarReserva() {
        this.estado.completarReserva(this);
    }

    /**
     * Cambia el estado de la reserva.
     * @param nuevoEstado Nuevo estado de la reserva.
     */
    protected void setEstado(EstadoReserva nuevoEstado) {
        this.estado = nuevoEstado;
    }

    /**
     * Entrega el nombre del estado actual de la reserva.
     * @return String del nombre del estado.
     */
    public String getNombreEstado() {
        return this.estado.getNombreEstado();
    }

    /**
     * Booleano que depende de si la reserva se debe contar o no.
     * @return Depende del estado de la reserva.
     */
    public boolean ocupaCupo(){
        return this.estado.ocupaCupo();
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
     * Modifica el tutor asociado a la reserva.
     * @param tutor Nuevo tutor.
     */
    protected void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }

    /**
     * Getter de materia.
     * @return Materia que se imparte.
     */
    public String getMateria(){
        return materia;
    }

    /**
     * Modifica la materia asociada a la reserva.
     * @param materia Nueva materia.
     */
    protected void setMateria(String materia) {
        this.materia = materia;
    }

    /**
     * Getter de horario.
     * @return Horario agendado.
     */
    public BloqueHorario getHorario(){
        return horario;
    }

    /**
     * Modifica el horario agendado.
     * @param horario Nuevo Horario.
     */
    protected void setHorario(BloqueHorario horario) {
        this.horario = horario;
    }

    /**
     * Getter de fecha.
     * @return Fecha de la clase.
     */
    public LocalDate getFecha() {
        return this.fecha;
    }

    /**
     * Modifica la fecha de la clase.
     * @param fecha Nueva fecha.
     */
    protected void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    /**
     * Getter de idReserva.
     * @return String del id.
     */
    public String getIdReserva() {
        return this.idReserva;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reserva reserva = (Reserva) o;
        return Objects.equals(idReserva, reserva.idReserva);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idReserva);
    }

}
package sistema.reserva.clases.logica;

/**
 * Clase que representa una reserva de una clase.
 * Está asociada a un estudiante y a un tutor que imparte cierta materia.
 * La reserva tiene un horario.
 * Puede ser modificada, cancelada o completada.
 */
public class Reserva {
    private Estudiante estudiante;
    private Tutor tutor;
    private String materia;
    private String horario;

    /**
     * Crea una reserva asignando datos asociados.
     * @param estudiante Estudiante que reserva.
     * @param tutor Tutor que imparte la clase.
     * @param materia Materia que imparte el tutor.
     * @param horario Horario agendado.
     */
    public Reserva (Estudiante estudiante, Tutor tutor, String materia, String horario) {
        this.estudiante = estudiante;
        this.tutor = tutor;
        this.materia = materia;
        this.horario = horario;
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
    public String getHorario(){
        return horario;
    }

}

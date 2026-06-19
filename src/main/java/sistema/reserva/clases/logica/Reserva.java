package sistema.reserva.clases.logica;

public class Reserva {
    private Estudiante estudiante;
    private Tutor tutor;
    private String materia;
    private String horario;

    public Reserva (Estudiante estudiante, Tutor tutor, String materia, String horario) {
        this.estudiante = estudiante;
        this.tutor = tutor;
        this.materia = materia;
        this.horario = horario;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public Tutor getTutor() {
        return tutor;
    }
    public String getMateria(){
        return materia;
    }

    public String getHorario(){
        return horario;
    }

}

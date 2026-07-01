package sistema.reserva.clases.logica;

/**
 * Clase que gestiona los perfiles de los tutores en el sistema.
 * Hereda de GestorPerfil.
 */
public class GestorTutores extends GestorPerfil<Tutor> {

    /**
     * Registra un tutor en el sistema.
     * @param tutor Nuevo tutor a inscribir.
     */
    public void registrarTutor(Tutor tutor){
        // Usa el ID de 4 dígitos como llave
        super.registrarPerfil(tutor.getId(), tutor);
    }

    /**
     * Agrega un nuevo bloque de disponibilidad al tutor.
     * @param idTutor El identificador del tutor.
     * @param horario El bloque horario disponible.
     */
    public void agregarHorarioDisponibilidad(String idTutor, BloqueHorario horario) {
        // Utilizamos buscarPorId.
        Tutor tutor = super.buscarPorId(idTutor);
        if (tutor != null) {
            tutor.addHorariosDisponibles(horario);
        } else {
            throw new IllegalArgumentException("No se encontró ningún tutor con el ID: " + idTutor);
        }
    }
}
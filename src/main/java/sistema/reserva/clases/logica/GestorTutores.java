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
     * @param idTutor Key asociada al tutor.
     * @param horario Nuevo bloque horario disponible.
     */
    public void agregarHorarioDisponible(String idTutor, BloqueHorario horario) {
        obtenerTutorValidado(idTutor).addHorarioDisponible(horario);
    }

    /**
     * Elimina un horario de disponibilidad del tutor.
     * @param idTutor Identificador (key) asociado al tutor.
     * @param horario Horario que se quiere eliminar.
     */
    public boolean eliminarHorarioDisponible(String idTutor, BloqueHorario horario) {
        return obtenerTutorValidado(idTutor).removeHorarioDisponible(horario);
    }

    /**
     * Agrega una nueva materia al perfil del tutor.
     * @param idTutor Key asociada al tutor.
     * @param materia Nueva materia que imparte el tutor.
     */
    public void addMateria(String idTutor, String materia){
        obtenerTutorValidado(idTutor).addMateria(materia);
    }

    /**
     * Elimina un materia asociada al perfil del tutor.
     * @param idTutor Key del perfil del tutor.
     * @param materia Materia que se desea eliminar.
     * @return Boolean dependiendo de si la eliminación fue exitosa o no.
     */
    public boolean eliminarMateria(String idTutor, String materia){
        return obtenerTutorValidado(idTutor).removeMateria(materia);
    }

    //Lógica auxiliar para verificar referencias nulas de Tutor.
    private Tutor obtenerTutorValidado(String idTutor) {
        Tutor tutor = super.buscarPorId(idTutor);
        if (tutor == null) {
            throw new IllegalArgumentException("No se encontró ningún tutor con el ID: " + idTutor);
        }
        return tutor;
    }

}
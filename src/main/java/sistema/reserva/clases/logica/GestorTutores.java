package sistema.reserva.clases.logica;

import sistema.reserva.clases.excepciones.CorreoInvalidoException;
import sistema.reserva.clases.excepciones.CorreoYaRegistradoException;
import sistema.reserva.clases.logica.bloquehorario.BloqueHorario;

import java.util.NoSuchElementException;

/**
 * Clase que gestiona los perfiles de los tutores en el sistema.
 * Hereda de GestorPerfil.
 */
public class GestorTutores extends GestorPerfil<Tutor> {

    /**
     * Registra un tutor en el sistema.
     * @param tutor Nuevo tutor a inscribir.
     */
    public void registrarTutor(Tutor tutor) throws CorreoYaRegistradoException {
        // Usa el ID de 4 dígitos como llave
        super.registrarPerfil(tutor.getId(), tutor);
    }

    /**
     * Modifica el perfil de un tutor.
     * @param idTutor Id del tutor.
     * @param nuevoNombre Nuevo nombre.
     * @param nuevoEmail Nuevo correo electrónico.
     * @param nuevaTarifa Nueva tarifa.
     * @param nuevoCupoMaximo Nuevo cupo máximo por clase.
     * @throws CorreoInvalidoException Si el correo no es válido.
     * @throws CorreoYaRegistradoException Si el correo ya está asociado a otro perfil.
     */
    public void modificarTutor(String idTutor, String nuevoNombre, String nuevoEmail, int nuevaTarifa, int nuevoCupoMaximo)
            throws CorreoInvalidoException, CorreoYaRegistradoException {

        //Modifica el perfil.
        super.modificarDatosBasicos(idTutor, nuevoNombre, nuevoEmail);
        Tutor tutor = obtenerTutorValidado(idTutor);
        tutor.setTarifa(nuevaTarifa);
        tutor.setCupoMaximo(nuevoCupoMaximo);
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
    public void eliminarHorarioDisponible(String idTutor, BloqueHorario horario) {
        Tutor tutor = obtenerTutorValidado(idTutor);
        if (!tutor.getHorariosDisponibles().contains(horario)) {
            throw new NoSuchElementException("El horario " + horario + " no está registrado para este tutor.");
        }
        tutor.removeHorarioDisponible(horario);
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
     */
    public void eliminarMateria(String idTutor, String materia){
        Tutor tutor = obtenerTutorValidado(idTutor);
        boolean tieneMateria = tutor.getMaterias().stream()
                .anyMatch(m -> m.equalsIgnoreCase(materia));

        if (!tieneMateria) {
            throw new NoSuchElementException("La materia " + materia + " no está registrada para este tutor.");
        }
        tutor.removeMateria(materia);
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
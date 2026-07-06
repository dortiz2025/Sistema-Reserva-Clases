package sistema.reserva.clases.logica;

import sistema.reserva.clases.excepciones.CorreoYaRegistradoException;

/**
 * Clase que gestiona los perfiles de los estudiantes en el sistema.
 * Hereda de GestorPerfil.
 */
public class GestorEstudiantes extends GestorPerfil<Estudiante> {

    /**
     * Registra un estudiante en el sistema.
     * @param estudiante Nuevo estudiante a registrar.
     */
    public void registrarEstudiante(Estudiante estudiante) throws CorreoYaRegistradoException {
        // Usa la matrícula como llave para el mapa heredado de GestorPerfil.
        super.registrarPerfil(estudiante.getMatricula(), estudiante);
    }
}
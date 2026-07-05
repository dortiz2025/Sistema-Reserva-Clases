package sistema.reserva.clases.logica;

import sistema.reserva.clases.excepciones.*;
import sistema.reserva.clases.logica.bloquehorario.*;
import sistema.reserva.clases.logica.reserva.*;
import sistema.reserva.clases.logica.estrategias.*;

import java.util.List;

/**
 * Clase que funciona como una única interfaz unificada (Patrón Singleton + Facade).
 * Aquí se conectan los gestores y la lógica interna de todas las clases
 * para poder entregar un sistema de reserva de clases.
 */
public class Sistema {
    private static Sistema instancia;

    private final GestorTutores gestorTutores;
    private final GestorEstudiantes gestorEstudiantes;
    private final GestorReservas gestorReservas;

    /**
     * Se inicializa el sistema.
     */
    public  Sistema() {
        gestorTutores = new GestorTutores();
        gestorEstudiantes = new GestorEstudiantes();
        gestorReservas = new GestorReservas();
    }

    /**
     * Entrega la única instancia del sistema.
     * Si no existe, la instancia por primera y única vez.
     * @return Referencia del sistema.
     */
    public static Sistema getInstancia() {
        if (instancia == null) {
            instancia = new Sistema();
        }
        return instancia;
    }

    //--------------------GESTIÓN DE PERFILES--------------------//

    /**
     * Registra un estudiante en el sistema.
     * @param nombre Nombre del estudiante.
     * @param email Correo electrónico del estudiante.
     * @throws CorreoInvalidoException Si el correo no es válido.
     * @throws CorreoYaRegistradoException Si el correo ya está asociado a otro perfil.
     */
    public void registrarEstudiante(String nombre, String email) throws CorreoInvalidoException, CorreoYaRegistradoException {
        Estudiante nuevoEstudiante = new Estudiante(nombre, email);
        gestorEstudiantes.registrarEstudiante(nuevoEstudiante);
    }

    /**
     * Registra un tutor en el sistema.
     * @param nombre Nombre del tutor.
     * @param email Correo electrónico del tutor.
     * @param tarifa Tarifa del tutor.
     * @param cupoMaximo Cupo máximo por clase del tutor.
     * @throws CorreoInvalidoException Si el correo no es válido.
     * @throws CorreoYaRegistradoException Si el correo ya está asociado a otro perfil.
     */
    public void registrarTutor(String nombre, String email, int tarifa, int cupoMaximo) throws CorreoInvalidoException, CorreoYaRegistradoException {
        Tutor nuevoTutor = new Tutor(nombre, email, tarifa, cupoMaximo);
        gestorTutores.registrarTutor(nuevoTutor);
    }

    /**
     * Devuelve una lista de todos los estudiantes en el sistema.
     * @return Lista de estudiantes.
     */
    public List<Estudiante> obtenerEstudiantes() {
        return gestorEstudiantes.obtenerPerfiles();
    }

    /**
     * Devuelve una lista de todos los tutores en el sistema.
     * @return Lista de tutores.
     */
    public List<Tutor> obtenerTutores() {
        return gestorTutores.obtenerPerfiles();
    }

    /**
     * Agrega una materia que imparte un tutor.
     * @param idTutor Key asociada al tutor.
     * @param materia Nueva materia que se agrega a la lista.
     */
    public void agregarMateriaTutor(String idTutor, String materia) {
        gestorTutores.addMateria(idTutor, materia);
    }

    /**
     * Agrega un horario de disponibilidad de un tutor.
     * @param idTutor Key asociada al tutor.
     * @param horario Nuevo horario de disponibilidad del tutor.
     */
    public void agregarHorarioTutor(String idTutor, BloqueHorario horario) {
        gestorTutores.agregarHorarioDisponible(idTutor, horario);
    }

}

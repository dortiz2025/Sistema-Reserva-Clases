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

    // ------------------GESTIÓN DE PERFILES------------------ //

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

    /**
     * Elimina el perfil de un estudiante del sistema.
     * @param matricula Key asociada al estudiante.
     * @return Boolean si la eliminación fue exitosa.
     */
    public boolean eliminarEstudiante(String matricula) {
        //Primero se cancelan las reservas asociadas al estudiante.
        for (Reserva r : gestorReservas.obtenerReservas()) {
            if (r.getEstudiante().getMatricula().equals(matricula) && r.ocupaCupo()) {
                gestorReservas.cancelarReserva(r);
            }
        }
        //Finalmente se elimina el perfil.
        return gestorEstudiantes.eliminarPerfil(matricula);
    }

    /**
     * Elimina el perfil de un tutor en el sistema.
     * @param idTutor Key asociada al tutor.
     * @return Boolean si la eliminación fue exitosa.
     */
    public boolean eliminarTutor(String idTutor) {
        //Se cancelan las reservas asociadas al tutor.
        for (Reserva r : gestorReservas.obtenerReservas()) {
            if (r.getTutor().getId().equals(idTutor) && r.ocupaCupo()) {
                gestorReservas.cancelarReserva(r);
            }
        }
        //Se elimina el perfil.
        return gestorTutores.eliminarPerfil(idTutor);
    }

    // ------------ GESTIÓN DE RESERVAS Y BÚSQUEDAS ------------ //

    /**
     * Reserva una clase.
     * @param matricula Matrícula del estudiante que reserva.
     * @param idTutor Id del tutor que imparte la clase.
     * @param materia Materia de la clase.
     * @param horario Horario de la clase.
     * @throws EstudianteYaRegistradoException Si el estudiante ya tiene una reserva asociada.
     * @throws CupoExcedidoException Si no quedan cupos en el horario.
     * @throws ConflictoMateriaException Si la materia no se imparte en ese horario.
     */
    public void agendarClase(String matricula, String idTutor, String materia, BloqueHorario horario)
            throws EstudianteYaRegistradoException, CupoExcedidoException, ConflictoMateriaException {

        // El sistema busca los perfiles asociados a la matrícula e Id.
        Estudiante estudiante = gestorEstudiantes.buscarPorId(matricula);
        Tutor tutor = gestorTutores.buscarPorId(idTutor);

        //Se intenta reservar la clase.
        gestorReservas.registrarReserva(estudiante, tutor, materia, horario);
    }

    /**
     * Cancela una reserva de una clase.
     * @param reserva Reserva de la clase.
     */
    public void cancelarReserva(Reserva reserva) {
        gestorReservas.cancelarReserva(reserva);
    }

    /**
     * Devuelve una lista de las reservas para mostrarlas en un calendario.
     * @return Lista de las reservas.
     */
    public List<Reserva> obtenerCalendarioCompleto() {
        return gestorReservas.obtenerReservas();
    }

    /**
     * Búsqueda de tutores utilizando Patrón Strategy y Composite.
     */
    public List<Tutor> buscarTutoresDinamicamente(String nombre, String materia) {
        FiltroCompuesto<Tutor> filtroAvanzado = new FiltroCompuesto<>();

        // Si el usuario escribió un nombre, se usa el filtro por nombre.
        if (nombre != null && !nombre.trim().isEmpty()) {
            filtroAvanzado.agregarFiltro(new FiltroPorNombre<>(nombre));
        }

        // Si el usuario ingresó una materia, se usa el filtro por materia.
        if (materia != null && !materia.trim().isEmpty()) {
            filtroAvanzado.agregarFiltro(new FiltroTutorPorMateria(materia));
        }

        //Devuelve la lista filtrada.
        return gestorTutores.filtrar(filtroAvanzado);
    }
}

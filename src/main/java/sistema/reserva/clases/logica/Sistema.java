package sistema.reserva.clases.logica;

import sistema.reserva.clases.excepciones.*;
import sistema.reserva.clases.logica.bloquehorario.*;
import sistema.reserva.clases.logica.reserva.*;
import sistema.reserva.clases.logica.estrategias.*;

import java.time.LocalDate;
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
     * Modifica los datos de un estudiante existente.
     * @param matricula Matrícula del estudiante a modificar.
     * @param nuevoNombre Nuevo nombre.
     * @param nuevoEmail Nuevo correo electrónico.
     * @throws CorreoInvalidoException Si el formato del correo es inválido.
     * @throws CorreoYaRegistradoException Si el nuevo correo ya pertenece a otro estudiante.
     */
    public void modificarEstudiante(String matricula, String nuevoNombre, String nuevoEmail)
            throws CorreoInvalidoException, CorreoYaRegistradoException {

        if (nuevoEmail == null || nuevoEmail.trim().isEmpty()) {
            throw new IllegalArgumentException("El correo no puede estar vacío.");
        }

        Estudiante estudiante = gestorEstudiantes.buscarPorId(matricula);

        // Verifica que no se repita un email asociado a otro estudiante.
        if (!estudiante.getEmail().equalsIgnoreCase(nuevoEmail)) {
            boolean correoEnUso = gestorEstudiantes.obtenerPerfiles().stream()
                    .anyMatch(p -> p.getEmail().equalsIgnoreCase(nuevoEmail));

            if (correoEnUso) {
                throw new CorreoYaRegistradoException("El correo " + nuevoEmail + " ya está en uso por otro estudiante.");
            }
            estudiante.setEmail(nuevoEmail);
        }

        //Modifica el resto de atributos.
        estudiante.setNombre(nuevoNombre);
    }

    /**
     * Modifica los datos principales de un tutor existente.
     * @param idTutor ID del tutor a modificar.
     * @param nuevoNombre Nuevo nombre.
     * @param nuevoEmail Nuevo correo electrónico.
     * @param nuevaTarifa Nueva tarifa.
     * @param nuevoCupoMaximo Nuevo límite de alumnos por clase.
     * @throws CorreoInvalidoException Si el formato del correo es inválido.
     * @throws CorreoYaRegistradoException Si el nuevo correo ya está asociado a otro tutor.
     */
    public void modificarTutor(String idTutor, String nuevoNombre, String nuevoEmail, int nuevaTarifa, int nuevoCupoMaximo)
            throws CorreoInvalidoException, CorreoYaRegistradoException {

        if (nuevoEmail == null || nuevoEmail.trim().isEmpty()) {
            throw new IllegalArgumentException("El correo no puede estar vacío.");
        }

        Tutor tutor = gestorTutores.buscarPorId(idTutor);

        //Verifica que no exista otro tutor con ese correo.
        if (!tutor.getEmail().equalsIgnoreCase(nuevoEmail)) {
            boolean correoEnUso = gestorTutores.obtenerPerfiles().stream()
                    .anyMatch(p -> p.getEmail().equalsIgnoreCase(nuevoEmail));

            if (correoEnUso) {
                throw new CorreoYaRegistradoException("El correo " + nuevoEmail + " ya está en uso por otro tutor.");
            }
            tutor.setEmail(nuevoEmail);
        }

        //Verifica que no existan conflictos con el cambio de cupo.
        if (nuevoCupoMaximo < tutor.getCupoMaximo()) {
            validarReduccionDeCupos(idTutor, nuevoCupoMaximo);
        }

        //Aplica los cambios.
        tutor.setNombre(nuevoNombre);
        tutor.setTarifa(nuevaTarifa);
        tutor.setCupoMaximo(nuevoCupoMaximo);
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
     * Elimina una materia asociada a un tutor.
     * @param idTutor Id del tutor.
     * @param materia Materia a eliminar.
     */
    public void eliminarMateriaTutor(String idTutor, String materia) {
        //Se cancelan clases pendientes de esa materia específica.
        for (Reserva r : gestorReservas.obtenerReservas()) {
            if (r.getTutor().getId().equals(idTutor) &&
                    r.getMateria().equalsIgnoreCase(materia) &&
                    r.getNombreEstado().equals(NombreEstado.PENDIENTE.toString())) {
                gestorReservas.cancelarReserva(r);
            }
        }
        //Se elimina la materia del perfil.
        gestorTutores.eliminarMateria(idTutor, materia);
    }

    /**
     * Elimina un horario de disponibilidad de un tutor.
     * @param idTutor Id del tutor.
     * @param horario Horario a eliminar.
     */
    public void eliminarHorarioTutor(String idTutor, BloqueHorario horario) {
        //Se cancelan clases pendientes en ese bloque específico.
        for (Reserva r : gestorReservas.obtenerReservas()) {
            if (r.getTutor().getId().equals(idTutor) &&
                    r.getHorario().equals(horario) &&
                    r.getNombreEstado().equals(NombreEstado.PENDIENTE.toString())) {
                gestorReservas.cancelarReserva(r);
            }
        }
        //Se elimina el bloque del perfil.
        gestorTutores.eliminarHorarioDisponible(idTutor, horario);
    }

    /**
     * Elimina el perfil de un estudiante del sistema.
     * @param matricula Key asociada al estudiante.
     * @return Boolean si la eliminación fue exitosa.
     */
    public boolean eliminarEstudiante(String matricula) {
        // Se cancelan las reservas pendientes asociadas al perfil del estudiante.
        for (Reserva r : gestorReservas.obtenerReservas()) {
            if (r.getEstudiante().getMatricula().equals(matricula) &&
                    r.getNombreEstado().equals(NombreEstado.PENDIENTE.toString())) {
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
        //Las reservas pendientes asociadas al tutor se cancelan.
        for (Reserva r : gestorReservas.obtenerReservas()) {
            if (r.getTutor().getId().equals(idTutor) &&
                    r.getNombreEstado().equals(NombreEstado.PENDIENTE.toString())) {
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
     * @param fecha Fecha de la clase.
     * @throws EstudianteYaRegistradoException Si el estudiante ya tiene una reserva asociada.
     * @throws CupoExcedidoException Si no quedan cupos en el horario.
     * @throws ConflictoMateriaException Si la materia no se imparte en ese horario.
     */
    public void agendarClase(String matricula, String idTutor, String materia, BloqueHorario horario, LocalDate fecha)
            throws EstudianteYaRegistradoException, CupoExcedidoException, ConflictoMateriaException {

        // El sistema busca los perfiles asociados a la matrícula e Id.
        Estudiante estudiante = gestorEstudiantes.buscarPorId(matricula);
        Tutor tutor = gestorTutores.buscarPorId(idTutor);

        //Se intenta reservar la clase.
        gestorReservas.registrarReserva(estudiante, tutor, materia, horario, fecha);
    }

    /**
     * Cancela una reserva de una clase.
     * @param reserva Reserva de la clase.
     */
    public void cancelarReserva(Reserva reserva) {
        gestorReservas.cancelarReserva(reserva);
    }

    /**
     * Completa una reserva.
     * @param reserva Referencia de la reserva.
     */
    public void completarReserva(Reserva reserva) {
        gestorReservas.completarReserva(reserva);
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

    /**
     * Devuelve una lista de todas las reservas para un calendario completo.
     * @return Lista de las reservas.
     */
    public List<Reserva> obtenerCalendarioCompleto() {
        return gestorReservas.obtenerReservas();
    }

    /**
     * Devuelve una lista de reservas de un Estudiante específico para un calendario.
     * @param matricula Matrícula del estudiante.
     */
    public List<Reserva> obtenerCalendarioEstudiante(String matricula) {
        // Validamos que el estudiante exista.
        gestorEstudiantes.buscarPorId(matricula);

        // Usamos FiltrarStrategy para filtrar.
        return gestorReservas.filtrarReservas(
                r -> r.getEstudiante()
                        .getMatricula().equals(matricula));
    }

    /**
     * Devuelve una lista de reservas de un Tutor específico para un calendario.
     * @param idTutor Id del tutor.
     */
    public List<Reserva> obtenerCalendarioTutor(String idTutor) {
        // Validamos que el tutor exista.
        gestorTutores.buscarPorId(idTutor);

        // Usamos FiltrarStrategy para filtrar.
        return gestorReservas.filtrarReservas(
                r -> r.getTutor()
                        .getId().equals(idTutor));
    }

    //Verifica que al reducir el cupo máximo de un tutor,
    // no existan clases ya agendadas que superen el nuevo límite impuesto.
    private void validarReduccionDeCupos(String idTutor, int nuevoCupo) {
        for (Reserva r1 : gestorReservas.obtenerReservas()) {
            if (r1.getTutor().getId().equals(idTutor) && r1.ocupaCupo()) {

                // Contamos cuántas reservas existen para ese mismo bloque exacto y esa fecha exacta
                long alumnosEnEsaClase = gestorReservas.obtenerReservas().stream()
                        .filter(r2 -> r2.getTutor().getId().equals(idTutor)
                                && r2.getFecha().equals(r1.getFecha())
                                && r2.getHorario().equals(r1.getHorario())
                                && r2.ocupaCupo())
                        .count();

                if (alumnosEnEsaClase > nuevoCupo) {
                    throw new IllegalStateException("Operación rechazada: No se puede reducir el cupo a " + nuevoCupo +
                            ". El tutor ya tiene una clase agendada el " + r1.getFecha() +
                            " (" + r1.getHorario() + ") con " + alumnosEnEsaClase + " alumnos inscritos.");
                }
            }
        }
    }
}

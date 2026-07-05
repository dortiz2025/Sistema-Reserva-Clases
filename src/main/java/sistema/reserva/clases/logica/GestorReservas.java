package sistema.reserva.clases.logica;

import sistema.reserva.clases.excepciones.*;
import sistema.reserva.clases.logica.bloquehorario.BloqueHorario;
import sistema.reserva.clases.logica.estrategias.FiltrarStrategy;
import sistema.reserva.clases.logica.reserva.NombreEstado;
import sistema.reserva.clases.logica.reserva.Reserva;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Clase que se encarga de gestionar las reservas
 * y resolver los conflictos asociados.
 */
public class GestorReservas {
    private final List<Reserva> reservas;

    public GestorReservas() {
        this.reservas = new ArrayList<>();
    }

    /**
     * Intenta registrar una nueva reserva validando las reglas de negocio.
     * @param estudiante Estudiante que reserva la clase.
     * @param tutor Tutor que imparte la clase.
     * @param materia Materia que imparte el tutor.
     * @param horario Horario de la clase.
     * @param fecha Fecha de la clase.
     * @throws EstudianteYaRegistradoException Si es que el estudiante ya tiene una reserva en el horario.
     * @throws CupoExcedidoException Si es que el cupo de la clase alcanzó su máximo.
     * @throws ConflictoMateriaException Si el tutor tiene una clase de otra materia en ese horario.
     */
    public void registrarReserva(Estudiante estudiante, Tutor tutor, String materia, BloqueHorario horario, LocalDate fecha)
            throws EstudianteYaRegistradoException, CupoExcedidoException, ConflictoMateriaException {

        //Se valida con lógica auxiliar.
        validarReglasDeNegocio(estudiante, tutor, materia, horario, fecha,null);

        //Si se pasaron exitosamente todas las verificaciones, la reserva se agenda.
        Reserva nuevaReserva = new Reserva(estudiante, tutor, materia, horario, fecha);
        this.reservas.add(nuevaReserva);
    }

    /**
     * Intenta modificar una reserva validando las reglas del negocio.
     * @param nuevoTutor Nuevo tutor que imparte una clase.
     * @param nuevaMateria Nueva materia que imparte el tutor.
     * @param nuevoHorario Nuevo horario de la clase.
     * @param nuevaFecha Nueva fecha de la clase.
     * @param reserva Referencia de la reserva.
     * @throws EstudianteYaRegistradoException Si es que el estudiante ya tiene una reserva en el nuevo horario.
     * @throws CupoExcedidoException Si es que el cupo de la clase alcanzó su máximo.
     * @throws ConflictoMateriaException Si el tutor tiene una clase de otra materia en ese horario.
     */
    public void modificarReserva(Tutor nuevoTutor, String nuevaMateria, BloqueHorario nuevoHorario,LocalDate nuevaFecha, Reserva reserva)
            throws EstudianteYaRegistradoException, CupoExcedidoException, ConflictoMateriaException {

        //Verifica que la reserva se pueda modificar antes de resolver conflictos.
        if (!reserva.getNombreEstado().equals(NombreEstado.PENDIENTE.toString())) {
            throw new IllegalStateException("Operación rechazada: No se puede modificar una reserva que se encuentra " + reserva.getNombreEstado() + ".");
        }

        //Se valida con lógica auxiliar.
        validarReglasDeNegocio(reserva.getEstudiante(), nuevoTutor, nuevaMateria, nuevoHorario, nuevaFecha, reserva);
        //Si se pasaron exitosamente todas las verificaciones, la reserva se modifica.
        reserva.modificarReserva(nuevoTutor, nuevaMateria, nuevoHorario, nuevaFecha);
    }

    /**
     * Se cancela una reserva.
     * @param reserva Reserva que se quiere cancelar.
     */
    public void cancelarReserva(Reserva reserva) {
        reserva.cancelarReserva();
    }

    /**
     * Completa una reserva.
     * @param reserva Referencia de la reserva.
     */
    public void completarReserva(Reserva reserva) {
        reserva.completarReserva();
    }

    /**
     * Devuelve una lista de todas las reservas.
     * @return Lista de reservas.
     */
    public List<Reserva> obtenerReservas() {
        return Collections.unmodifiableList(this.reservas); // Devolvemos una copia por seguridad.
    }

    /**
     * Busca una reserva específica utilizando su identificador único.
     * @param idReserva El identificador generado.
     * @return La referencia de la reserva.
     */
    public Reserva buscarReservaPorId(String idReserva) {
        return this.reservas.stream()
                .filter(r -> r.getIdReserva().equalsIgnoreCase(idReserva))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("No se encontró ninguna reserva con el ID: " + idReserva));
    }

    /**
     * Devuelve una lista de reservas filtradas.
     * @param estrategia Filtro entregado.
     * @return Lista filtrada.
     */
    public List<Reserva> filtrarReservas(FiltrarStrategy<Reserva> estrategia) {
        return this.reservas.stream()
                .filter(estrategia::cumpleCondicion)
                .collect(Collectors.toList());
    }

    //Lógica de validación auxiliar para registrarReserva() y modificarReserva().
    //Aquí se implementa la "Resolución de Conflictos" expuesta en el enunciado.
    private void validarReglasDeNegocio(Estudiante estudiante, Tutor tutor, String materia, BloqueHorario horario, LocalDate fecha, Reserva reservaAModificar)
            throws EstudianteYaRegistradoException, CupoExcedidoException, ConflictoMateriaException {

        //Evita que se ingresen campos nulos.
        if (estudiante == null || tutor == null || materia == null || horario == null || fecha == null) {
            throw new IllegalArgumentException("Todos los campos son obligatorios para gestionar la reserva.");
        }

        //Evita que se agenden reservas en el pasado.
        if (fecha.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("No se pueden agendar o modificar reservas en fechas pasadas.");
        }

        //Verifica que la fecha sea coherente con el día de bloqueHorario.
        int diaFechaInt = fecha.getDayOfWeek().getValue();
        int diaBloqueInt = horario.getDia().ordinal() + 1;
        if (diaFechaInt != diaBloqueInt) {
            throw new IllegalArgumentException("Inconsistencia: La fecha " + fecha + " no corresponde a un día " + horario.getDia().toString());
        }

        //Verifica que el tutor imparta la materia (ignora mayúsculas y minúsculas).
        boolean imparteMateria = tutor.getMaterias().stream()
                .anyMatch(m -> m.equalsIgnoreCase(materia));
        if (!imparteMateria) {
            throw new IllegalArgumentException("El tutor " + tutor.getNombre() + " no imparte la materia: " + materia);
        }

        if (!tutor.getHorariosDisponibles().contains(horario)) {
            throw new IllegalArgumentException("El tutor no tiene disponibilidad en el horario: " + horario);
        }

        //Verifica que el tutor tiene horarios disponibles.
        if (!tutor.getHorariosDisponibles().contains(horario)) {
            throw new IllegalArgumentException("El tutor no tiene disponibilidad en el horario: " + horario);
        }

        //Se verifica si el estudiante tiene una reserva en el mismo horario solicitado
        //o si ya no quedan cupos en el horario deseado.
        int alumnosInscritosEnBloque = 0;
        for (Reserva r : this.reservas) {
            if (r.equals(reservaAModificar)) {
                continue;//Se ignora a si misma para poder ejecutar correctamente modificarReserva()
            }

            boolean esMismoEstudiante = r.getEstudiante().getMatricula().equals(estudiante.getMatricula());
            boolean esMismoHorario = r.getHorario().equals(horario) && r.getFecha().equals(fecha);
            boolean esMismoTutor = r.getTutor().getId().equals(tutor.getId());

            if (esMismoEstudiante && esMismoHorario && r.ocupaCupo()) { //Verifica si hay reservas propias en el mismo horario.
                throw new EstudianteYaRegistradoException("El estudiante ya tiene una reserva en la fecha: " + fecha + " y horario: " + horario);
            }

            if (esMismoTutor && esMismoHorario && r.ocupaCupo()) {//Suma las reservas en ese horario para comprobar disponibilidad de cupo.
                if (!r.getMateria().equals(materia)) {
                    throw new ConflictoMateriaException("El tutor tiene una clase de " + r.getMateria() + " en ese horario.");
                }
                alumnosInscritosEnBloque++;
            }
        }

        if (alumnosInscritosEnBloque >= tutor.getCupoMaximo()) { //Verifica el cupo con la suma anterior.
            throw new CupoExcedidoException("El tutor " + tutor.getNombre() +
                    " ya alcanzó su límite máximo de " + tutor.getCupoMaximo() + " alumnos en ese horario.");
        }
    }
}

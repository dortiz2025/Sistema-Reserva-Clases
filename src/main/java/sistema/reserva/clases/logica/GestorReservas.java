package sistema.reserva.clases.logica;

import sistema.reserva.clases.excepciones.*;
import sistema.reserva.clases.logica.bloquehorario.BloqueHorario;
import sistema.reserva.clases.logica.estrategias.FiltrarStrategy;
import sistema.reserva.clases.logica.reserva.Reserva;

import java.util.*;
import java.util.stream.Collectors;

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
     * @throws EstudianteYaRegistradoException Si es que el estudiante ya tiene una reserva en el horario.
     * @throws CupoExcedidoException Si es que el cupo de la clase alcanzó su máximo.
     * @throws ConflictoMateriaException Si el tutor tiene una clase de otra materia en ese horario.
     */
    public void registrarReserva(Estudiante estudiante, Tutor tutor, String materia, BloqueHorario horario)
            throws EstudianteYaRegistradoException, CupoExcedidoException, ConflictoMateriaException {

        //Verifica si el tutor imparte la materia.
        if (!tutor.getMaterias().contains(materia)) {
            throw new IllegalArgumentException("El tutor " + tutor.getNombre() + " no imparte la materia: " + materia);
        }

        //Verifica si el tutor tiene disponibilidad en el horario requerido.
        if (!tutor.getHorariosDisponibles().contains(horario)) {
            throw new IllegalArgumentException("El tutor no tiene disponibilidad en el horario: " + horario);
        }

        //Verifica si el estudiante tiene una reserva pendiente en ese mismo horario.
        for (Reserva r : this.reservas){
            boolean esMismoEstudiante = r.getEstudiante().getMatricula().equals(estudiante.getMatricula());
            boolean esMismoHorario = r.getHorario().equals(horario);

            if (esMismoEstudiante && esMismoHorario && r.ocupaCupo()) {
                throw new EstudianteYaRegistradoException("El estudiante ya tiene una reserva en el horario: " + horario);
            }
        }

        //Verifica si el cupo por clase del tutor ya fue alcanzado.
        int alumnosInscritosEnBloque = 0;
        for (Reserva r : this.reservas) {
            boolean esMismoTutor = r.getTutor().getId().equals(tutor.getId());
            boolean esMismoHorario = r.getHorario().equals(horario);

            if (esMismoTutor && esMismoHorario && r.ocupaCupo()) {
                if (!r.getMateria().equals(materia)) {
                    throw new ConflictoMateriaException("El tutor tiene una clase de " + r.getMateria() + " en ese horario.");
                }
                alumnosInscritosEnBloque++;
            }
        }
        if (alumnosInscritosEnBloque >= tutor.getCupoMaximo()) {
            throw new CupoExcedidoException("El tutor " + tutor.getNombre() +
                    " ya alcanzó su límite máximo de " + tutor.getCupoMaximo() + " alumnos en ese horario.");
        }

        //Si se pasaron exitosamente todas las verificaciones, la reserva se agenda.
        Reserva nuevaReserva = new Reserva(estudiante, tutor, materia, horario);
        this.reservas.add(nuevaReserva);
    }

    /**
     * Se cancela una reserva.
     * @param reserva Reserva que se quiere cancelar.
     */
    public void cancelarReservaDeLista(Reserva reserva) {
        reserva.cancelarReserva();
    }

    /**
     * Devuelve una lista de todas las reservas.
     * @return Lista de reservas.
     */
    public List<Reserva> obtenerTodasLasReservas() {
        return Collections.unmodifiableList(this.reservas); // Devolvemos una copia por seguridad.
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

}

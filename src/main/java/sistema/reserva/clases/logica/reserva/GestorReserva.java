package sistema.reserva.clases.logica.reserva;

import sistema.reserva.clases.excepciones.CupoExcedidoException;
import sistema.reserva.clases.excepciones.EstudianteYaInscritoException;
import sistema.reserva.clases.logica.BloqueHorario;
import sistema.reserva.clases.logica.Estudiante;
import sistema.reserva.clases.logica.Tutor;

import java.util.ArrayList;
import java.util.List;

public class GestorReserva {
    private List<Reserva> listaReservas;

    public GestorReserva() {
        this.listaReservas = new ArrayList<>();
    }

    /**
     * Intenta registrar una nueva reserva validando las reglas de negocio.
     */
    public void registrarReserva(Estudiante estudiante, Tutor tutor, String materia, BloqueHorario horario)
            throws EstudianteYaInscritoException, CupoExcedidoException {

        for (Reserva r : listaReservas){
            boolean esMismoEstudiante = r.getEstudiante().getMatricula().equals(estudiante.getMatricula());
            boolean esMismoHorario = r.getHorario().equals(horario);
            boolean noEstaCancelada = !r.getNombreEstado().equals("Cancelada"); // Asumiendo que getNombreEstado() devuelve "Cancelada"

            if (esMismoEstudiante && esMismoHorario && noEstaCancelada) {
                throw new EstudianteYaInscritoException("El estudiante " + estudiante.getNombre() +
                        " ya tiene una reserva en el " + horario.toString());
            }
        }

        int alumnosInscritosEnBloque = 0;

        for (Reserva r : listaReservas) {
            boolean esMismoTutor = r.getTutor().getId().equals(tutor.getId());
            boolean esMismoHorario = r.getHorario().equals(horario);
            boolean noEstaCancelada = !r.getNombreEstado().equals("Cancelada");

            if (esMismoTutor && esMismoHorario && noEstaCancelada) {
                alumnosInscritosEnBloque++;
            }
        }
        if (alumnosInscritosEnBloque >= tutor.getMaxAlum()) {
            throw new CupoExcedidoException("El tutor " + tutor.getNombre() +
                    " ya alcanzó su límite máximo de " + tutor.getMaxAlum() + " alumnos en ese horario.");
        }
        Reserva nuevaReserva = new Reserva(estudiante, tutor, materia, horario);
        listaReservas.add(nuevaReserva);
    }

    /**
     * Devuelve todas las reservas para mostrarlas en el calendario de la GUI.
     */
    public List<Reserva> obtenerTodasLasReservas() {
        return new ArrayList<>(listaReservas); // Devolvemos una copia por seguridad
    }

    public void cancelarReservaDeLista(Reserva reserva) {
        reserva.cancelarReserva();
    }
}

package sistema.reserva.clases.logica.reserva;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sistema.reserva.clases.excepciones.CorreoInvalidoException;
import sistema.reserva.clases.logica.Estudiante;
import sistema.reserva.clases.logica.Tutor;
import sistema.reserva.clases.logica.bloquehorario.Bloque;
import sistema.reserva.clases.logica.bloquehorario.BloqueHorario;
import sistema.reserva.clases.logica.bloquehorario.DiaSemana;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class EstadoPendienteTest {
/**
 * Clase de prueba unitaria para EstadoPendiente.
 * Verifica el comportamiento y las transiciones de estado de una reserva
 * que se encuentra en su estado inicial (Pendiente).
 */
public class EstadoPendienteTest {

    private EstadoPendiente estadoPendiente;
    private Reserva reserva;

    /**
     * Inicializa el contexto de la prueba antes de la ejecución de cada método.
     * Garantiza que cada prueba opere sobre una instancia limpia.
     */
    @BeforeEach
    public void setUp() throws CorreoInvalidoException {
        BloqueHorario bloqueHorario = new BloqueHorario(DiaSemana.LUNES, Bloque.BLOQUE_3);
        Estudiante estudiante = new Estudiante("Alumno DOO", "alumno@udec.cl");
        Tutor tutor = new Tutor("Tutor Estrella", "tutor@udec.cl", 15000, 20);

        estadoPendiente = new EstadoPendiente();
        reserva = new Reserva(estudiante, tutor, "Matemáticas", bloqueHorario, LocalDate.parse("2026-05-04"));

        reserva.setEstado(estadoPendiente);
    }

}
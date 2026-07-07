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

/**
 * Pruebas unitarias para el comportamiento del estado Cancelada dentro del ciclo de vida de una Reserva.
 */
public class EstadoCanceladaTest {

    private EstadoCancelada estadoCancelada;
    private Reserva reserva;

    /**
     * Inicializa el estado y los objetos de negocio necesarios antes de cada prueba para asegurar independencia de contexto.
     *  @throws CorreoInvalidoException Si el formato del correo de las entidades Estudiante o Tutor no es válido.
     */
    @BeforeEach
    public void setUp() throws CorreoInvalidoException {
        estadoCancelada = new EstadoCancelada();
        Estudiante estudiante = new Estudiante("Alumno DOO", "alumno@udec.cl");
        Tutor tutor = new Tutor("Tutor", "tutor@udec.cl", 15000, 20);

        BloqueHorario bloqueHorario = new BloqueHorario(DiaSemana.LUNES, Bloque.BLOQUE_3);

        // Se asigna la instancia al atributo de la clase para evitar sombreado de variables (shadowing)
        this.reserva = new Reserva(estudiante, tutor, "Matemáticas", bloqueHorario, LocalDate.parse("2026-05-04"));
    }

    /**
     * Comprueba que el sistema rechace cualquier intento de modificar una reserva que ya se encuentra cancelada,
     * lanzando la excepción correspondiente.
     */
    @Test
    public void testModificarReservaLanzaExcepcion() {
        assertThrows(IllegalStateException.class, () -> {
            estadoCancelada.modificarReserva(reserva, null, null, null, null);
        }, "Debe lanzar IllegalStateException al intentar modificar una reserva en estado cancelada.");
    }

    /**
     * Comprueba que el sistema rechace cualquier intento de volver a cancelar una reserva que ya se encuentra cancelada,
     * lanzando la excepción correspondiente.
     */
    @Test
    public void testCancelarReservaLanzaExcepcion() {
        assertThrows(IllegalStateException.class, () -> {
            estadoCancelada.cancelarReserva(reserva);
        }, "Debe lanzar IllegalStateException al intentar cancelar una reserva que ya está en estado cancelada.");
    }
}
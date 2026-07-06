package sistema.reserva.clases.logica.reserva;

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
 * Pruebas unitarias para la entidad Reserva y su ciclo de vida.
 */
class ReservaTest {

    /**
     * Verifica que toda nueva reserva se inicialice por defecto en EstadoPendiente.
     */
    @Test
    void testNuevaReservaIniciaComoPendiente() throws CorreoInvalidoException {
        // Arrange
        Estudiante estudiante = new Estudiante("Alumno DOO", "alumno@udec.cl");
        Tutor tutor = new Tutor("Tutor Estrella", "tutor@udec.cl", 15000, 20);

        BloqueHorario bloqueHorario = new BloqueHorario(DiaSemana.LUNES, Bloque.BLOQUE_3);

        // Act: Se utiliza LocalDate para la fecha.
        // Si Bloque.BLOQUE_3 sigue en rojo, revisa si el constructor pide un BloqueHorario.
        Reserva reserva = new Reserva(estudiante, tutor, "Matemáticas", bloqueHorario, LocalDate.parse("2026-05-04"));

        // Assert
        assertEquals(NombreEstado.PENDIENTE, reserva.getEstado(), "El estado inicial de la reserva debe ser PENDIENTE");
    }
}
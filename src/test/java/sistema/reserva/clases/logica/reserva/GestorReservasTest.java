package sistema.reserva.clases.logica.reserva;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sistema.reserva.clases.excepciones.*;
import sistema.reserva.clases.logica.Estudiante;
import sistema.reserva.clases.logica.Tutor;
import sistema.reserva.clases.logica.bloquehorario.Bloque;
import sistema.reserva.clases.logica.bloquehorario.BloqueHorario;
import sistema.reserva.clases.logica.bloquehorario.DiaSemana;
import sistema.reserva.clases.logica.estrategias.FiltrarStrategy;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias para la clase GestorReservas.
 * Verifica la correcta administración de reservas, manejo de estados y resolución de conflictos de negocio.
 */
public class GestorReservasTest {

    private GestorReservas gestor;
    private Estudiante estudiante1;
    private Estudiante estudiante2;
    private Estudiante estudiante3;
    private Tutor tutor;
    private BloqueHorario bloqueLunes;
    private LocalDate fechaFuturaLunes;

    /**
     * Inicializa el gestor y los objetos de negocio necesarios antes de cada prueba.
     * @throws CorreoInvalidoException Si el formato del correo de las entidades Estudiante o Tutor no es válido.
     */
    @BeforeEach
    public void setUp() throws CorreoInvalidoException {
        gestor = new GestorReservas();

        estudiante1 = new Estudiante("Alumno Uno", "alumno1@udec.cl");
        estudiante2 = new Estudiante("Alumno Dos", "alumno2@udec.cl");
        estudiante3 = new Estudiante("Alumno Tres", "alumno3@udec.cl");

        bloqueLunes = new BloqueHorario(DiaSemana.LUNES, Bloque.BLOQUE_3);

        //Se usa una clase anónima para poder acceder a los métodos protected de Tutor
        tutor = new Tutor("Tutor Test", "tutor@udec.cl", 15000, 2) {
            {
                addMateria("Matemáticas");
                addMateria("Física");
                addHorarioDisponible(bloqueLunes);
            }
        };

        fechaFuturaLunes = LocalDate.now();
        while (fechaFuturaLunes.getDayOfWeek() != java.time.DayOfWeek.MONDAY) {
            fechaFuturaLunes = fechaFuturaLunes.plusDays(1);
        }
        fechaFuturaLunes = fechaFuturaLunes.plusWeeks(1);
    }

    /**
     * Comprueba que el sistema registre exitosamente una reserva cuando se cumplen todas las reglas de negocio.
     */
    @Test
    public void testRegistrarReservaExito() throws Exception {
        String idReserva = gestor.registrarReserva(estudiante1, tutor, "Matemáticas", bloqueLunes, fechaFuturaLunes);

        assertNotNull(idReserva);
        assertEquals(1, gestor.obtenerReservas().size());
        assertEquals("Matemáticas", gestor.buscarReservaPorId(idReserva).getMateria());
    }

    /**
     * Comprueba que el sistema rechace la reserva si hay inconsistencia entre la fecha ingresada y el día del bloque,
     * lanzando la excepción correspondiente.
     */
    @Test
    public void testRegistrarReservaInconsistenciaDiaLanzaExcepcion() throws CorreoInvalidoException {
        BloqueHorario bloqueMartes = new BloqueHorario(DiaSemana.MARTES, Bloque.BLOQUE_3);

        Tutor tutorMartes = new Tutor("Tutor Martes", "tutormartes@udec.cl", 15000, 2) {
            {
                addMateria("Matemáticas");
                addHorarioDisponible(bloqueMartes);
            }
        };

        assertThrows(IllegalArgumentException.class, () -> {
            gestor.registrarReserva(estudiante1, tutorMartes, "Matemáticas", bloqueMartes, fechaFuturaLunes);
        });
    }

    /**
     * Comprueba que el sistema rechace la reserva si el tutor no imparte la materia solicitada,
     * lanzando la excepción correspondiente.
     */
    @Test
    public void testRegistrarReservaTutorNoImparteMateriaLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> {
            gestor.registrarReserva(estudiante1, tutor, "Química", bloqueLunes, fechaFuturaLunes);
        });
    }

    /**
     * Comprueba que el sistema rechace la reserva si el estudiante ya tiene una agendada en el mismo horario,
     * lanzando la excepción correspondiente.
     */
    @Test
    public void testRegistrarReservaEstudianteYaRegistradoLanzaExcepcion() throws Exception {
        gestor.registrarReserva(estudiante1, tutor, "Matemáticas", bloqueLunes, fechaFuturaLunes);

        assertThrows(EstudianteYaRegistradoException.class, () -> {
            gestor.registrarReserva(estudiante1, tutor, "Matemáticas", bloqueLunes, fechaFuturaLunes);
        });
    }

    /**
     * Comprueba que el sistema rechace la reserva si el tutor ya tiene una clase de otra materia en ese horario,
     * lanzando la excepción correspondiente.
     */
    @Test
    public void testRegistrarReservaConflictoMateriaLanzaExcepcion() throws Exception {
        gestor.registrarReserva(estudiante1, tutor, "Matemáticas", bloqueLunes, fechaFuturaLunes);

        assertThrows(ConflictoMateriaException.class, () -> {
            gestor.registrarReserva(estudiante2, tutor, "Física", bloqueLunes, fechaFuturaLunes);
        });
    }

    /**
     * Comprueba que el sistema rechace la reserva si se ha superado el cupo máximo definido para el tutor,
     * lanzando la excepción correspondiente.
     */
    @Test
    public void testRegistrarReservaCupoExcedidoLanzaExcepcion() throws Exception {
        gestor.registrarReserva(estudiante1, tutor, "Matemáticas", bloqueLunes, fechaFuturaLunes);
        gestor.registrarReserva(estudiante2, tutor, "Matemáticas", bloqueLunes, fechaFuturaLunes);

        assertThrows(CupoExcedidoException.class, () -> {
            gestor.registrarReserva(estudiante3, tutor, "Matemáticas", bloqueLunes, fechaFuturaLunes);
        });
    }
}
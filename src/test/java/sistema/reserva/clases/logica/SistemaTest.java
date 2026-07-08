package sistema.reserva.clases.logica;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sistema.reserva.clases.excepciones.*;
import sistema.reserva.clases.logica.bloquehorario.Bloque;
import sistema.reserva.clases.logica.bloquehorario.BloqueHorario;
import sistema.reserva.clases.logica.bloquehorario.DiaSemana;
import sistema.reserva.clases.logica.reserva.NombreEstado;
import sistema.reserva.clases.logica.reserva.Reserva;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias para la clase Sistema.
 * Verifica la correcta integración (Facade) entre los distintos gestores del sistema y sus reglas transversales.
 */
public class SistemaTest {

    private Sistema sistema;
    private String matriculaBase;
    private String idTutorBase;
    private BloqueHorario bloqueLunes;
    private LocalDate fechaFuturaLunes;

    /**
     * Inicializa el sistema unificado y los objetos de negocio necesarios antes de cada prueba.
     * Reinicia el Singleton para asegurar la independencia de contexto entre los tests.
     * @throws Exception Si ocurre un error al usar Reflection o registrar entidades base.
     */
    @BeforeEach
    public void setUp() throws Exception {
        // Reiniciar el Singleton de Sistema usando Reflection
        Field field = Sistema.class.getDeclaredField("instancia");
        field.setAccessible(true);
        field.set(null, null);

        sistema = Sistema.getInstancia();

        matriculaBase = sistema.registrarEstudiante("Estudiante Base", "base@udec.cl");
        idTutorBase = sistema.registrarTutor("Tutor Base", "tutorbase@udec.cl", 15000, 2);

        sistema.agregarMateriaTutor(idTutorBase, "Matemáticas");
        bloqueLunes = new BloqueHorario(DiaSemana.LUNES, Bloque.BLOQUE_3);
        sistema.agregarHorarioTutor(idTutorBase, bloqueLunes);

        fechaFuturaLunes = LocalDate.now();
        while (fechaFuturaLunes.getDayOfWeek() != java.time.DayOfWeek.MONDAY) {
            fechaFuturaLunes = fechaFuturaLunes.plusDays(1);
        }
        fechaFuturaLunes = fechaFuturaLunes.plusWeeks(1);
    }

    /**
     * Comprueba que el sistema registre exitosamente un estudiante y devuelva su matrícula.
     */
    @Test
    public void testRegistrarEstudianteExito() throws CorreoInvalidoException, CorreoYaRegistradoException {
        String matricula = sistema.registrarEstudiante("Nuevo Alumno", "nuevo@udec.cl");

        assertNotNull(matricula);
        assertEquals(2, sistema.obtenerEstudiantes().size());
        assertEquals("Nuevo Alumno", sistema.obtenerEstudiantePorId(matricula).getNombre());
    }

    /**
     * Comprueba que el sistema modifique correctamente los datos de un estudiante.
     */
    @Test
    public void testModificarEstudianteExito() throws CorreoInvalidoException, CorreoYaRegistradoException {
        sistema.modificarEstudiante(matriculaBase, "Estudiante Modificado", "modificado@udec.cl");

        Estudiante estudiante = sistema.obtenerEstudiantePorId(matriculaBase);
        assertEquals("Estudiante Modificado", estudiante.getNombre());
        assertEquals("modificado@udec.cl", estudiante.getEmail());
    }

    /**
     * Comprueba que el sistema rechace la reducción de cupos de un tutor si este ya tiene clases agendadas
     * que superan el nuevo límite impuesto, lanzando la excepción correspondiente.
     */
    @Test
    public void testModificarTutorReduccionCuposLanzaExcepcion() throws Exception {
        String matricula2 = sistema.registrarEstudiante("Estudiante Dos", "dos@udec.cl");

        sistema.agendarClase(matriculaBase, idTutorBase, "Matemáticas", bloqueLunes, fechaFuturaLunes);
        sistema.agendarClase(matricula2, idTutorBase, "Matemáticas", bloqueLunes, fechaFuturaLunes);

        assertThrows(IllegalStateException.class, () -> {
            sistema.modificarTutor(idTutorBase, "Tutor Base", "tutorbase@udec.cl", 15000, 1);
        }, "Debe lanzar IllegalStateException si se intenta reducir el cupo habiendo ya reservas que lo superen.");
    }

    /**
     * Comprueba que al eliminar una materia de un tutor, el sistema cancele masivamente las reservas pendientes asociadas.
     */
    @Test
    public void testEliminarMateriaTutorCancelaReservasAsociadas() throws Exception {
        String idReserva = sistema.agendarClase(matriculaBase, idTutorBase, "Matemáticas", bloqueLunes, fechaFuturaLunes);

        sistema.eliminarMateriaTutor(idTutorBase, "Matemáticas");

        Reserva reserva = sistema.obtenerReservaPorId(idReserva);
        assertEquals(NombreEstado.CANCELADA, reserva.getEstado());
    }

    /**
     * Comprueba que al eliminar un tutor del sistema, se cancelen también todas sus reservas pendientes.
     */
    @Test
    public void testEliminarTutorCancelaReservasYRemuevePerfil() throws Exception {
        String idReserva = sistema.agendarClase(matriculaBase, idTutorBase, "Matemáticas", bloqueLunes, fechaFuturaLunes);

        sistema.eliminarTutor(idTutorBase);

        Reserva reserva = sistema.obtenerReservaPorId(idReserva);
        assertEquals(NombreEstado.CANCELADA, reserva.getEstado());
        assertThrows(NoSuchElementException.class, () -> sistema.obtenerTutorPorId(idTutorBase));
    }

    /**
     * Comprueba que el sistema unificado agende correctamente una clase delegando al gestor de reservas.
     */
    @Test
    public void testAgendarClaseExito() throws Exception {
        String idReserva = sistema.agendarClase(matriculaBase, idTutorBase, "Matemáticas", bloqueLunes, fechaFuturaLunes);

        assertNotNull(idReserva);
        Reserva reserva = sistema.obtenerReservaPorId(idReserva);
        assertEquals(matriculaBase, reserva.getEstudiante().getMatricula());
        assertEquals(idTutorBase, reserva.getTutor().getId());
    }

}
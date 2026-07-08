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

}
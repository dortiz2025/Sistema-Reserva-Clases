package sistema.reserva.clases.logica.estrategias;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sistema.reserva.clases.logica.Tutor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de prueba unitaria para FiltroTutorPorMateria.
 * Verifica que la estrategia de filtrado evalúe correctamente las materias
 * asociadas a un tutor, considerando la insensibilidad a mayúsculas y minúsculas.
 */
public class FiltroTutorPorMateriaTest {

    private Tutor tutorPrueba;
    private Tutor tutorSinMaterias;

    /**
     * Inicializa el contexto de la prueba antes de la ejecución de cada método.
     * Se instancian clases anónimas que heredan de Tutor para controlar el
     * retorno del método getMaterias() y garantizar el aislamiento de la prueba.
     *
     * @throws Exception Permite la instanciación en caso de que el constructor
     * de Tutor lance excepciones controladas (ej. CorreoInvalidoException).
     */
    @BeforeEach
    public void setUp() throws Exception {
        // Stub de un tutor con un conjunto predefinido de materias
        tutorPrueba = new Tutor("Nuevo Tutor", "nuevo@udec.cl", 3200, 23) {
            @Override
            public List<String> getMaterias() {
                return Arrays.asList("Cálculo", "Física", "Programación");
            }
        };

        // Stub de un tutor sin materias para evaluar el caso borde de listas vacías
        tutorSinMaterias = new Tutor("Nuevo Tutor", "nuevo@udec.cl",3200,23) {
            @Override
            public List<String> getMaterias() {
                return new ArrayList<>();
            }
        };
    }

    /**
     * Prueba la evaluación cuando el criterio de búsqueda coincide exactamente
     * con una de las materias de la lista del tutor.
     */
    @Test
    public void testCumpleCondicionCoincidenciaExacta() {
        FiltroTutorPorMateria filtro = new FiltroTutorPorMateria("Física");

        assertTrue(filtro.cumpleCondicion(tutorPrueba),
                "El filtro debe retornar verdadero ante una coincidencia exacta de la materia.");
    }

    /**
     * Prueba que la evaluación sea insensible a las diferencias entre
     * mayúsculas y minúsculas (case-insensitive) al buscar en la lista.
     */
    @Test
    public void testCumpleCondicionIgnoraMayusculasYMinusculas() {
        // Se inyecta el criterio de búsqueda alternando mayúsculas y minúsculas
        FiltroTutorPorMateria filtro = new FiltroTutorPorMateria("pRoGrAmAcIóN");

        assertTrue(filtro.cumpleCondicion(tutorPrueba),
                "El filtro debe ignorar las mayúsculas y minúsculas al evaluar la coincidencia.");
    }

    /**
     * Prueba la evaluación cuando la materia buscada no existe
     * dentro de la lista de materias del tutor.
     */
    @Test
    public void testNoCumpleCondicionMateriaInexistente() {
        FiltroTutorPorMateria filtro = new FiltroTutorPorMateria("Química");

        assertFalse(filtro.cumpleCondicion(tutorPrueba),
                "El filtro debe retornar falso si la materia no se encuentra en la colección del tutor.");
    }
}
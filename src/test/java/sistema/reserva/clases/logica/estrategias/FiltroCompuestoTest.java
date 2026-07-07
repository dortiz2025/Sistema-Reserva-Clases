package sistema.reserva.clases.logica.estrategias;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de prueba unitaria para FiltroCompuesto.
 * Verifica el comportamiento del patrón Composite al agrupar y ejecutar
 * secuencialmente múltiples estrategias de filtrado mediante el método cumpleCondicion.
 */
public class FiltroCompuestoTest {

    private FiltroCompuesto<String> filtroCompuesto;

    /**
     * Inicializa el contexto de la prueba antes de la ejecución de cada método.
     * Se instanciará utilizando el tipo genérico String para abstraer y aislar
     * la prueba de las dependencias de otras clases del dominio (como Tutor o Estudiante).
     */
    @BeforeEach
    public void setUp() {
        filtroCompuesto = new FiltroCompuesto<>();
    }

    /**
     * Prueba el comportamiento del filtro compuesto cuando no se le ha agregado
     * ninguna estrategia interna.
     * Verifica que cualquier objeto evaluado cumpla la condición por defecto.
     */
    @Test
    public void testCumpleCondicionSinFiltros() {
        assertTrue(filtroCompuesto.cumpleCondicion("ObjetoDePrueba"),
                "Si el FiltroCompuesto está vacío, la evaluación por defecto debe retornar verdadero.");
    }

    /**
     * Prueba el encadenamiento de filtros simulando el comportamiento del patrón Composite.
     * Se inyectan estrategias anónimas (Stubs) que retornan siempre verdadero,
     * verificando que el resultado final de la intersección lógica sea verdadero.
     */
    @Test
    public void testCumpleCondicionMultiplesFiltrosVerdaderos() {
        filtroCompuesto.agregarFiltro(new FiltrarStrategy<String>() {
            @Override
            public boolean cumpleCondicion(String item) {
                return true;
            }
        });

        filtroCompuesto.agregarFiltro(new FiltrarStrategy<String>() {
            @Override
            public boolean cumpleCondicion(String item) {
                return true;
            }
        });

        assertTrue(filtroCompuesto.cumpleCondicion("ObjetoDePrueba"),
                "El resultado final debe ser verdadero si estrictamente todas las estrategias internas retornan verdadero.");
    }

    /**
     * Prueba la correcta aplicación de la intersección lógica (AND) en el filtro compuesto.
     * Se inyecta una estrategia que retorna falso para verificar que
     * la evaluación general resulta en falso, simulando el incumplimiento de una regla.
     */
    @Test
    public void testCumpleCondicionConFiltroFalso() {
        filtroCompuesto.agregarFiltro(new FiltrarStrategy<String>() {
            @Override
            public boolean cumpleCondicion(String item) {
                return true;
            }
        });

}
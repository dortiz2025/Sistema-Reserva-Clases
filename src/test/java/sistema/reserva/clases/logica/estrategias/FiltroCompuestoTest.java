package sistema.reserva.clases.logica.estrategias;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FiltroCompuestoTest {
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


}
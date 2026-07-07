package sistema.reserva.clases.logica.bloquehorario;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BloqueTest {
/**
 * Clase de prueba unitaria para el enumerador Bloque.
 * Verifica la correcta definición de sus constantes y el acceso
 * a los atributos internos (hora de inicio y hora de fin).
 */
public class BloqueTest {

    /**
     * Prueba la correcta asignación y recuperación de los atributos de un bloque específico.
     * Verifica que los valores devueltos por los métodos de acceso coincidan con
     * los valores definidos en el constructor de la constante.
     */
    @Test
    public void testAtributosDeBloque() {
        Bloque bloque = Bloque.BLOQUE_3;

        assertEquals("10:00", bloque.getHoraInicio(),
                "La hora de inicio debe coincidir estrictamente con el valor asignado en el enumerador.");

        assertEquals("10:45", bloque.getHoraFin(),
                "La hora de fin debe coincidir estrictamente con el valor asignado en el enumerador.");
    }

}
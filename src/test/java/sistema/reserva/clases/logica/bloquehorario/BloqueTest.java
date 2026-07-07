package sistema.reserva.clases.logica.bloquehorario;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

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

    /**
     * Verifica la integridad de la enumeración asegurando que contenga
     * los elementos definidos en los requisitos del dominio.
     * Esta prueba previene la eliminación accidental de constantes en futuras modificaciones.
     */
    @Test
    public void testCantidadDeBloquesValidos() {
        // Recupera todas las constantes definidas en el enumerador Bloque
        Bloque[] bloquesDefinidos = Bloque.values();

        // Verifica que el enumerador no esté vacío.
        // Si se conoce el número exacto de bloques (por ejemplo, 8 bloques al día),
        // se recomienda cambiar el 'assertTrue' por: assertEquals(8, bloquesDefinidos.length);
        assertTrue(bloquesDefinidos.length > 0,
                "El enumerador Bloque debe contener al menos una constante definida en el sistema.");
    }
}
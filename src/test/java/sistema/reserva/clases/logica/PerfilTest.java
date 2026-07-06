package sistema.reserva.clases.logica;

import org.junit.jupiter.api.Test;
import sistema.reserva.clases.excepciones.CorreoInvalidoException;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de prueba unitaria para Perfil.
 * Valida que la información de contacto básica de los usuarios del sistema
 * se inicialice de manera correcta.
 */
class PerfilTest {

    /**
     * Verifica que el constructor asigne el nombre y el correo correctamente.
     * @throws CorreoInvalidoException Si el correo no tiene formato válido.
     */
    @Test
    void testAsignacionDatosBase() throws CorreoInvalidoException {

        Perfil perfil = new Perfil("Juan Mecánico", "juan.meca@udec.cl") {};

        assertEquals("Juan Mecánico", perfil.getNombre());
        assertEquals("juan.meca@udec.cl", perfil.getEmail());
    }
}
package sistema.reserva.clases.logica;

import org.junit.jupiter.api.Test;
import sistema.reserva.clases.excepciones.CorreoInvalidoException;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de prueba unitaria para Estudiante.
 * Verifica la herencia de datos y la correcta generación de la matrícula única.
 */
class EstudianteTest {

    /**
     * Comprueba que la matrícula generada con UUID cumpla con el formato:
     * 6 caracteres de largo y en mayúsculas.
     * @throws CorreoInvalidoException Si el formato del correo falla.
     */
    @Test
    void testGeneracionDeMatricula() throws CorreoInvalidoException {
        Estudiante estudiante = new Estudiante("María López", "maria@udec.cl");

        //Sacamos la matrícula para revisarla
        String matricula = estudiante.getMatricula();

        assertNotNull(matricula);
        assertEquals(6, matricula.length());
        assertEquals(matricula.toUpperCase(), matricula);
    }
}
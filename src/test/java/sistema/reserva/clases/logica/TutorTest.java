package sistema.reserva.clases.logica;

import org.junit.jupiter.api.Test;
import sistema.reserva.clases.excepciones.CorreoInvalidoException;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de prueba unitaria para Tutor.
 * Comprueba que el tutor pueda instanciarse con la materia que va a dictar.
 */
class TutorTest {

    /**
     * Valida que el tutor guarde bien su materia de especialidad.
     * @throws CorreoInvalidoException Si se ingresa un correo malo al crearlo.
     */
    @Test
    void testCreacionTutorConMateria() throws CorreoInvalidoException {
        //Asumiendo que el constructor del Tutor incluye la materia al final
        Tutor tutor = new Tutor("Profe Ayudante", "ayudante@udec.cl", 10000, 20);

        assertEquals("Profe Ayudante", tutor.getNombre());

        tutor.addMateria("Desarrollo Orientado A Objetos");
        assertTrue(tutor.getMaterias().contains("Desarrollo Orientado A Objetos"));
    }
}
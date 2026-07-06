package sistema.reserva.clases.logica;

import org.junit.jupiter.api.Test;
import sistema.reserva.clases.excepciones.CorreoInvalidoException;

import static org.junit.jupiter.api.Assertions.*;

class EstudianteTest {
    @Test
    void testCreacionEstudianteExitosa() throws CorreoInvalidoException {
        String nombreEsperado = "Luis Soto";
        String emailEsperado = "luis.soto@email.com";

        Estudiante estudiante = new Estudiante(nombreEsperado, emailEsperado);

        //Se verifica que los datos heredados de Perfil se asignen correctamente
        assertEquals(nombreEsperado, estudiante.getNombre(), "El nombre del estudiante no coincide.");
        assertEquals(emailEsperado, estudiante.getEmail(), "El email del estudiante no coincide.");
    }
}
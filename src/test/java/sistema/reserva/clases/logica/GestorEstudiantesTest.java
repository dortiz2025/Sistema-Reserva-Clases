package sistema.reserva.clases.logica;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sistema.reserva.clases.excepciones.CorreoInvalidoException;
import sistema.reserva.clases.excepciones.CorreoYaRegistradoException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias para el gestor encargado del ciclo de vida de los Estudiantes.
 */
class GestorEstudiantesTest {

    private GestorEstudiantes gestor;

    /**
     * Inicializa el estado antes de cada prueba para asegurar independencia de contexto.
     */
    @BeforeEach
    void setUp() {
        gestor = new GestorEstudiantes();
    }

    /**
     * Valida que un estudiante válido se registre correctamente en el sistema.
     * La verificación se realiza consultando la colección interna del gestor.
     * @throws CorreoInvalidoException si el formato de correo no cumple con el formato.
     * @throws CorreoYaRegistradoException si el correo ya se había registrado previamente
     * para evitar duplicados.
     */
    @Test
    void testRegistrarEstudianteExitoso() throws CorreoInvalidoException, CorreoYaRegistradoException {
        // Arrange
        Estudiante estudiante = new Estudiante("Catalina Silva", "cata.silva@udec.cl");

        // Act
        gestor.registrarEstudiante(estudiante);

        // Assert
        // Se utiliza el método heredado de GestorPerfil
        assertTrue(gestor.obtenerPerfiles().contains(estudiante), "El estudiante debe encontrarse en la colección del gestor tras su registro.");
    }

    /**
     * Comprueba que el sistema rechace el registro de un estudiante nuevo
     * que utilice un correo ya existente, lanzando las excepciones correspondientes.
     */
    @Test
    void testRegistrarEstudianteDuplicadoLanzaExcepcion() throws CorreoInvalidoException, CorreoYaRegistradoException {
        //Se instancian dos estudiantes distintos pero con el mismo correo electrónico
        Estudiante estudiante1 = new Estudiante("Carlos Vera", "carlos.v@udec.cl");
        Estudiante estudiante2 = new Estudiante("Carlos Varela", "carlos.v@udec.cl");

        //El registro del primer estudiante es exitoso
        gestor.registrarEstudiante(estudiante1);

        //El registro del segundo estudiante falla en la validación de correo
        assertThrows(CorreoYaRegistradoException.class, () -> {
            gestor.registrarEstudiante(estudiante2);
        }, "Debe lanzar CorreoYaRegistradoException al ingresar un nuevo estudiante con un correo ya en uso.");
    }
}
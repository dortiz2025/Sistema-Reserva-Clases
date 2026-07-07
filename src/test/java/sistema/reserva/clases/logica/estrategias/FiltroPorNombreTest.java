package sistema.reserva.clases.logica.estrategias;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sistema.reserva.clases.logica.Perfil;
import sistema.reserva.clases.excepciones.CorreoInvalidoException; // Ajustar según tu estructura

import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de prueba unitaria para FiltroPorNombre.
 * Verifica que la estrategia de filtrado evalúe correctamente las cadenas de texto,
 * considerando coincidencias parciales y la insensibilidad a mayúsculas o minúsculas.
 */
public class FiltroPorNombreTest {

    private Perfil perfilPrueba;

    /**
     * Inicializa el contexto de la prueba antes de la ejecución de cada método.
     * Se instancia un objeto Perfil real utilizando su constructor paramétrico.
     *
     * @throws CorreoInvalidoException Si el formato del correo inyectado es incorrecto.
     */
    @BeforeEach
    public void setUp() throws CorreoInvalidoException {
        //Se utiliza {} para crear subclase anónima e instanciarla, ya que Perfil es una clase abstracta.
        perfilPrueba = new Perfil("Camilo", "camilo@udec.cl") {};
    }
}
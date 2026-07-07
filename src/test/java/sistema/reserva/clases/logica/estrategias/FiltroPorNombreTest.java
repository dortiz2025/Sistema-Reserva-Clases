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

    /**
     * Prueba la evaluación cuando el criterio de búsqueda coincide exactamente
     * con el nombre del perfil (en minúsculas).
     */
    @Test
    public void testCumpleCondicionCoincidenciaExacta() {
        FiltroPorNombre<Perfil> filtro = new FiltroPorNombre<>("camilo");

        assertTrue(filtro.cumpleCondicion(perfilPrueba),
                "El filtro debe retornar verdadero ante una coincidencia exacta de la cadena.");
    }

    /**
     * Prueba la evaluación cuando el criterio de búsqueda es solo una subcadena
     * del nombre del perfil (coincidencia parcial).
     */
    @Test
    public void testCumpleCondicionCoincidenciaParcial() {
        FiltroPorNombre<Perfil> filtro = new FiltroPorNombre<>("cami");

        assertTrue(filtro.cumpleCondicion(perfilPrueba),
                "El filtro debe retornar verdadero si el nombre contiene la subcadena buscada.");
    }

}
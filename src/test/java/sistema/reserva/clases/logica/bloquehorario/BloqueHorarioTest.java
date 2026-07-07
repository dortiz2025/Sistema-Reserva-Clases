package sistema.reserva.clases.logica.bloquehorario;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static sistema.reserva.clases.logica.bloquehorario.Bloque.BLOQUE_9;

/**
 * Clase de prueba unitaria para BloqueHorario.
 * Verifica la correcta asignación y recuperación del día de la semana
 * y el bloque de tiempo asociado a una disponibilidad o reserva.
 */
public class BloqueHorarioTest {

    private BloqueHorario bloqueHorario;
    private Bloque bloqueMock;

    /**
     * Inicializa el contexto de la prueba antes de la ejecución de cada método.
     * Garantiza el aislamiento instanciando nuevos objetos.
     */
    @BeforeEach
    public void setUp() {
        //Utilizamos el nombre definido en el Enum de Bloque para recibir el formato pedido.
        bloqueMock = BLOQUE_9;

        bloqueHorario = new BloqueHorario(DiaSemana.LUNES, bloqueMock);
    }

    /**
     * Verifica que el método de acceso para el día de la semana
     * retorne el valor correcto asignado durante la instanciación.
     */
    @Test
    public void testGetDiaSemana() {
        assertEquals(DiaSemana.LUNES, bloqueHorario.getDia(),
                "El método debe retornar DiaSemana.LUNES, que fue el valor inyectado en el constructor.");
    }

    /**
     * Verifica que el método de acceso para el bloque de tiempo
     * retorne exactamente la instancia esperada.
     */
    @Test
    public void testGetBloque() {
        assertEquals(bloqueMock, bloqueHorario.getBloque(),
                "El bloque retornado debe coincidir con la instancia asignada al BloqueHorario.");
    }

    /**
     * Verifica la comparación de igualdad entre dos bloques horarios idénticos.
     */
    @Test
    public void testEquals() {
        BloqueHorario otroBloqueHorario = new BloqueHorario(DiaSemana.LUNES, bloqueMock);

        assertEquals(bloqueHorario, otroBloqueHorario,
                "Dos BloqueHorario con el mismo día y bloque deben ser considerados iguales.");
    }
}
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

class BloqueHorarioTest {

}
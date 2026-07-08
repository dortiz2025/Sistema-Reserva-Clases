package sistema.reserva.clases.logica;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sistema.reserva.clases.excepciones.CorreoInvalidoException;
import sistema.reserva.clases.excepciones.CorreoYaRegistradoException;
import sistema.reserva.clases.logica.bloquehorario.Bloque;
import sistema.reserva.clases.logica.bloquehorario.BloqueHorario;
import sistema.reserva.clases.logica.bloquehorario.DiaSemana;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class GestorTutoresTest {
/**
 * Pruebas unitarias para la clase GestorTutores.
 * Verifica el correcto registro, modificación y administración de horarios y materias asociadas a los tutores.
 */
public class GestorTutoresTest {

    private GestorTutores gestor;
    private Tutor tutor;
    private BloqueHorario bloque;

    /**
     * Inicializa el gestor y los objetos de negocio necesarios antes de cada prueba para asegurar independencia de contexto.
     * @throws CorreoInvalidoException Si el formato del correo de la entidad Tutor no es válido al instanciarlo.
     */
    @BeforeEach
    public void setUp() throws CorreoInvalidoException {
        gestor = new GestorTutores();
        tutor = new Tutor("Tutor Ciencias", "ciencias@udec.cl", 10000, 5);
        bloque = new BloqueHorario(DiaSemana.MIERCOLES, Bloque.BLOQUE_4);
    }


}
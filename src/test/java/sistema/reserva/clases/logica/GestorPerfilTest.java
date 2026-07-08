package sistema.reserva.clases.logica;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sistema.reserva.clases.excepciones.CorreoInvalidoException;
import sistema.reserva.clases.excepciones.CorreoYaRegistradoException;
import sistema.reserva.clases.logica.estrategias.FiltrarStrategy;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias para la clase GestorPerfil.
 * Al ser una clase abstracta, se utilizan implementaciones Dummy para verificar su comportamiento.
 */
public class GestorPerfilTest {

    /**
     * Implementación concreta y simplificada de la clase Perfil para uso exclusivo en pruebas.
     */
    static class PerfilDummy extends Perfil {
        private String nombre;
        private String email;

        /**
         * Constructor de PerfilDummy.
         * @param nombre Nombre del perfil.
         * @param email Correo electrónico del perfil.
         */
        public PerfilDummy(String nombre, String email) throws CorreoInvalidoException {
            super(nombre, email);
            this.setNombre(nombre);
            this.setEmail(email);
        }

        @Override
        public String getNombre() { return nombre; }
        @Override
        public void setNombre(String nombre) { this.nombre = nombre; }
        @Override
        public String getEmail() { return email; }
        @Override
        public void setEmail(String email) { this.email = email; }
    }

    /**
     * Implementación concreta de la clase GestorPerfil para probar sus métodos base.
     */
    static class GestorPerfilDummy extends GestorPerfil<PerfilDummy> {
    }

    private GestorPerfilDummy gestor;
    private PerfilDummy perfil1;
    private PerfilDummy perfil2;

    /**
     * Inicializa el gestor y los perfiles de prueba antes de cada test.
     */
    @BeforeEach
    public void setUp() throws CorreoInvalidoException {
        gestor = new GestorPerfilDummy();
        perfil1 = new PerfilDummy("Juan Díaz", "juan@udec.cl");
        perfil2 = new PerfilDummy("Martín Álvarez", "martin@udec.cl");
    }

    /**
     * Comprueba que el sistema registre exitosamente un perfil cuando los datos son válidos.
     */
    @Test
    public void testRegistrarPerfilExito() throws CorreoYaRegistradoException {
        gestor.registrarPerfil("ID01", perfil1);

        assertEquals(1, gestor.obtenerPerfiles().size());
        assertEquals(perfil1, gestor.buscarPorId("ID01"));
    }

    /**
     * Comprueba que el sistema rechace el registro de un perfil si se intenta utilizar una Key (ID) ya existente,
     * lanzando la excepción correspondiente.
     */
    @Test
    public void testRegistrarPerfilIdDuplicadoLanzaExcepcion() throws CorreoYaRegistradoException, CorreoInvalidoException {
        gestor.registrarPerfil("ID01", perfil1);
        PerfilDummy perfilMismoId = new PerfilDummy("Carlos", "carlos@udec.cl");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            gestor.registrarPerfil("ID01", perfilMismoId);
        });
        assertTrue(exception.getMessage().contains("ya se encuentra asociado a un perfil"));
    }

    /**
     * Comprueba que el sistema rechace el registro de un perfil si el correo ingresado ya está en uso,
     * lanzando la excepción correspondiente.
     */
    @Test
    public void testRegistrarPerfilCorreoDuplicadoLanzaExcepcion() throws CorreoYaRegistradoException, CorreoInvalidoException {
        gestor.registrarPerfil("ID01", perfil1);
        PerfilDummy perfilMismoCorreo = new PerfilDummy("Juan Falso", "juan@udec.cl");

        assertThrows(CorreoYaRegistradoException.class, () -> {
            gestor.registrarPerfil("ID02", perfilMismoCorreo);
        });
    }

    /**
     * Comprueba que el sistema devuelva el perfil correcto cuando se busca por un ID existente.
     */
    @Test
    public void testBuscarPorIdExito() throws CorreoYaRegistradoException {
        gestor.registrarPerfil("ID01", perfil1);

        PerfilDummy encontrado = gestor.buscarPorId("ID01");

        assertNotNull(encontrado);
        assertEquals("Juan Díaz", encontrado.getNombre());
    }

    /**
     * Comprueba que el sistema lance una excepción al buscar un ID que no se encuentra registrado.
     */
    @Test
    public void testBuscarPorIdNoEncontradoLanzaExcepcion() {
        assertThrows(NoSuchElementException.class, () -> {
            gestor.buscarPorId("ID_INEXISTENTE");
        });
    }

}
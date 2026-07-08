package sistema.reserva.clases.logica;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sistema.reserva.clases.excepciones.CorreoInvalidoException;
import sistema.reserva.clases.excepciones.CorreoYaRegistradoException;
import sistema.reserva.clases.logica.estrategias.FiltrarStrategy;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class GestorPerfilTest {
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

}
package sistema.reserva.clases.logica;

import sistema.reserva.clases.excepciones.CorreoInvalidoException;

/**
 * Clase que representa un perfil en el sistema.
 * Debe extenderse a un tipo de perfil específico.
 */
public abstract class Perfil {
    private String nombre;
    private String email;

    /**
     * Constructor de la clase.
     * @param nombre Nombre del perfil.
     * @param email Email del perfil.
     * @throws CorreoInvalidoException Se lanza excepción si el correo es inválido.
     */
    public Perfil(String nombre, String email) throws CorreoInvalidoException {
        validarDatos(nombre, email);
        this.nombre = nombre;
        this.email = email;
    }

    /**
     * Getter de nombre.
     * @return Nombre del perfil.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Setter de nombre.
     * @param nombre Nuevo nombre del perfil.
     */
    protected void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        this.nombre = nombre;
    }

    /**
     * Getter del email.
     * @return Nombre del perfil.
     */
    protected String getEmail() {
        return email;
    }

    /**
     * Setter del perfil.
     * @param email Nuevo email asociado.
     * @throws CorreoInvalidoException Verificación de @ en el email.
     */
    public void setEmail(String email) throws CorreoInvalidoException {
        validarDatos(this.nombre, email);
        this.email = email;
    }

    // Lógica auxiliar para proteger la integridad de los datos base
    private void validarDatos(String nombre, String email) throws CorreoInvalidoException {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("El correo electrónico no puede estar vacío.");
        }
        if (!email.contains("@")) {
            throw new CorreoInvalidoException("Email inválido");
        }
    }
}

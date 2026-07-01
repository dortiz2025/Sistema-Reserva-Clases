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
     * @throws CorreoInvalidoException Se lanza excepción si el correo es inválido,
     * es decir, si no contiene un @.
     */
    public Perfil(String nombre, String email) throws CorreoInvalidoException {
        this.nombre = nombre;
        if (email.contains("@")) this.email = email;
        else throw new CorreoInvalidoException("Email invalido");
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
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Getter del email.
     * @return Nombre del perfil.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter del perfil.
     * @param email Nuevo email asociado.
     * @throws CorreoInvalidoException Verificación de @ en el email. Si no lo contiene, se lanza excepción.
     */
    public void setEmail(String email) throws CorreoInvalidoException {
        if (email.contains("@")) this.email = email;
        else throw new CorreoInvalidoException("Email invalido");

    }
}

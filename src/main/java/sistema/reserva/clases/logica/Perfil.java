package sistema.reserva.clases.logica;

import sistema.reserva.clases.excepciones.CorreoInvalidoException;

/**
 * Clase que representa un perfil en el sistema.
 * Debe extenderse a un tipo de perfil específico.
 */
public abstract class Perfil {
    private String nombre;
    private String email;

    public Perfil(String nombre, String email) throws CorreoInvalidoException {
        this.nombre = nombre;
        if (email.contains("@")) this.email = email;
        else throw new CorreoInvalidoException("Email invalido");
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws CorreoInvalidoException {
        if (email.contains("@")) this.email = email;
        else throw new CorreoInvalidoException("Email invalido");

    }
}

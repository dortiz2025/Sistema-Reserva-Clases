package sistema.reserva.clases.logica;

/**
 * Clase que representa un perfil en el sistema.
 * Debe extenderse a un tipo de perfil específico.
 */
public abstract class Perfil {
    private String nombre;
    private String email;

    public Perfil(String nombre, String email) {
        this.nombre = nombre;
        this.email = email;
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

    public void setEmail(String email) {
        this.email = email;
    }
}

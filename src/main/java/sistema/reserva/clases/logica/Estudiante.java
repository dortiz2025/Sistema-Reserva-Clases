package sistema.reserva.clases.logica;

/**
 * Clase que representa a un estudiante.
 * Este puede tomar clases reservando un horario.
 */
public class Estudiante {
    private final String nombre;
    private final String matricula;
    private final String email;

    /**
     * Inicializa la clase con datos básicos del estudiante.
     * @param nombre Asigna nombre.
     * @param matricula Matrícula única (como un ID).
     * @param email Correo electrónico para recibir confirmaciones.
     */
    public Estudiante(String nombre, String matricula, String email) {
        this.nombre = nombre;
        this.matricula = matricula;
        this.email = email;
    }

    /**
     * Getter de nombre.
     * @return nombre del estudiante.
     */
    public String getNombre() {
        return this.nombre;
    }

    /**
     * Getter de la matrícula.
     * @return matrícula del estudiante.
     */
    public String getMatricula() {
        return this.matricula;
    }

    /**
     * Getter del email.
     * @return Correo electrónico del estudiante.
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * ToString de la clase.
     * Útil para imprimir datos del estudiante en pantalla.
     * @return Datos representativos de la clase.
     */
    @Override
    public String toString() {
        return "Estudiante{" +
                "nombre='" + nombre + '\'' +
                ", matricula='" + matricula + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

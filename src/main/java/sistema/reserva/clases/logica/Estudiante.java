package sistema.reserva.clases.logica;

import java.util.UUID;

/**
 * Clase que representa a un estudiante.
 * Este puede tomar clases reservando un horario.
 */
public class Estudiante {
    private String nombre;
    private final String matricula;
    private String email;

    /**
     * Inicializa la clase con datos básicos del estudiante.
     * @param nombre Asigna nombre.
     * @param email Correo electrónico para recibir confirmaciones.
     */
    public Estudiante(String nombre, String email) {
        this.nombre = nombre;
        //Se asigna una matrícula única de 6 dígitos.
        this.matricula = UUID.randomUUID().toString().substring(0, 6).toUpperCase();
        this.email = email;
    }

    /**
     * Getter de nombre.
     * @return Nombre del estudiante.
     */
    public String getNombre() {
        return this.nombre;
    }

    /**
     * Setter de nombre.
     * @param nombre Nuevo nombre del estudiante.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
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
     * Setter del email.
     * @param email Nuevo correo del estudiante.
     */
    public void setEmail(String email) {
        this.email = email;
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

package sistema.reserva.clases.logica;

import sistema.reserva.clases.excepciones.CorreoInvalidoException;
import java.util.UUID;

/**
 * Clase que representa a un estudiante.
 * Este puede tomar clases reservando un horario.
 */
public class Estudiante extends Perfil{
    private final String matricula;

    /**
     * Inicializa la clase con datos básicos del estudiante.
     * @param nombre Asigna nombre.
     * @param email Correo electrónico para recibir confirmaciones.
     */
    public Estudiante(String nombre, String email) throws CorreoInvalidoException {
        super(nombre, email);
        //Se asigna una matrícula única de 6 dígitos.
        this.matricula = UUID.randomUUID().toString().substring(0, 6).toUpperCase();
    }

    /**
     * Getter de la matrícula.
     * @return matrícula del estudiante.
     */
    public String getMatricula() {
        return this.matricula;
    }

    /**
     * ToString de la clase.
     * Útil para imprimir datos del estudiante en pantalla.
     * @return Datos representativos de la clase.
     */
    @Override
    public String toString() {
        return "Matrícula: " + this.matricula + " | Estudiante: " + getNombre() + "\n" +
                "Email: " + getEmail();
    }
}

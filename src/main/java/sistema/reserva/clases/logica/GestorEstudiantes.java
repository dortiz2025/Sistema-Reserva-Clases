package sistema.reserva.clases.logica;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que se encarga de gestionar los perfiles de los estudiantes.
 * Puede crear, modificar o eliminar un perfil.
 */
public class GestorEstudiantes {

    private final List<Estudiante> estudiantes;

    /**
     * Inicializa la clase.
     */
    public GestorEstudiantes(){
        this.estudiantes = new ArrayList<>();
    }

    /**
     * Getter de estudiantes.
     * @return Entrega la lista de estudiantes registrados.
     */
    public List<Estudiante> getEstudiantes(){
        return this.estudiantes;
    }

    /**
     * Adder de estudiante.
     * @param estudiante Nuevo estudiante a registrar.
     */
    public void addEstudiante(Estudiante estudiante){
        this.estudiantes.add(estudiante);
    }

    /**
     * Remover de estudiante.
     * @param estudiante Estudiante a eliminar.
     */
    public void removeEstudiante(Estudiante estudiante){
        this.estudiantes.remove(estudiante);
    }
}

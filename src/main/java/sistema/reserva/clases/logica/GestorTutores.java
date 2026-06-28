package sistema.reserva.clases.logica;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que se encarga de gestionar los perfiles de los tutores.
 * Puede crear, modificar o eliminar un perfil.
 */
public class GestorTutores {

    private final List<Tutor> tutores;

    /**
     * Inicializa la clase.
     */
    public GestorTutores() {
        this.tutores = new ArrayList<>();
    }

    /**
     * Getter de tutores.
     * @return Entrega la lista de tutores inscritos.
     */
    public List<Tutor> getTutores(){
        return this.tutores;
    }

    /**
     * Adder de tutores.
     * @param tutor Nuevo tutor a inscribir en el sistema.
     */
    public void addTutor(Tutor tutor){
        this.tutores.add(tutor);
    }

    /**
     * Remover de tutores.
     * @param tutor Tutor a eliminar.
     */
    public void removeTutor(Tutor tutor){
        this.tutores.remove(tutor);
    }
}

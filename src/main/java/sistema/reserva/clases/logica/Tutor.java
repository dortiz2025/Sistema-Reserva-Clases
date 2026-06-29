package sistema.reserva.clases.logica;
 import java.util.List;
 import java.util.UUID;

/**
 * Clase que representa un tutor.
 * Imparte una materia en un horario definido,
 * con cierta tarifa y límite de estudiantes por clase.
 */
public class Tutor extends Perfil{
    private String id;
    private String materia;

    private List<String> horariosDisponibles;
    private int tarifa;
    private int maxAlum;

    /**
     * Inicializa un tutor con sus datos básicos.
     * @param nombre Nombre del tutor.
     * @param id Identificador único.
     * @param materia Materia que imparte.
     * @param tarifa Tarifa (única tarifa).
     * @param maxAlum Cantidad de alumnos máximos por clase.
     */
    public Tutor(String nombre, String email, String id, String materia, int tarifa, int maxAlum){
        super(nombre, email);
        //Se asigna un ID único de 4 dígitos
        this.id = UUID.randomUUID().toString().substring(0,4).toUpperCase();
        this.materia = materia;
        this.tarifa = tarifa;
        this.maxAlum = maxAlum;
    }

    /**
     * Getter de id.
     * @return Identificador único.
     */
    public String getId(){
        return this.id;
    }

    /**
     * Getter de materia.
     * @return Materia que imparte el profesor.
     */
    public String getMateria(){
        return this.materia;
    }

    /**
     * Getter de horariosDisponibles.
     * @return Horarios disponibles para reservar.
     */
    public List<String> getHorariosDisponibles(){
        return this.horariosDisponibles;
    }

    /**
     * Getter de maxAlum.
     * @return Cupos máximos por clase.
     */
    public int getMaxAlum() {
        return maxAlum;
    }

    /**
     * Getter de tarifa.
     * @return Tarifa única.
     */
    public int getTarifa() {
        return tarifa;
    }

    /**
     * Adder de horariosDisponibles.
     * @param horario Nuevo horario disponible para reservar.
     */
    public void addHorariosDisponibles(String horario){
        this.horariosDisponibles.add(horario);
    }

    /**
     * Setter de tarifa.
     * @param tarifa Tarifa única.
     */
    public void setTarifa(int tarifa){
        this.tarifa = tarifa;
    }

    /**
     * Setter de maxAlum.
     * @param maxAlum Cupos máximos por clase.
     */
    public void setMaxAlum(int maxAlum){
        this.maxAlum = maxAlum;
    }

    /**
     * ToString de la clase.
     * Útil para imprimir datos del tutor en pantalla.
     * @return Datos representativos de la clase.
     */
    @Override
    public String toString() {
        return "Tutor{" +
                "nombre='" + getNombre() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", id='" + id + '\'' +
                ", materia='" + materia + '\'' +
                ", horariosDisponibles=" + horariosDisponibles +
                ", tarifa=" + tarifa +
                ", maxAlum=" + maxAlum +
                '}';
    }
}

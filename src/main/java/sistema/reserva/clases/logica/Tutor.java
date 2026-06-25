package sistema.reserva.clases.logica;
 import java.util.List;

/**
 * Clase que representa un tutor.
 * Imparte una materia en un horario definido,
 * con cierta tarifa y límite de estudiantes por clase.
 */
public class Tutor {
    private String nombre;
    private String id;
    private String materia;

    private List<String> horariosDisp;
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
    public Tutor(String nombre, String id, String materia, int tarifa, int maxAlum){
        this.nombre = nombre;
        this.id = id;
        this.materia = materia;
        this.tarifa = tarifa;
        this.maxAlum = maxAlum;
    }

    /**
     * Getter de nombre.
     * @return nombre del tutor.
     */
    public String getNombre(){
        return this.nombre;
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
     * Getter de horariosDisp.
     * @return Horarios disponibles para reservar.
     */
    public List<String> getHorariosDisp(){
        return this.horariosDisp;
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
     * Adder de horariosDisp.
     * @param horario Nuevo horario disponible para reservar.
     */
    public void addHorariosDisp(String horario){
        this.horariosDisp.add(horario);
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
}

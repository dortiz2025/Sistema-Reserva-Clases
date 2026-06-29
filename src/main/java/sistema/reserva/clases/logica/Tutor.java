package sistema.reserva.clases.logica;
 import java.util.List;
 import java.util.UUID;

/**
 * Clase que representa un tutor.
 * Imparte una materia en un horario definido,
 * con cierta tarifa y límite de estudiantes por clase.
 */
public class Tutor extends Perfil{
    private final String id;
    private int tarifa;
    private int maxAlum;
    private List<String> materias;
    private List<BloqueHorario> horariosDisponibles;

    /**
     * Inicializa un tutor con sus datos básicos.
     * @param nombre Nombre del tutor.
     * @param email Correo Electrónico del tutor.
     * @param tarifa Tarifa (única tarifa).
     * @param maxAlum Cantidad de alumnos máximos por clase.
     */
    public Tutor(String nombre, String email, int tarifa, int maxAlum){
        super(nombre, email);
        //Se asigna un ID único de 4 dígitos
        this.id = UUID.randomUUID().toString().substring(0,4).toUpperCase();
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
     * Getter de tarifa.
     * @return Tarifa única.
     */
    public int getTarifa() {
        return tarifa;
    }

    /**
     * Setter de tarifa.
     * @param tarifa Tarifa única.
     */
    public void setTarifa(int tarifa){
        this.tarifa = tarifa;
    }

    /**
     * Getter de maxAlum.
     * @return Cupos máximos por clase.
     */
    public int getMaxAlum() {
        return maxAlum;
    }

    /**
     * Setter de maxAlum.
     * @param maxAlum Cupos máximos por clase.
     */
    public void setMaxAlum(int maxAlum){
        this.maxAlum = maxAlum;
    }

    /**
     * Getter de materias.
     * @return Materias que imparte el profesor.
     */
    public List<String> getMaterias(){
        return this.materias;
    }

    /**
     * Adder de materias.
     * @param materia Nueva materia que imparte el tutor.
     */
    public void addMateria(String materia){
        this.materias.add(materia);
    }

    /**
     * Remover de materia.
     * @param materia Materia a eliminar.
     */
    public void removeMateria(String materia){
        this.materias.remove(materia);
    }

    /**
     * Getter de horariosDisponibles.
     * @return Horarios disponibles para reservar.
     */
    public List<BloqueHorario> getHorariosDisponibles(){
        return this.horariosDisponibles;
    }

    /**
     * Adder de horariosDisponibles.
     * @param horario Nuevo horario disponible para reservar.
     */
    public void addHorariosDisponibles(BloqueHorario horario){
        this.horariosDisponibles.add(horario);
    }

    /**
     * Remover de horariosDisponibles.
     * @param horario Horario a eliminar.
     */
    public void removeHorariosDisponibles(BloqueHorario horario){
        this.horariosDisponibles.remove(horario);
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
                ", materia='" + materias + '\'' +
                ", horariosDisponibles=" + horariosDisponibles +
                ", tarifa=" + tarifa +
                ", maxAlum=" + maxAlum +
                '}';
    }
}

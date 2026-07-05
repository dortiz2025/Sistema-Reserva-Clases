package sistema.reserva.clases.logica;

import sistema.reserva.clases.excepciones.CorreoInvalidoException;
import sistema.reserva.clases.logica.bloquehorario.BloqueHorario;

import java.util.*;

/**
 * Clase que representa un tutor.
 * Imparte una materia en un horario definido,
 * con cierta tarifa y límite de estudiantes por clase.
 */
public class Tutor extends Perfil{
    private final String id;
    private int tarifa;
    private int cupoMaximo;
    private final List<String> materias;
    private final List<BloqueHorario> horariosDisponibles;

    /**
     * Inicializa un tutor con sus datos básicos.
     * @param nombre Nombre del tutor.
     * @param email Correo Electrónico del tutor.
     * @param tarifa Tarifa (única tarifa).
     * @param cupoMaximo Cantidad de alumnos máximos por clase.
     */
    public Tutor(String nombre, String email, int tarifa, int cupoMaximo) throws CorreoInvalidoException {
        super(nombre, email);
        //Se asigna un ID único de 4 dígitos
        this.id = UUID.randomUUID().toString().substring(0,4).toUpperCase();
        validarEnteroPositivo(tarifa);
        this.tarifa = tarifa;
        validarEnteroPositivo(cupoMaximo);
        this.cupoMaximo = cupoMaximo;
        this.materias = new ArrayList<>();
        this.horariosDisponibles = new ArrayList<>();
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
        validarEnteroPositivo(tarifa);
        this.tarifa = tarifa;
    }

    /**
     * Getter de cupoMaximo.
     * @return Cupo máximo por clase.
     */
    public int getCupoMaximo() {
        return cupoMaximo;
    }

    /**
     * Setter de cupoMaximo.
     * @param cupoMaximo Cupos máximos por clase.
     */
    public void setCupoMaximo(int cupoMaximo){
        validarEnteroPositivo(cupoMaximo);
        this.cupoMaximo = cupoMaximo;
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
    public void addMateria(String materia) {
        if (materias.contains(materia)) {
            throw new IllegalStateException("La materia ingresada ya se encuentra registrada.");
        } else{
            this.materias.add(materia);
        }
    }

    /**
     * Remover de materias.
     * @param materia Materia a eliminar.
     * @return Boolean que depende de si la eliminación fue exitosa o no.
     */
    public boolean removeMateria(String materia) {
        return this.materias.remove(materia);
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
    public void addHorarioDisponible(BloqueHorario horario){
        if (horario == null) {
            throw new IllegalArgumentException("No se puede agregar un bloque de horario nulo.");
        } else if (this.horariosDisponibles.contains(horario)) {
            throw new IllegalStateException("El bloque de horario ingresado ya está registrado.");
        } else {
            this.horariosDisponibles.add(horario);
        }
    }

    /**
     * Remover de horariosDisponibles.
     * @param horario Horario a eliminar.
     * @return Booleano dependiendo si la eliminación fue exitosa o no.
     */
    public boolean removeHorarioDisponible(BloqueHorario horario){
        if (horario == null) throw new IllegalArgumentException("No se puede quitar un horario nulo.");
        return this.horariosDisponibles.remove(horario);
    }

    //Lógica auxiliar para poder verificar que un número es entero.
    private void validarEnteroPositivo(int entero){
        if (entero <= 0) throw new IllegalArgumentException("El número ingresado debe ser mayor a 0");
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
                ", materias='" + materias + '\'' +
                ", horariosDisponibles=" + horariosDisponibles +
                ", tarifa=" + tarifa +
                ", cupos=" + cupoMaximo +
                '}';
    }
}

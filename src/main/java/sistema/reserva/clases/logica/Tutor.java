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
        return this.tarifa;
    }

    /**
     * Setter de tarifa.
     * @param tarifa Tarifa única.
     */
    protected void setTarifa(int tarifa){
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
    protected void setCupoMaximo(int cupoMaximo){
        validarEnteroPositivo(cupoMaximo);
        this.cupoMaximo = cupoMaximo;
    }

    /**
     * Getter de materias.
     * @return Materias que imparte el profesor.
     */
    public List<String> getMaterias(){
        return Collections.unmodifiableList(this.materias); //Protege la lista original.
    }

    /**
     * Adder de materias.
     * @param materia Nueva materia que imparte el tutor.
     */
    protected void addMateria(String materia) {
        if (materia == null || materia.trim().isEmpty()) {
            throw new IllegalArgumentException("La materia no puede estar vacía.");
        }
        boolean yaExiste = materias.stream().anyMatch(m -> m.equalsIgnoreCase(materia));
        if (yaExiste) {
            throw new IllegalStateException("La materia ingresada ya se encuentra registrada.");
        } else {
            this.materias.add(materia);
        }
    }

    /**
     * Remover de materias.
     * @param materia Materia a eliminar.
     */
    protected void removeMateria(String materia) {
        this.materias.removeIf(m -> m.equalsIgnoreCase(materia));
    }

    /**
     * Getter de horariosDisponibles.
     * @return Horarios disponibles para reservar.
     */
    public List<BloqueHorario> getHorariosDisponibles(){
        return Collections.unmodifiableList(this.horariosDisponibles); //Protege la lista original.
    }

    /**
     * Adder de horariosDisponibles.
     * @param horario Nuevo horario disponible para reservar.
     */
    protected void addHorarioDisponible(BloqueHorario horario){
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
     */
    protected void removeHorarioDisponible(BloqueHorario horario){
        if (horario == null) throw new IllegalArgumentException("No se puede quitar un horario nulo.");
        this.horariosDisponibles.remove(horario);
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
        return "ID: " + this.id + " | Tutor: " + getNombre() + "\n" +
                "Email: " + getEmail() + "\n" +
                "Tarifa: $" + this.tarifa + " | Cupo Máximo: " + this.cupoMaximo + " alumnos\n" +
                "Materias: " + (this.materias.isEmpty() ? "Ninguna registrada" : this.materias) + "\n" +
                "Horarios: " + (this.horariosDisponibles.isEmpty() ? "Ninguno registrado" : this.horariosDisponibles);
    }
}

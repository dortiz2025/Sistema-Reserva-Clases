package sistema.reserva.clases.logica;

public class Estudiante {
    private String nombre;
    private String matricula;

    private int presupuesto;

    public Estudiante(String nombre, String matricula){

        this.nombre = nombre;
        this.matricula = matricula;
    }

    public void setPresupuesto(int presupuesto) {
        this.presupuesto = presupuesto;
    }

    public String getNombre() {
        return nombre;
    }

    public String getMatricula() {
        return matricula;
    }

    public int getPresupuesto() {
        return presupuesto;
    }

    @Override
    public String toString() {
        return nombre  + " | #" + matricula + " | $" + presupuesto;
    }
}

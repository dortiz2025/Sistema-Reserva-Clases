package sistema.reserva.clases.logica;

import java.util.List;

public class Estudiante {
    private String nombre;
    private String matricula;

    private String materiaDeseada;
    private List<String> horarioDisp;
    private int presupuesto;

    public Estudiante(String nombre, String matricula){

        this.nombre = nombre;
        this.matricula = matricula;
    }


    public void setMateriaDeseada(String materiaDeseada) {
        this.materiaDeseada = materiaDeseada;
    }

    public void setHorarioDisp(List<String> horarioDisp) {
        this.horarioDisp = horarioDisp;
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

    public String getMateriaDeseada() {
        return materiaDeseada;
    }

    public List<String> getHorarioDisp() {
        return horarioDisp;
    }

    public int getPresupuesto() {
        return presupuesto;
    }

    @Override
    public String toString() {
        return nombre  + " | #" + matricula + " | $" + presupuesto;
    }
}

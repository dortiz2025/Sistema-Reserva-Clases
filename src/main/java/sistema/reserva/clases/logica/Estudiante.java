package sistema.reserva.clases.logica;

import java.util.List;

public class Estudiante {
    private String nombre;
    private String apellidos;
    private String email;

    private String materiaDeseada;
    private List<String> horarioDisp;
    private int presupuesto;

    public Estudiante(String nombre, String apellidos, String email){
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;

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

}

package sistema.reserva.clases.logica;
 import java.util.List;

public class Tutor {
    private String nombre;
    private String apellidos;
    private String email;
    private String materia;
    private String horariosDisp;
    private int tarifa;
    private int maxAlum;

    public Tutor(String nombre, String apellidos, String email, String materia){

        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.materia = materia;
    }


    public void setHorariosDisp(String horariosDisp){
        this.horariosDisp = horariosDisp;
    }

    public void setTarifa(int tarifa){
        this.tarifa = tarifa;
    }

    public void setMaxAlum(int maxAlum){
        this.maxAlum = maxAlum;
    }

    public String getNombre(){
        return this.nombre;
    }

    public String getApellidos(){
        return this.apellidos;
    }

    public String getEmail(){
        return this.email;
    }

    public String getMateria(){
        return this.materia;
    }

    public String getHorariosDisp(){
        return this.horariosDisp;
    }

    public int getMaxAlum() {
        return maxAlum;
    }

    public int getTarifa() {
        return tarifa;
    }
}

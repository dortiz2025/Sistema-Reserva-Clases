package sistema.reserva.clases.logica;
import java.util.Objects;

/**
 * Clase que representa un horario semanal.
 * Tiene día de semana y bloque que corresponde
 * a un intervalo de tiempo en el día.
 */
public class BloqueHorario {
    private DiaSemana dia;
    private int bloque;

    public BloqueHorario(DiaSemana dia, int bloque) {
        this.dia = dia;
        this.bloque = bloque;
    }

    public DiaSemana getDia() {
        return dia;
    }

    public int getBloque() {
        return bloque;
    }

    @Override
    public boolean equals(Object objeto) {
        if (this == objeto) return true;
        if (objeto == null || getClass() != objeto.getClass()) return false;
        BloqueHorario bloqueHorario = (BloqueHorario) objeto;
        return this.bloque == bloqueHorario.bloque && this.dia == bloqueHorario.dia;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dia, bloque);
    }

    @Override
    public String toString() {
        return dia + " - Bloque " + bloque;
    }
}
package sistema.reserva.clases.logica.estrategias;
import sistema.reserva.clases.logica.Tutor;

public class FiltroTutorPorMateria implements FiltrarStrategy<Tutor> {
    private final String materiaBuscada;

    public FiltroTutorPorMateria(String materia) {
        this.materiaBuscada = materia;
    }

    @Override
    public boolean cumpleCondicion(Tutor tutor) {
        return tutor.getMaterias().contains(materiaBuscada);
    }
}
package sistema.reserva.clases.logica.estrategias;
import sistema.reserva.clases.logica.Perfil;

public class FiltroPorNombre<T extends Perfil> implements FiltrarStrategy<T> {
    private final String nombreBuscado;

    public FiltroPorNombre(String nombre) {
        this.nombreBuscado = nombre.toLowerCase();
    }

    @Override
    public boolean cumpleCondicion(T item) {
        return item.getNombre().toLowerCase().contains(nombreBuscado);
    }
}
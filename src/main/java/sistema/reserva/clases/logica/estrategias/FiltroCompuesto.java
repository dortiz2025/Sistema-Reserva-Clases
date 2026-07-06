package sistema.reserva.clases.logica.estrategias;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que permite agrupar múltiples estrategias
 * de filtro como si fueran una sola.
 * Esto permite añadir filtros en tiempo de ejecución y no tener
 * que crear infinidad de clases.
 * @param <T> El tipo de objeto a evaluar (Tutor o Estudiante).
 */
public class FiltroCompuesto<T> implements FiltrarStrategy<T> {
    private final List<FiltrarStrategy<T>> estrategiasActivas;

    /**
     * Inicializa la clase
     */
    public FiltroCompuesto() {
        this.estrategiasActivas = new ArrayList<>();
    }

    /**
     * Permite añadir filtros en tiempo de ejecución.
     */
    public void agregarFiltro(FiltrarStrategy<T> estrategia) {
        this.estrategiasActivas.add(estrategia);
    }

    /**
     * Evalúa si un objeto cumple con las condiciones del filtro.
     */
    @Override
    public boolean cumpleCondicion(T item) {
        // Si no hay filtros activos, el objeto pasa automáticamente.
        if (estrategiasActivas.isEmpty()) {
            return true;
        }

        // Se verifica que el objeto cumpla con todas las condiciones.
        for (FiltrarStrategy<T> estrategia : estrategiasActivas) {
            if (!estrategia.cumpleCondicion(item)) {
                return false; // Si no cumple al menos una condición, no pasa.
            }
        }
        return true;
    }
}
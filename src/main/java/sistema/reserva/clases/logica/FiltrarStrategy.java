package sistema.reserva.clases.logica;

/**
 * Interfaz genérica para el patrón Strategy.
 * Permite definir múltiples algoritmos de filtrado.
 * @param <T> El tipo de objeto a evaluar.
 */
public interface FiltrarStrategy<T> {
    boolean cumpleCondicion(T item);
}
package sistema.reserva.clases.logica.estrategias;

/**
 * Interfaz genérica para el patrón Strategy.
 * Permite definir múltiples algoritmos de filtrado.
 * @param <T> El tipo de objeto a evaluar.
 */
public interface FiltrarStrategy<T> {

    /**
     * Verifica si se cumple la condición.
     * @param item Objeto
     * @return True si cumple la condición, de lo contrario, False.
     */
    boolean cumpleCondicion(T item);
}
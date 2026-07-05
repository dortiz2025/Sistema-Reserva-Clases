package sistema.reserva.clases.logica.reserva;

/**
 * Enum de los nombres de estados posibles para una reserva.
 */
public enum NombreEstado {
    PENDIENTE("Pendiente"),
    COMPLETADA("Completada"),
    CANCELADA("Cancelada");

    private final String nombre;

    NombreEstado(String descripcion) {
        this.nombre = descripcion;
    }

    @Override
    public String toString() {
        return this.nombre;
    }
}
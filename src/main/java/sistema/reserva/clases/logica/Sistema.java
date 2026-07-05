package sistema.reserva.clases.logica;

/**
 * Clase que funciona como una interfaz unificada (Patrón Facade).
 * Aquí se conectan los gestores y la lógica interna de todas las clases
 * para poder entregar un sistema de reserva de clases.
 */
public class Sistema {
    private final GestorTutores gestorTutores;
    private final GestorEstudiantes gestorEstudiantes;
    private final GestorReservas gestorReservas;

    public  Sistema() {
        gestorTutores = new GestorTutores();
        gestorEstudiantes = new GestorEstudiantes();
        gestorReservas = new GestorReservas();
    }

}

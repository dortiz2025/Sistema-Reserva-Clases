package sistema.reserva.clases.excepciones;

public class EstudianteYaRegistradoException extends Exception {
    public EstudianteYaRegistradoException(String mensaje) {
        super(mensaje);
    }
}
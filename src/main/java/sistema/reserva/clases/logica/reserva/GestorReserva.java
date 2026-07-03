package sistema.reserva.clases.logica.reserva;

import sistema.reserva.clases.excepciones.CupoExcedidoException;
import sistema.reserva.clases.excepciones.EstudianteYaInscritoException;
import sistema.reserva.clases.logica.BloqueHorario;
import sistema.reserva.clases.logica.Estudiante;
import sistema.reserva.clases.logica.Tutor;

import java.util.ArrayList;
import java.util.List;

public class GestorReserva{
public class GestorReserva {
    private List<Reserva> listaReservas;

    public GestorReserva() {
        this.listaReservas = new ArrayList<>();
    }

}

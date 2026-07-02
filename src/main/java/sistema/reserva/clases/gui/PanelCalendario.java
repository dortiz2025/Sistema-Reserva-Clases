package sistema.reserva.clases.gui;

import javax.swing.*;
import java.awt.*;

public class PanelCalendario extends JPanel{

    public PanelCalendario() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        //visualización del Calendario
        JPanel panelLista = new JPanel(new BorderLayout());
        panelLista.setBorder(BorderFactory.createTitledBorder("Calendario de Reservas Activas"));
        JTextArea txtArea = new JTextArea();
        txtArea.setEditable(false);
        JButton btnActualizar = new JButton("Actualizar Calendario");

        btnActualizar.addActionListener(e -> {
            //aqui se espera que se consulte la lista de reservas al administrador y se muestre en txtArea
        });

        panelLista.add(new JScrollPane(txtArea), BorderLayout.CENTER);
        panelLista.add(btnActualizar, BorderLayout.SOUTH);

        //cancelacion de reserva
        JPanel panelCancelar = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelCancelar.setBorder(BorderFactory.createTitledBorder("Modificar/Cancelar Reserva"));
        JTextField txtIdentificador = new JTextField(20);
        JButton btnCancelar = new JButton("Cancelar Reserva");
        btnCancelar.addActionListener(e -> {
            String idReserva = txtIdentificador.getText();
            //aqui se espera la llamada a admin.cancelarReserva(idReserva)
        });

        panelCancelar.add(new JLabel("Datos de Reserva (Estudiante-Horario):"));
        panelCancelar.add(txtIdentificador);
        panelCancelar.add(btnCancelar);

        add(panelLista, BorderLayout.CENTER);
        add(panelCancelar, BorderLayout.SOUTH);
    }
}

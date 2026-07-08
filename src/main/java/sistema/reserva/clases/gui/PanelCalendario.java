package sistema.reserva.clases.gui;

import sistema.reserva.clases.logica.Sistema;
import sistema.reserva.clases.logica.reserva.Reserva;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PanelCalendario extends JPanel {

    public PanelCalendario(Sistema sistema, JLabel lblEstado) {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        //visualización del Calendario
        JPanel panelLista = new JPanel(new BorderLayout());
        panelLista.setBorder(BorderFactory.createTitledBorder("Calendario de Reservas Activas e Historial"));
        JTextArea txtArea = new JTextArea();
        txtArea.setEditable(false);
        JButton btnActualizar = new JButton("Actualizar Calendario");

        btnActualizar.addActionListener(e -> {
            try {
                // Se solicita la lista completa al Facade (Sistema)
                List<Reserva> reservas = sistema.obtenerCalendarioCompleto();
                StringBuilder sb = new StringBuilder();

                if (reservas.isEmpty()) {
                    sb.append("No hay reservas registradas en el sistema.");
                } else {
                    for (Reserva r : reservas) {
                        // El toString() de la reserva debería mostrar toda la info, incluyendo su Estado (Pendiente/Cancelada)
                        sb.append(r.toString()).append("\n");
                    }
                }

                txtArea.setText(sb.toString());
                lblEstado.setText("  Estado: calendario actualizado correctamente");
            } catch (Exception ex) {
                lblEstado.setText("  Error: " + ex.getMessage());
            }
        });

        panelLista.add(new JScrollPane(txtArea), BorderLayout.CENTER);
        panelLista.add(btnActualizar, BorderLayout.SOUTH);

        //cancelacion de reserva usando el patron State de Daniel
        JPanel panelCancelar = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelCancelar.setBorder(BorderFactory.createTitledBorder("Modificar/Cancelar Reserva"));
        JTextField txtIdentificador = new JTextField(20);
        JButton btnCancelar = new JButton("Cancelar Reserva");

        btnCancelar.addActionListener(e -> {
            try {
                String idReserva = txtIdentificador.getText();
                // Al llamar a este método, la lógica interna aplicará el Patrón State
                sistema.cancelarReserva(idReserva);

                lblEstado.setText("  Estado: Reserva " + idReserva + " cancelada exitosamente");
                txtIdentificador.setText("");

                // Hacemos clic automático en actualizar para que el usuario vea el cambio de estado de inmediato
                btnActualizar.doClick();

            } catch (IllegalStateException ex) {
                // Capturamos el error específico del patrón State (ej: intentar cancelar algo ya cancelado)
                lblEstado.setText("  Error de Estado: " + ex.getMessage());
            } catch (Exception ex) {
                lblEstado.setText("  Error: " + ex.getMessage());
            }
        });

        panelCancelar.add(new JLabel("ID de la Reserva:"));
        panelCancelar.add(txtIdentificador);
        panelCancelar.add(btnCancelar);

        add(panelLista, BorderLayout.CENTER);
        add(panelCancelar, BorderLayout.SOUTH);
    }
}
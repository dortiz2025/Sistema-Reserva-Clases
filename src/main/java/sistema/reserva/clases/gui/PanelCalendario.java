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

        //visualización del calendario y filtros
        JPanel panelLista = new JPanel(new BorderLayout());
        panelLista.setBorder(BorderFactory.createTitledBorder("Calendario de Reservas Activas e Historial"));

        JPanel panelFiltros = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JComboBox<String> comboFiltro = new JComboBox<>(new String[]{"Todos", "Por Estudiante (Matricula)", "Por Tutor (ID)"});
        JTextField txtFiltro = new JTextField(15);
        JButton btnActualizar = new JButton("Buscar / Actualizar");
        panelFiltros.add(new JLabel("Filtro:")); panelFiltros.add(comboFiltro);
        panelFiltros.add(txtFiltro); panelFiltros.add(btnActualizar);

        JTextArea txtArea = new JTextArea();
        txtArea.setEditable(false);
        txtArea.setLineWrap(true);
        txtArea.setWrapStyleWord(true);

        btnActualizar.addActionListener(e -> {
            try {
                List<Reserva> reservas;
                int seleccion = comboFiltro.getSelectedIndex();
                String query = txtFiltro.getText().trim();

                if (seleccion == 1 && !query.isEmpty()) {
                    reservas = sistema.obtenerCalendarioEstudiante(query);
                } else if (seleccion == 2 && !query.isEmpty()) {
                    reservas = sistema.obtenerCalendarioTutor(query);
                } else {
                    reservas = sistema.obtenerCalendarioCompleto();
                }

                StringBuilder sb = new StringBuilder();
                if (reservas.isEmpty()) {
                    sb.append("No hay reservas para mostrar.");
                } else {
                    for (Reserva r : reservas) {
                        sb.append(r.toString()).append("\n\n");
                    }
                }
                txtArea.setText(sb.toString());
                lblEstado.setText("  Estado: calendario actualizado");
            } catch (Exception ex) {
                lblEstado.setText("  Error: " + ex.getMessage());
            }
        });

        panelLista.add(panelFiltros, BorderLayout.NORTH);
        panelLista.add(new JScrollPane(txtArea), BorderLayout.CENTER);

        //gestion de reservas
        JPanel panelGestion = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelGestion.setBorder(BorderFactory.createTitledBorder("Gestionar Reserva"));
        JTextField txtIdentificador = new JTextField(15);
        JButton btnCompletar = new JButton("Completar (Realizada)");
        JButton btnCancelar = new JButton("Cancelar Reserva");

        btnCompletar.addActionListener(e -> {
            try {
                sistema.completarReserva(txtIdentificador.getText());
                lblEstado.setText("  Estado: reserva completada exitosamente");
                btnActualizar.doClick();
            } catch (Exception ex) { lblEstado.setText("  Error: " + ex.getMessage()); }
        });

        btnCancelar.addActionListener(e -> {
            try {
                sistema.cancelarReserva(txtIdentificador.getText());
                lblEstado.setText("  Estado: reserva cancelada exitosamente");
                btnActualizar.doClick();
            } catch (Exception ex) { lblEstado.setText("  Error: " + ex.getMessage()); }
        });

        panelGestion.add(new JLabel("ID Reserva:"));
        panelGestion.add(txtIdentificador);
        panelGestion.add(btnCompletar);
        panelGestion.add(btnCancelar);

        add(panelLista, BorderLayout.CENTER);
        add(panelGestion, BorderLayout.SOUTH);
    }
}
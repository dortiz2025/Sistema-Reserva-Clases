package sistema.reserva.clases.gui;

import sistema.reserva.clases.logica.Sistema;
import sistema.reserva.clases.logica.Tutor;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PanelTutores extends JPanel{
    public PanelTutores(Sistema sistema, JLabel lblEstado) {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        //registro
        JPanel panelRegistro = new JPanel(new GridBagLayout());
        panelRegistro.setBorder(BorderFactory.createTitledBorder("Registrar Tutor"));
        JPanel form = new JPanel(new GridLayout(6, 2, 10, 20));

        JTextField txtNombre = new JTextField(15);
        JTextField txtEmail = new JTextField(15);
        JTextField txtTarifa = new JTextField(15);
        JTextField txtCupos = new JTextField(15);
        JButton btnGuardar = new JButton("Registrar");

        btnGuardar.addActionListener(e -> {
            try {
                String nombre = txtNombre.getText();
                String email = txtEmail.getText();
                int tarifa = Integer.parseInt(txtTarifa.getText());
                int cupos = Integer.parseInt(txtCupos.getText());

                //logica para registrar
                String idGen = sistema.registrarTutor(nombre, email, tarifa, cupos);
                lblEstado.setText("  Estado: tutor registrado exitosamente con id " + idGen);

                txtNombre.setText("");
                txtEmail.setText("");
                txtTarifa.setText("");
                txtCupos.setText("");
            } catch (NumberFormatException ex) {
                lblEstado.setText("  Error: tarifa y cupos deben ser numeros");
            } catch (Exception ex) {
                lblEstado.setText("  Error: " + ex.getMessage());
            }
        });

        form.add(new JLabel("Nombre:")); form.add(txtNombre);
        form.add(new JLabel("Email:")); form.add(txtEmail);
        form.add(new JLabel("Tarifa ($):")); form.add(txtTarifa);
        form.add(new JLabel("Cupos:")); form.add(txtCupos);
        form.add(new JLabel("")); form.add(btnGuardar);
        panelRegistro.add(form);

        //visualizacion
        JPanel panelLista = new JPanel(new BorderLayout());
        panelLista.setBorder(BorderFactory.createTitledBorder("Tutores Registrados"));
        JTextArea txtAreaTutores = new JTextArea();
        txtAreaTutores.setEditable(false);
        JButton btnActualizarLista = new JButton("Actualizar Lista");
        btnActualizarLista.addActionListener(e -> {
            try {
                // se pide la lista de tutores al sistema
                List<Tutor> lista = sistema.obtenerTutores();
                StringBuilder sb = new StringBuilder();
                for (Tutor tut : lista) {
                    sb.append(tut.toString()).append("\n");
                }
                txtAreaTutores.setText(sb.toString());
                lblEstado.setText("  Estado: lista de tutores actualizada");
            } catch (Exception ex) {
                lblEstado.setText("  Error: " + ex.getMessage());
            }
        });
        panelLista.add(new JScrollPane(txtAreaTutores), BorderLayout.CENTER);
        panelLista.add(btnActualizarLista, BorderLayout.SOUTH);

        //eliminación
        JPanel panelEliminar = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelEliminar.setBorder(BorderFactory.createTitledBorder("Eliminar Perfil"));
        JTextField txtIdEliminar = new JTextField(15);
        JButton btnEliminar = new JButton("Eliminar por ID");

        btnEliminar.addActionListener(e -> {
            try {
                String id = txtIdEliminar.getText();
                // se elimina mediante id
                sistema.eliminarTutor(id);
                lblEstado.setText("  Estado: tutor eliminado correctamente");
                txtIdEliminar.setText("");
            } catch (Exception ex) {
                lblEstado.setText("  Error: " + ex.getMessage());
            }
        });

        panelEliminar.add(new JLabel("ID del Tutor:"));
        panelEliminar.add(txtIdEliminar);
        panelEliminar.add(btnEliminar);

        add(panelRegistro, BorderLayout.WEST);
        add(panelLista, BorderLayout.CENTER);
        add(panelEliminar, BorderLayout.SOUTH);
    }
}
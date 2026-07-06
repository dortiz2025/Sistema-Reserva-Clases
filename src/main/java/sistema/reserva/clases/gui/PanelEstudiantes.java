package sistema.reserva.clases.gui;

import sistema.reserva.clases.logica.Sistema;
import sistema.reserva.clases.logica.Estudiante;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PanelEstudiantes extends JPanel{

    public PanelEstudiantes(Sistema sistema, JLabel lblEstado) {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        //registro
        JPanel panelRegistro = new JPanel(new GridBagLayout());
        panelRegistro.setBorder(BorderFactory.createTitledBorder("Registrar Estudiante"));
        JPanel form = new JPanel(new GridLayout(5, 2, 10, 20));

        JTextField txtNombre = new JTextField(15);
        JTextField txtEmail = new JTextField(15);
        JButton btnGuardar = new JButton("Registrar");

        btnGuardar.addActionListener(e -> {
            try {
                String nombre = txtNombre.getText();
                String email = txtEmail.getText();
                String matriculaGen = sistema.registrarEstudiante(nombre, email);
                lblEstado.setText("  Estado: estudiante registrado exitosamente con matricula " + matriculaGen);
                txtNombre.setText("");
                txtEmail.setText("");
            } catch (Exception ex) {
                lblEstado.setText("  Error: " + ex.getMessage());
            }
        });

        form.add(new JLabel("Nombre:")); form.add(txtNombre);
        form.add(new JLabel("Email:")); form.add(txtEmail);
        form.add(new JLabel("")); form.add(new JLabel(""));
        form.add(new JLabel("")); form.add(btnGuardar);
        panelRegistro.add(form);

        //visualizacion
        JPanel panelLista = new JPanel(new BorderLayout());
        panelLista.setBorder(BorderFactory.createTitledBorder("Estudiantes Registrados"));
        JTextArea txtAreaEstudiantes = new JTextArea();
        txtAreaEstudiantes.setEditable(false);
        JButton btnActualizarLista = new JButton("Actualizar Lista");

        btnActualizarLista.addActionListener(e -> {
            try {
                // consulta de lista al backend
                List<Estudiante> lista = sistema.obtenerEstudiantes();
                StringBuilder sb = new StringBuilder();
                for (Estudiante est : lista) {
                    sb.append(est.toString()).append("\n");
                }
                txtAreaEstudiantes.setText(sb.toString());
                lblEstado.setText("  Estado: lista de estudiantes actualizada");
            } catch (Exception ex) {
                lblEstado.setText("  Error: " + ex.getMessage());
            }
        });

        panelLista.add(new JScrollPane(txtAreaEstudiantes), BorderLayout.CENTER);
        panelLista.add(btnActualizarLista, BorderLayout.SOUTH);

        //eliminación
        JPanel panelEliminar = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelEliminar.setBorder(BorderFactory.createTitledBorder("Eliminar Perfil"));
        JTextField txtMatriculaEliminar = new JTextField(15);
        JButton btnEliminar = new JButton("Eliminar por Matrícula");

        btnEliminar.addActionListener(e -> {
            try {
                String matricula = txtMatriculaEliminar.getText();
                // se pide eliminar al sistema
                sistema.eliminarEstudiante(matricula);
                lblEstado.setText("  Estado: estudiante eliminado correctamente");
                txtMatriculaEliminar.setText("");
            } catch (Exception ex) {
                lblEstado.setText("  Error: " + ex.getMessage());
            }
        });

        panelEliminar.add(new JLabel("Matrícula:"));
        panelEliminar.add(txtMatriculaEliminar);
        panelEliminar.add(btnEliminar);

        add(panelRegistro, BorderLayout.WEST);
        add(panelLista, BorderLayout.CENTER);
        add(panelEliminar, BorderLayout.SOUTH);
    }
}
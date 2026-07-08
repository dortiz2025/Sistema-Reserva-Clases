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

        JTabbedPane pestanasHerramientas = new JTabbedPane();

        //registro
        JPanel panelRegistro = new JPanel(new GridLayout(5, 2, 10, 20));
        panelRegistro.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JTextField txtNombre = new JTextField(15);
        JTextField txtEmail = new JTextField(15);
        JButton btnGuardar = new JButton("Registrar");

        btnGuardar.addActionListener(e -> {
            try {
                String matriculaGen = sistema.registrarEstudiante(txtNombre.getText(), txtEmail.getText());
                lblEstado.setText("  Estado: estudiante registrado exitosamente con matricula " + matriculaGen);
                txtNombre.setText("");
                txtEmail.setText("");
            } catch (Exception ex) {
                lblEstado.setText("  Error: " + ex.getMessage());
            }
        });

        panelRegistro.add(new JLabel("Nombre:")); panelRegistro.add(txtNombre);
        panelRegistro.add(new JLabel("Email:")); panelRegistro.add(txtEmail);
        panelRegistro.add(new JLabel("")); panelRegistro.add(new JLabel(""));
        panelRegistro.add(new JLabel("")); panelRegistro.add(btnGuardar);

        //modificar
        JPanel panelModificar = new JPanel(new GridLayout(5, 2, 10, 20));
        panelModificar.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JTextField txtMatMod = new JTextField(15);
        JTextField txtNomMod = new JTextField(15);
        JTextField txtEmailMod = new JTextField(15);
        JButton btnModificar = new JButton("Guardar Cambios");

        btnModificar.addActionListener(e -> {
            try {
                sistema.modificarEstudiante(txtMatMod.getText(), txtNomMod.getText(), txtEmailMod.getText());
                lblEstado.setText("  Estado: estudiante modificado exitosamente");
            } catch (Exception ex) {
                lblEstado.setText("  Error: " + ex.getMessage());
            }
        });

        panelModificar.add(new JLabel("Matricula:")); panelModificar.add(txtMatMod);
        panelModificar.add(new JLabel("Nuevo Nombre:")); panelModificar.add(txtNomMod);
        panelModificar.add(new JLabel("Nuevo Email:")); panelModificar.add(txtEmailMod);
        panelModificar.add(new JLabel("")); panelModificar.add(btnModificar);

        pestanasHerramientas.addTab("Registrar", panelRegistro);
        pestanasHerramientas.addTab("Modificar", panelModificar);

        //visualizacion
        JPanel panelLista = new JPanel(new BorderLayout());
        panelLista.setBorder(BorderFactory.createTitledBorder("Estudiantes Registrados"));
        JTextArea txtAreaEstudiantes = new JTextArea();
        txtAreaEstudiantes.setEditable(false);
        txtAreaEstudiantes.setLineWrap(true);
        txtAreaEstudiantes.setWrapStyleWord(true);
        JButton btnActualizarLista = new JButton("Actualizar Lista");

        btnActualizarLista.addActionListener(e -> {
            try {
                List<Estudiante> lista = sistema.obtenerEstudiantes();
                StringBuilder sb = new StringBuilder();
                for (Estudiante est : lista) {
                    sb.append(est.toString()).append("\n\n");
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
                sistema.eliminarEstudiante(txtMatriculaEliminar.getText());
                lblEstado.setText("  Estado: estudiante eliminado correctamente");
                txtMatriculaEliminar.setText("");
                btnActualizarLista.doClick();
            } catch (Exception ex) {
                lblEstado.setText("  Error: " + ex.getMessage());
            }
        });

        panelEliminar.add(new JLabel("Matrícula:"));
        panelEliminar.add(txtMatriculaEliminar);
        panelEliminar.add(btnEliminar);

        add(pestanasHerramientas, BorderLayout.WEST);
        add(panelLista, BorderLayout.CENTER);
        add(panelEliminar, BorderLayout.SOUTH);
    }
}
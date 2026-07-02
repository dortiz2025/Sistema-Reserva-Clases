package sistema.reserva.clases.gui;

import javax.swing.*;
import java.awt.*;

public class PanelEstudiantes extends JPanel{

    public PanelEstudiantes() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        //registro
        JPanel panelRegistro = new JPanel(new GridBagLayout());
        panelRegistro.setBorder(BorderFactory.createTitledBorder("Registrar Estudiante"));
        JPanel form = new JPanel(new GridLayout(5, 2, 10, 20));

        JTextField txtNombre = new JTextField(15);
        JTextField txtMatricula = new JTextField(15);
        JTextField txtPresupuesto = new JTextField(15);
        JButton btnGuardar = new JButton("Registrar");

        btnGuardar.addActionListener(e -> {
            String nombre = txtNombre.getText();
            String matricula = txtMatricula.getText();
            String presupuesto = txtPresupuesto.getText();
            //aqui se espera la llamada a admin.registrarEstudiante(nombre, matricula, presupuesto)
        });

        form.add(new JLabel("Nombre:")); form.add(txtNombre);
        form.add(new JLabel("Matrícula:")); form.add(txtMatricula);
        form.add(new JLabel("Presupuesto ($):")); form.add(txtPresupuesto);
        form.add(new JLabel("")); form.add(btnGuardar);
        panelRegistro.add(form);

        //visualizacion
        JPanel panelLista = new JPanel(new BorderLayout());
        panelLista.setBorder(BorderFactory.createTitledBorder("Estudiantes Registrados"));
        JTextArea txtAreaEstudiantes = new JTextArea();
        txtAreaEstudiantes.setEditable(false);
        JButton btnActualizarLista = new JButton("Actualizar Lista");
        btnActualizarLista.addActionListener(e -> {
            //aqui se espera que se consulte la lista de estudiantes al admin y se muestre
        });
        panelLista.add(new JScrollPane(txtAreaEstudiantes), BorderLayout.CENTER);
        panelLista.add(btnActualizarLista, BorderLayout.SOUTH);

        //eliminación
        JPanel panelEliminar = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelEliminar.setBorder(BorderFactory.createTitledBorder("Eliminar Perfil"));
        JTextField txtMatriculaEliminar = new JTextField(15);
        JButton btnEliminar = new JButton("Eliminar por Matrícula");
        btnEliminar.addActionListener(e -> {
            String matricula = txtMatriculaEliminar.getText();
            //aqui se espera la llamada a admin.eliminarEstudiante(matricula)
        });
        panelEliminar.add(new JLabel("Matrícula:"));
        panelEliminar.add(txtMatriculaEliminar);
        panelEliminar.add(btnEliminar);

        add(panelRegistro, BorderLayout.WEST);
        add(panelLista, BorderLayout.CENTER);
        add(panelEliminar, BorderLayout.SOUTH);
    }
}

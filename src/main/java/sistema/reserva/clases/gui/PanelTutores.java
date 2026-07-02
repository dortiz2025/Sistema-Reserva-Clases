package sistema.reserva.clases.gui;

import javax.swing.*;
import java.awt.*;

public class PanelTutores extends JPanel{
    public PanelTutores() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        //registro
        JPanel panelRegistro = new JPanel(new GridBagLayout());
        panelRegistro.setBorder(BorderFactory.createTitledBorder("Registrar Tutor"));
        JPanel form = new JPanel(new GridLayout(6, 2, 10, 20));

        JTextField txtNombre = new JTextField(15);
        JTextField txtMateria = new JTextField(15);
        JTextField txtTarifa = new JTextField(15);
        JTextField txtCupos = new JTextField(15);
        JButton btnGuardar = new JButton("Registrar");

        btnGuardar.addActionListener(e -> {
            String nombre = txtNombre.getText();
            String materia = txtMateria.getText();
            String tarifa = txtTarifa.getText();
            String cupos = txtCupos.getText();
            //aqui se espera la llamada a admin.registrarTutor(nombre, materia, tarifa, cupos)
        });

        form.add(new JLabel("Nombre:")); form.add(txtNombre);
        form.add(new JLabel("Materia:")); form.add(txtMateria);
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
            //aqui se espera que se consulte la lista de tutores al admin y se muestre
        });
        panelLista.add(new JScrollPane(txtAreaTutores), BorderLayout.CENTER);
        panelLista.add(btnActualizarLista, BorderLayout.SOUTH);

        //eliminación
        JPanel panelEliminar = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelEliminar.setBorder(BorderFactory.createTitledBorder("Eliminar Perfil"));
        JTextField txtNombreEliminar = new JTextField(15);
        JButton btnEliminar = new JButton("Eliminar por Nombre");
        btnEliminar.addActionListener(e -> {
            String nombre = txtNombreEliminar.getText();
            //aqui se espera la llamada a admin.eliminarTutor(nombre)
        });
        panelEliminar.add(new JLabel("Nombre del Tutor:"));
        panelEliminar.add(txtNombreEliminar);
        panelEliminar.add(btnEliminar);

        add(panelRegistro, BorderLayout.WEST);
        add(panelLista, BorderLayout.CENTER);
        add(panelEliminar, BorderLayout.SOUTH);
    }
}

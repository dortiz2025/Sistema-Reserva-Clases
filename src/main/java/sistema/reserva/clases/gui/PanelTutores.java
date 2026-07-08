package sistema.reserva.clases.gui;

import sistema.reserva.clases.logica.Sistema;
import sistema.reserva.clases.logica.Tutor;
import sistema.reserva.clases.logica.bloquehorario.BloqueHorario;
import sistema.reserva.clases.logica.bloquehorario.DiaSemana;
import sistema.reserva.clases.logica.bloquehorario.Bloque;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PanelTutores extends JPanel{
    public PanelTutores(Sistema sistema, JLabel lblEstado) {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTabbedPane pestanas = new JTabbedPane();

        //registro
        JPanel panelRegistro = new JPanel(new GridLayout(6, 2, 5, 10));
        panelRegistro.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JTextField txtNombre = new JTextField(10);
        JTextField txtEmail = new JTextField(10);
        JTextField txtTarifa = new JTextField(10);
        JTextField txtCupos = new JTextField(10);
        JButton btnGuardar = new JButton("Registrar");

        btnGuardar.addActionListener(e -> {
            try {
                String idGen = sistema.registrarTutor(txtNombre.getText(), txtEmail.getText(),
                        Integer.parseInt(txtTarifa.getText()), Integer.parseInt(txtCupos.getText()));
                lblEstado.setText("  Estado: tutor registrado exitosamente con id " + idGen);
            } catch (Exception ex) { lblEstado.setText("  Error: " + ex.getMessage()); }
        });

        panelRegistro.add(new JLabel("Nombre:")); panelRegistro.add(txtNombre);
        panelRegistro.add(new JLabel("Email:")); panelRegistro.add(txtEmail);
        panelRegistro.add(new JLabel("Tarifa ($):")); panelRegistro.add(txtTarifa);
        panelRegistro.add(new JLabel("Cupos:")); panelRegistro.add(txtCupos);
        panelRegistro.add(new JLabel("")); panelRegistro.add(btnGuardar);

        //modificar
        JPanel panelModificar = new JPanel(new GridLayout(6, 2, 5, 10));
        panelModificar.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JTextField txtIdMod = new JTextField(10);
        JTextField txtNomMod = new JTextField(10);
        JTextField txtEmailMod = new JTextField(10);
        JTextField txtTarifaMod = new JTextField(10);
        JTextField txtCupoMod = new JTextField(10);
        JButton btnMod = new JButton("Modificar");

        btnMod.addActionListener(e -> {
            try {
                sistema.modificarTutor(txtIdMod.getText(), txtNomMod.getText(), txtEmailMod.getText(),
                        Integer.parseInt(txtTarifaMod.getText()), Integer.parseInt(txtCupoMod.getText()));
                lblEstado.setText("  Estado: tutor modificado");
            } catch (Exception ex) { lblEstado.setText("  Error: " + ex.getMessage()); }
        });
        panelModificar.add(new JLabel("ID:")); panelModificar.add(txtIdMod);
        panelModificar.add(new JLabel("Nuevo Nom:")); panelModificar.add(txtNomMod);
        panelModificar.add(new JLabel("Nuevo Email:")); panelModificar.add(txtEmailMod);
        panelModificar.add(new JLabel("Nueva Tarifa:")); panelModificar.add(txtTarifaMod);
        panelModificar.add(new JLabel("Nuevo Cupo:")); panelModificar.add(txtCupoMod);
        panelModificar.add(new JLabel("")); panelModificar.add(btnMod);

        //materias y horarios
        JPanel panelMatHor = new JPanel(new GridLayout(6, 2, 2, 5));
        panelMatHor.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JTextField txtIdMH = new JTextField(5);
        JTextField txtMateria = new JTextField(10);
        JComboBox<String> comboDia = new JComboBox<>();
        JComboBox<String> comboBloque = new JComboBox<>();
        for(DiaSemana d: DiaSemana.values()) comboDia.addItem(d.name());
        for(Bloque b: Bloque.values()) comboBloque.addItem(b.name());

        JButton btnAddMat = new JButton("+ Mat");
        JButton btnDelMat = new JButton("- Mat");
        JButton btnAddHor = new JButton("+ Hor");
        JButton btnDelHor = new JButton("- Hor");

        btnAddMat.addActionListener(e -> {
            try { sistema.agregarMateriaTutor(txtIdMH.getText(), txtMateria.getText()); lblEstado.setText("  Estado: materia agregada");
            } catch (Exception ex) { lblEstado.setText("  Error: " + ex.getMessage()); }
        });
        btnDelMat.addActionListener(e -> {
            try { sistema.eliminarMateriaTutor(txtIdMH.getText(), txtMateria.getText()); lblEstado.setText("  Estado: materia eliminada");
            } catch (Exception ex) { lblEstado.setText("  Error: " + ex.getMessage()); }
        });
        btnAddHor.addActionListener(e -> {
            try { sistema.agregarHorarioTutor(txtIdMH.getText(), new BloqueHorario(DiaSemana.valueOf((String)comboDia.getSelectedItem()), Bloque.valueOf((String)comboBloque.getSelectedItem()))); lblEstado.setText("  Estado: horario agregado");
            } catch (Exception ex) { lblEstado.setText("  Error: " + ex.getMessage()); }
        });
        btnDelHor.addActionListener(e -> {
            try { sistema.eliminarHorarioTutor(txtIdMH.getText(), new BloqueHorario(DiaSemana.valueOf((String)comboDia.getSelectedItem()), Bloque.valueOf((String)comboBloque.getSelectedItem()))); lblEstado.setText("  Estado: horario eliminado");
            } catch (Exception ex) { lblEstado.setText("  Error: " + ex.getMessage()); }
        });

        panelMatHor.add(new JLabel("ID Tutor:")); panelMatHor.add(txtIdMH);
        panelMatHor.add(new JLabel("Materia:")); panelMatHor.add(txtMateria);
        panelMatHor.add(btnAddMat); panelMatHor.add(btnDelMat);
        panelMatHor.add(new JLabel("Día:")); panelMatHor.add(comboDia);
        panelMatHor.add(new JLabel("Bloque:")); panelMatHor.add(comboBloque);
        panelMatHor.add(btnAddHor); panelMatHor.add(btnDelHor);

        pestanas.addTab("Registrar", panelRegistro);
        pestanas.addTab("Modificar", panelModificar);
        pestanas.addTab("Gestión Clases", panelMatHor);

        //visualizacion
        JPanel panelLista = new JPanel(new BorderLayout());
        panelLista.setBorder(BorderFactory.createTitledBorder("Tutores Registrados"));
        JTextArea txtAreaTutores = new JTextArea();
        txtAreaTutores.setEditable(false);
        txtAreaTutores.setLineWrap(true);
        txtAreaTutores.setWrapStyleWord(true);
        JButton btnActualizarLista = new JButton("Actualizar Lista");
        btnActualizarLista.addActionListener(e -> {
            try {
                List<Tutor> lista = sistema.obtenerTutores();
                StringBuilder sb = new StringBuilder();
                for (Tutor tut : lista) { sb.append(tut.toString()).append("\n\n"); }
                txtAreaTutores.setText(sb.toString());
                lblEstado.setText("  Estado: lista de tutores actualizada");
            } catch (Exception ex) { lblEstado.setText("  Error: " + ex.getMessage()); }
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
                sistema.eliminarTutor(txtIdEliminar.getText());
                lblEstado.setText("  Estado: tutor eliminado correctamente");
                txtIdEliminar.setText("");
                btnActualizarLista.doClick();
            } catch (Exception ex) { lblEstado.setText("  Error: " + ex.getMessage()); }
        });

        panelEliminar.add(new JLabel("ID del Tutor:"));
        panelEliminar.add(txtIdEliminar);
        panelEliminar.add(btnEliminar);

        add(pestanas, BorderLayout.WEST);
        add(panelLista, BorderLayout.CENTER);
        add(panelEliminar, BorderLayout.SOUTH);
    }
}
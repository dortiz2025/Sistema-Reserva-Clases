package sistema.reserva.clases.gui;

import javax.swing.*;
import java.awt.*;

public class SistemaTutoriasGUI extends JFrame {
    private CardLayout cardLayout;
    private JPanel panelDerecho;
    private JLabel lblEstado;

    private JComboBox<String> comboEstudiantes = new JComboBox<>();
    private JComboBox<String> comboMaterias = new JComboBox<>();
    private JComboBox<String> comboTutores = new JComboBox<>();
    private JComboBox<String> comboHorarios = new JComboBox<>();

    public SistemaTutoriasGUI() {
        setTitle("Sistema de Gestión de Tutorías - Panel de Administrador");
        setSize(850, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panelMenu = new JPanel(new GridLayout(5, 1, 10, 10));
        panelMenu.setBackground(new Color(45, 52, 54));
        panelMenu.setPreferredSize(new Dimension(200, 0));

        //botones
        JButton btnTutores = crearBotonMenu("Gestión Tutores");
        JButton btnEstudiantes = crearBotonMenu("Gestión Estudiantes");
        JButton btnAgendar = crearBotonMenu("Agendar Clase");
        JButton btnCalendario = crearBotonMenu("Ver Calendario");

        //cambiar de pantalla
        btnTutores.addActionListener(e -> cardLayout.show(panelDerecho, "TUTORES"));
        btnEstudiantes.addActionListener(e -> cardLayout.show(panelDerecho, "ESTUDIANTES"));
        btnAgendar.addActionListener(e -> cardLayout.show(panelDerecho, "AGENDAR"));
        btnCalendario.addActionListener(e -> cardLayout.show(panelDerecho, "CALENDARIO"));

        panelMenu.add(btnTutores);
        panelMenu.add(btnEstudiantes);
        panelMenu.add(btnAgendar);
        panelMenu.add(btnCalendario);


        cardLayout = new CardLayout();
        panelDerecho = new JPanel(cardLayout);

        panelDerecho.add(crearPanelEstudiantes(), "ESTUDIANTES");
        panelDerecho.add(crearPanelTutores(), "TUTORES");
        panelDerecho.add(crearPanelAgendar(), "AGENDAR");
        panelDerecho.add(crearPanelCalendario(), "CALENDARIO");

        lblEstado = new JLabel("  Estado: Iniciado");
        add(panelMenu, BorderLayout.WEST);
        add(panelDerecho, BorderLayout.CENTER);
        add(lblEstado, BorderLayout.SOUTH);

        cardLayout.show(panelDerecho, "ESTUDIANTES");
    }

    private JButton crearBotonMenu(String texto) {
        return new JButton(texto);
    }

    private JPanel crearPanelEstudiantes() {
        JPanel panel = new JPanel(new GridBagLayout());
        JPanel form = new JPanel(new GridLayout(4, 2, 10, 20));

        //componentes en variables
        JTextField txtNombre = new JTextField(15);
        JTextField txtMatricula = new JTextField(15);
        JButton btnGuardar = new JButton("Registrar Estudiante");

        //boton configurado para usarse
        btnGuardar.addActionListener(e -> {
            String nombre = txtNombre.getText();
            String matricula = txtMatricula.getText();
            // aqui se espera la llamada a admin.registrarEstudiante(nombre, matricula)
        });

        form.add(new JLabel("Nombre:")); form.add(txtNombre);
        form.add(new JLabel("Matrícula:")); form.add(txtMatricula);
        form.add(new JLabel("")); form.add(btnGuardar); //el espacio vacio alinea el boton a la derecha

        panel.add(form);
        return panel;
    }

    private JPanel crearPanelTutores() {
        JPanel panel = new JPanel(new GridBagLayout());
        JPanel form = new JPanel(new GridLayout(5, 2, 10, 20));

        JTextField txtNombre = new JTextField(15);
        JTextField txtMateria = new JTextField(15);
        JTextField txtCupos = new JTextField(15);
        JButton btnGuardar = new JButton("Registrar Tutor");

        btnGuardar.addActionListener(e -> {
            String nombre = txtNombre.getText();
            String materia = txtMateria.getText();
            String cupos = txtCupos.getText();
            // aqui se espera la llamada a admin.registrarTutor(nombre, materia, cupos)
        });

        form.add(new JLabel("Nombre:")); form.add(txtNombre);
        form.add(new JLabel("Materia:")); form.add(txtMateria);
        form.add(new JLabel("Cupos:")); form.add(txtCupos);
        form.add(new JLabel("")); form.add(btnGuardar);

        panel.add(form);
        return panel;
    }

    private JPanel crearPanelAgendar() {
        JPanel panel = new JPanel(new GridBagLayout());
        JPanel form = new JPanel(new GridLayout(6, 2, 10, 20));

        JButton btnConfirmar = new JButton("Confirmar Reserva");

        btnConfirmar.addActionListener(e -> {
            String estudiante = (String) comboEstudiantes.getSelectedItem();
            String materia = (String) comboMaterias.getSelectedItem();
            String tutor = (String) comboTutores.getSelectedItem();
            String horario = (String) comboHorarios.getSelectedItem();
            //aqui habria que realizar las validaciones de cupos cruzados y registrar la reserva
        });

        form.add(new JLabel("Estudiante:")); form.add(comboEstudiantes);
        form.add(new JLabel("Materia:")); form.add(comboMaterias);
        form.add(new JLabel("Tutor:")); form.add(comboTutores);
        form.add(new JLabel("Horario:")); form.add(comboHorarios);
        form.add(new JLabel("")); form.add(btnConfirmar);

        panel.add(form);
        return panel;
    }

    private JPanel crearPanelCalendario() {
        JPanel panel = new JPanel(new BorderLayout());

        JTextArea txtArea = new JTextArea();
        JButton btnActualizar = new JButton("Actualizar");

        btnActualizar.addActionListener(e -> {
            //aqui se espera que se consulte la lista de reservas al administrador y se muestre en txtArea
        });

        panel.add(new JScrollPane(txtArea), BorderLayout.CENTER);
        panel.add(btnActualizar, BorderLayout.SOUTH);
        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SistemaTutoriasGUI().setVisible(true));
    }
}
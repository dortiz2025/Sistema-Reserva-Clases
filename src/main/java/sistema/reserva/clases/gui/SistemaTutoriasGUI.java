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
        setSize(950, 600);
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
        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

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

        panelPrincipal.add(panelRegistro, BorderLayout.WEST);
        panelPrincipal.add(panelLista, BorderLayout.CENTER);
        panelPrincipal.add(panelEliminar, BorderLayout.SOUTH);

        return panelPrincipal;
    }

    private JPanel crearPanelTutores() {
        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

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

        panelPrincipal.add(panelRegistro, BorderLayout.WEST);
        panelPrincipal.add(panelLista, BorderLayout.CENTER);
        panelPrincipal.add(panelEliminar, BorderLayout.SOUTH);

        return panelPrincipal;
    }

    private JPanel crearPanelAgendar() {
        JPanel panel = new JPanel(new GridBagLayout());
        JPanel form = new JPanel(new GridLayout(6, 2, 10, 20));
        form.setBorder(BorderFactory.createTitledBorder("Datos de Reserva"));

        JButton btnConfirmar = new JButton("Confirmar Reserva");

        btnConfirmar.addActionListener(e -> {
            String estudiante = (String) comboEstudiantes.getSelectedItem();
            String materia = (String) comboMaterias.getSelectedItem();
            String tutor = (String) comboTutores.getSelectedItem();
            String horario = (String) comboHorarios.getSelectedItem();
            //aqui habria que realizar las validaciones de cupos cruzados,
            // verificar si el presupuesto alcanza para la tarifa y registrar la reserva
        });

        form.add(new JLabel("Estudiante:")); form.add(comboEstudiantes);
        form.add(new JLabel("Materia:")); form.add(comboMaterias);
        form.add(new JLabel("Tutor:")); form.add(comboTutores);
        form.add(new JLabel("Horario:")); form.add(comboHorarios);

        form.add(new JLabel(""));
        form.add(btnConfirmar);

        panel.add(form);
        return panel;
    }

    private JPanel crearPanelCalendario() {
        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

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

        // 2. Cancelación de Reservas
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

        panelPrincipal.add(panelLista, BorderLayout.CENTER);
        panelPrincipal.add(panelCancelar, BorderLayout.SOUTH);

        return panelPrincipal;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SistemaTutoriasGUI().setVisible(true));
    }
}
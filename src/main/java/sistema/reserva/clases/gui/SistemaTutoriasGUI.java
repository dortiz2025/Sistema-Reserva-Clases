package sistema.reserva.clases.gui;

import javax.swing.*;
import java.awt.*;

public class SistemaTutoriasGUI extends JFrame {
    private CardLayout cardLayout;
    private JPanel panelDerecho;
    private JLabel lblEstado;

    public SistemaTutoriasGUI() {
        setTitle("Sistema de Gestión de Tutorías - Panel de Administrador");
        setSize(850, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panelMenu = new JPanel(new GridLayout(5, 1, 10, 10));
        panelMenu.setBackground(new Color(45, 52, 54));
        panelMenu.setPreferredSize(new Dimension(200, 0));

        panelMenu.add(crearBotonMenu("Gestión Tutores"));
        panelMenu.add(crearBotonMenu("Gestión Estudiantes"));
        panelMenu.add(crearBotonMenu("Agendar Clase"));
        panelMenu.add(crearBotonMenu("Ver Calendario"));

        cardLayout = new CardLayout();
        panelDerecho = new JPanel(cardLayout);
        panelDerecho.add(crearPanelEstudiantes(), "ESTUDIANTES");
        panelDerecho.add(crearPanelTutores(), "TUTORES");
        lblEstado = new JLabel("  Estado: Iniciado");
        add(panelMenu, BorderLayout.WEST);
        add(panelDerecho, BorderLayout.CENTER);
        add(lblEstado, BorderLayout.SOUTH);
    }

    private JButton crearBotonMenu(String texto) {
        return new JButton(texto);
    }

    private JPanel crearPanelEstudiantes() {
        JPanel panel = new JPanel(new GridBagLayout());
        JPanel form = new JPanel(new GridLayout(4, 2, 10, 20));
        form.add(new JLabel("Nombre:")); form.add(new JTextField(15));
        form.add(new JLabel("Matrícula:")); form.add(new JTextField(15));
        form.add(new JButton("Registrar Estudiante"));
        panel.add(form);
        return panel;
    }

    private JPanel crearPanelTutores() {
        JPanel panel = new JPanel(new GridBagLayout());
        JPanel form = new JPanel(new GridLayout(5, 2, 10, 20));
        form.add(new JLabel("Nombre:")); form.add(new JTextField(15));
        form.add(new JLabel("Materia:")); form.add(new JTextField(15));
        form.add(new JLabel("Cupos:")); form.add(new JTextField(15));
        form.add(new JButton("Registrar Tutor"));
        panel.add(form);
        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SistemaTutoriasGUI().setVisible(true));
    }
}
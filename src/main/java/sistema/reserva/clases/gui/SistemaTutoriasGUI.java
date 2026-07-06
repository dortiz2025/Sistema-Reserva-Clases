package sistema.reserva.clases.gui;

import sistema.reserva.clases.logica.Sistema;
import javax.swing.*;
import java.awt.*;

/**
 * Clase que representa la Interfaz Gráfica de Usuario (GUI) del Sistema de Gestión de Tutorías.
 * * Actúa como la "Vista" del sistema, proporcionando los componentes visuales para
 * la gestión de perfiles (estudiantes y tutores), el agendamiento de reservas y
 * la visualización del calendario. No contiene lógica de negocio; los eventos de
 * los botones están diseñados para ser conectados a un controlador externo (Administrador).
 */
public class SistemaTutoriasGUI extends JFrame {
    private CardLayout cardLayout;
    private JPanel panelDerecho;
    public JLabel lblEstado;

    // se instancia el singleton de la logica
    private Sistema sistema = Sistema.getInstancia();

    /**
     * Constructor de SistemaTutoriasGUI.
     * Inicializa la ventana principal, configura el menú lateral de navegación
     * y prepara el contenedor principal usando un CardLayout para alternar entre pantallas.
     */
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

        // se inicializa primero para poder pasarlo a los paneles
        lblEstado = new JLabel("  Estado: Iniciado");

        cardLayout = new CardLayout();
        panelDerecho = new JPanel(cardLayout);

        panelDerecho.add(new PanelEstudiantes(sistema, lblEstado), "ESTUDIANTES");
        panelDerecho.add(new PanelTutores(sistema, lblEstado), "TUTORES");
        panelDerecho.add(new PanelAgendar(sistema, lblEstado), "AGENDAR");
        panelDerecho.add(new PanelCalendario(sistema, lblEstado), "CALENDARIO");

        add(panelMenu, BorderLayout.WEST);
        add(panelDerecho, BorderLayout.CENTER);
        add(lblEstado, BorderLayout.SOUTH);

        cardLayout.show(panelDerecho, "ESTUDIANTES");
    }

    private JButton crearBotonMenu(String texto) {
        return new JButton(texto);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SistemaTutoriasGUI().setVisible(true));
    }
}
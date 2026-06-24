package sistema.reserva.clases;

import sistema.reserva.clases.gui.SistemaTutoriasGUI;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SistemaTutoriasGUI ventana = new SistemaTutoriasGUI();
            ventana.setVisible(true);
        });
    }
}
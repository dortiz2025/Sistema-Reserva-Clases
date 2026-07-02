package sistema.reserva.clases.gui;

import javax.swing.*;
import java.awt.*;

public class PanelAgendar extends JPanel {

    private JComboBox<String> comboEstudiantes;
    private JComboBox<String> comboMaterias ;
    private JComboBox<String> comboTutores;
    private JComboBox<String> comboHorarios;

    public PanelAgendar(){
        setLayout(new GridBagLayout());

        comboEstudiantes = new JComboBox<>();
        comboMaterias = new JComboBox<>();
        comboTutores = new JComboBox<>();
        comboHorarios = new JComboBox<>();

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
        add(form);
    }
}


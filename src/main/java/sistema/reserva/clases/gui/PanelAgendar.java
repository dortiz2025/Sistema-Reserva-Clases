package sistema.reserva.clases.gui;

import sistema.reserva.clases.logica.Sistema;
import sistema.reserva.clases.logica.Estudiante;
import sistema.reserva.clases.logica.Tutor;
import sistema.reserva.clases.logica.bloquehorario.BloqueHorario;
import sistema.reserva.clases.logica.bloquehorario.Bloque;
import sistema.reserva.clases.logica.bloquehorario.DiaSemana;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Clase que representa el panel para agendar y modificar reservas de clases.
 * Conecta a estudiantes y tutores disponibles, permitiendo seleccionar materias
 * dinámicamente según la carga del tutor y definiendo bloques de horario específicos.
 */
public class PanelAgendar extends JPanel {

    /**
     * Constructor del panel de agendamiento.
     * Configura los selectores dinámicos y los formateadores de fecha para
     * capturar la entrada del usuario de manera segura y sin errores de formato.
     *
     * @param sistema Referencia al facade principal de la lógica del sistema.
     * @param lblEstado Etiqueta compartida para informar sobre el estado de la reserva.
     */
    public PanelAgendar(Sistema sistema, JLabel lblEstado){
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTabbedPane pestanas = new JTabbedPane();

        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // agendar
        JPanel formAgendar = new JPanel(new GridLayout(7, 2, 5, 15));
        formAgendar.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JComboBox<String> comboEstudiantes = new JComboBox<>();
        JComboBox<String> comboTutores = new JComboBox<>();
        JComboBox<String> comboMateria = new JComboBox<>();
        JComboBox<String> comboDia = new JComboBox<>();
        JComboBox<String> comboBloque = new JComboBox<>();
        JTextField txtFecha = new JTextField("06/07/2026", 15);

        for (DiaSemana dia : DiaSemana.values()) comboDia.addItem(dia.name());
        for (Bloque b : Bloque.values()) comboBloque.addItem(b.name());

        JButton btnActualizar = new JButton("Cargar Listas");
        JButton btnConfirmar = new JButton("Confirmar Reserva");

        comboTutores.addActionListener(e -> {
            if (comboTutores.getSelectedItem() != null) {
                comboMateria.removeAllItems();
                try {
                    Tutor tut = sistema.obtenerTutorPorId((String) comboTutores.getSelectedItem());
                    for (String m : tut.getMaterias()) {
                        comboMateria.addItem(m);
                    }
                } catch (Exception ex) {
                    lblEstado.setText("  Error: " + ex.getMessage());
                }
            }
        });

        btnActualizar.addActionListener(e -> {
            try {
                comboEstudiantes.removeAllItems();
                comboTutores.removeAllItems();
                comboMateria.removeAllItems();

                for(Estudiante est : sistema.obtenerEstudiantes()) comboEstudiantes.addItem(est.getMatricula());
                for(Tutor tut : sistema.obtenerTutores()) comboTutores.addItem(tut.getId());
                lblEstado.setText("  Estado: listas cargadas");
            } catch (Exception ex) { lblEstado.setText("  Error: " + ex.getMessage()); }
        });

        btnConfirmar.addActionListener(e -> {
            try {
                if (comboMateria.getSelectedItem() == null) throw new Exception("el tutor seleccionado no tiene materias registradas");

                BloqueHorario horario = new BloqueHorario(DiaSemana.valueOf((String)comboDia.getSelectedItem()), Bloque.valueOf((String)comboBloque.getSelectedItem()));
                LocalDate fechaParseada = LocalDate.parse(txtFecha.getText(), formatoFecha);

                String idReserva = sistema.agendarClase((String)comboEstudiantes.getSelectedItem(), (String)comboTutores.getSelectedItem(),
                        (String)comboMateria.getSelectedItem(), horario, fechaParseada);
                lblEstado.setText("  Estado: reserva exitosa con id " + idReserva);
            } catch (Exception ex) { lblEstado.setText("  Error: formato de fecha invalido u otro error " + ex.getMessage()); }
        });

        formAgendar.add(new JLabel("Matricula Estudiante:")); formAgendar.add(comboEstudiantes);
        formAgendar.add(new JLabel("ID Tutor:")); formAgendar.add(comboTutores);
        formAgendar.add(new JLabel("Materia:")); formAgendar.add(comboMateria);
        formAgendar.add(new JLabel("Día:")); formAgendar.add(comboDia);
        formAgendar.add(new JLabel("Bloque:")); formAgendar.add(comboBloque);
        formAgendar.add(new JLabel("Fecha (DD/MM/YYYY):")); formAgendar.add(txtFecha);
        formAgendar.add(btnActualizar); formAgendar.add(btnConfirmar);

        // modificar
        JPanel formModificar = new JPanel(new GridLayout(7, 2, 5, 15));
        formModificar.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JTextField txtIdReservaMod = new JTextField(15);
        JComboBox<String> comboTutorMod = new JComboBox<>();
        JComboBox<String> comboMateriaMod = new JComboBox<>();
        JComboBox<String> comboDiaMod = new JComboBox<>();
        JComboBox<String> comboBloqueMod = new JComboBox<>();
        JTextField txtFechaMod = new JTextField("06/07/2026", 15);

        for (DiaSemana dia : DiaSemana.values()) comboDiaMod.addItem(dia.name());
        for (Bloque b : Bloque.values()) comboBloqueMod.addItem(b.name());

        JButton btnCargarMod = new JButton("Cargar Tutores");
        JButton btnGuardarMod = new JButton("Guardar Cambios");

        comboTutorMod.addActionListener(e -> {
            if (comboTutorMod.getSelectedItem() != null) {
                comboMateriaMod.removeAllItems();
                try {
                    Tutor tut = sistema.obtenerTutorPorId((String) comboTutorMod.getSelectedItem());
                    for (String m : tut.getMaterias()) {
                        comboMateriaMod.addItem(m);
                    }
                } catch (Exception ex) {
                    lblEstado.setText("  Error: " + ex.getMessage());
                }
            }
        });

        btnCargarMod.addActionListener(e -> {
            try {
                comboTutorMod.removeAllItems();
                comboMateriaMod.removeAllItems();
                for(Tutor tut : sistema.obtenerTutores()) comboTutorMod.addItem(tut.getId());
                lblEstado.setText("  Estado: tutores cargados para modificar");
            } catch (Exception ex) { lblEstado.setText("  Error: " + ex.getMessage()); }
        });

        btnGuardarMod.addActionListener(e -> {
            try {
                if (comboMateriaMod.getSelectedItem() == null) throw new Exception("el tutor seleccionado no tiene materias registradas");

                BloqueHorario nuevoHorario = new BloqueHorario(DiaSemana.valueOf((String)comboDiaMod.getSelectedItem()), Bloque.valueOf((String)comboBloqueMod.getSelectedItem()));
                LocalDate fechaModParseada = LocalDate.parse(txtFechaMod.getText(), formatoFecha);

                sistema.modificarReserva(txtIdReservaMod.getText(), (String)comboTutorMod.getSelectedItem(), (String)comboMateriaMod.getSelectedItem(), nuevoHorario, fechaModParseada);
                lblEstado.setText("  Estado: reserva modificada exitosamente");
            } catch (Exception ex) { lblEstado.setText("  Error: formato de fecha invalido u otro error " + ex.getMessage()); }
        });

        formModificar.add(new JLabel("ID Reserva:")); formModificar.add(txtIdReservaMod);
        formModificar.add(new JLabel("Nuevo ID Tutor:")); formModificar.add(comboTutorMod);
        formModificar.add(new JLabel("Nueva Materia:")); formModificar.add(comboMateriaMod);
        formModificar.add(new JLabel("Nuevo Día:")); formModificar.add(comboDiaMod);
        formModificar.add(new JLabel("Nuevo Bloque:")); formModificar.add(comboBloqueMod);
        formModificar.add(new JLabel("Nueva Fecha (DD/MM/YYYY):")); formModificar.add(txtFechaMod);
        formModificar.add(btnCargarMod); formModificar.add(btnGuardarMod);

        pestanas.addTab("Agendar Clase", formAgendar);
        pestanas.addTab("Modificar Reserva", formModificar);

        add(pestanas, BorderLayout.CENTER);
    }
}
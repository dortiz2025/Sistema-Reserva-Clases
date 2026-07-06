package sistema.reserva.clases.logica.bloquehorario;

/**
 * Enum que representa los bloques de horarios disponibles en el sistema.
 */
public enum Bloque {
    BLOQUE_1("08:00", "08:45"),
    BLOQUE_2("09:00", "09:45"),
    BLOQUE_3("10:00", "10:45"),
    BLOQUE_4("11:00", "11:45"),
    BLOQUE_5("12:00", "12:45"),
    BLOQUE_6("13:00", "13:45"),
    BLOQUE_7("14:00", "14:45"),
    BLOQUE_8("15:00", "15:45"),
    BLOQUE_9("16:00", "16:45"),
    BLOQUE_10("17:00", "17:45"),
    BLOQUE_11("18:00", "18:45"),
    BLOQUE_12("19:00", "19:45"),
    BLOQUE_13("20:00", "20:45"),
    BLOQUE_14("20:45", "21:45");

    private final String horaInicio;
    private final String horaFin;

    Bloque(String horaInicio, String horaFin) {
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    @Override
    public String toString() {
        return this.name() + " (" + horaInicio + " - " + horaFin + ")";
    }
}
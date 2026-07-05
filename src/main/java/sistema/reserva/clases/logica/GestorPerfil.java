package sistema.reserva.clases.logica;

import sistema.reserva.clases.logica.estrategias.FiltrarStrategy;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Clase que gestiona los perfiles del sistema.
 * Debe extenderse a un tipo de perfil específico.
 * @param <T> Tipo de Perfil.
 */
public abstract class GestorPerfil<T extends Perfil> {
    protected Map<String, T> perfiles = new HashMap<>();

    /**
     * Registra un perfil en el mapa.
     * @param idKey Key que puede ser ID de tutor o matrícula de estudiante.
     * @param perfil Referencia del perfil que se guarda.
     */
    public void registrarPerfil(String idKey, T perfil) {
        if (perfiles.containsKey(idKey)) {
            throw new IllegalArgumentException("El identificador " + idKey + " ya se encuentra asociado a un perfil.");
        }
        //Validación para que no se repitan personas.
        boolean correoYaExiste = perfiles.values().stream()
                .anyMatch(p -> p.getEmail().equalsIgnoreCase(perfil.getEmail()));

        if (correoYaExiste) {
            throw new IllegalArgumentException("Ya existe un usuario registrado con el correo: " + perfil.getEmail());
        }
        perfiles.put(idKey, perfil);
    }

    /**
     * Busca el perfil por la Key asociada.
     * @param idKey Key: Id o matrícula.
     * @return Devuelve la referencia del perfil.
     */
    public T buscarPorId(String idKey) {
        T perfil = perfiles.get(idKey);
        if (perfil == null) {
            throw new NoSuchElementException("No se encontró ningún perfil asociado a la key: " + idKey);
        }
        return perfil;
    }

    /**
     * Elimina el perfil.
     * @param idKey Key asociada al perfil.
     * @return Boolean dependiendo si la eliminación fue exitosa o no.
     */
    public boolean eliminarPerfil(String idKey) {
        return perfiles.remove(idKey) != null;
    }

    /**
     * Devuelve la lista de perfiles.
     * @return Lista de perfiles registrados.
     */
    public List<T> obtenerPerfiles() {
        return new ArrayList<>(perfiles.values());
    }

    /**
     * Devuelve una lista de perfiles filtrada.
     * @param estrategia Filtro entregado.
     * @return Lista filtrada.
     */
    public List<T> filtrar(FiltrarStrategy<T> estrategia) {
        return perfiles.values().stream()
                .filter(estrategia::cumpleCondicion)
                .collect(Collectors.toList());
    }
}
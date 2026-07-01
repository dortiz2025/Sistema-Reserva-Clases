package sistema.reserva.clases.logica;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
            throw new IllegalArgumentException("El identificador " + idKey + " ya se encuentra registrado.");
        }
        perfiles.put(idKey, perfil);
    }

    /**
     * Busca el perfil por la Key asociada.
     * @param idKey Key: Id o matrícula.
     * @return Devuelve la referencia del perfil.
     */
    public T buscarPorId(String idKey) {
        return perfiles.get(idKey);
    }

    /**
     * Elimina el perfil.
     * @param idKey Key asociada al perfil.
     */
    public void eliminarPerfil(String idKey) {
        perfiles.remove(idKey);
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
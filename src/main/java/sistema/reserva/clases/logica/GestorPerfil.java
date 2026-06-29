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

    public void registrarPerfil(String idKey, T perfil) {
        if (perfiles.containsKey(idKey)) {
            throw new IllegalArgumentException("El identificador " + idKey + " ya se encuentra registrado.");
        }
        perfiles.put(idKey, perfil);
    }

    public T buscarPorId(String idKey) {
        return perfiles.get(idKey);
    }

    public void eliminarPerfil(String idKey) {
        perfiles.remove(idKey);
    }

    public List<T> obtenerPerfiles() {
        return new ArrayList<>(perfiles.values());
    }

    public List<T> filtrar(FiltrarStrategy<T> estrategia) {
        return perfiles.values().stream()
                .filter(estrategia::cumpleCondicion)
                .collect(Collectors.toList());
    }
}
package com.mibiblioteka.api.services.usuariosService;

import com.mibiblioteka.api.helpers.filtros.FiltroHelper;
import com.mibiblioteka.api.models.Usuarios;
import com.mibiblioteka.api.repository.usuariosRepository.UsuariosRepository;
import org.springframework.stereotype.Service;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ObtenerUsuariosService {

    private UsuariosRepository usuariosRepository;

    public ObtenerUsuariosService(UsuariosRepository usuariosRepository) {
        this.usuariosRepository = usuariosRepository;
    }

    public List<Usuarios> obtenerUsuarios(Map<String, Object> filtros) {
        Query query = FiltroHelper.construirQuery(filtros, Usuarios.class);
        return usuariosRepository.buscar(query);
    }

    public Optional<Usuarios> obtenerUsuariosPorID(String id) {
        return usuariosRepository.buscarPorId(id);
    }

}

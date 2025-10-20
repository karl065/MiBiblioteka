package com.mibiblioteka.api.services.rolesService;

import com.mibiblioteka.api.helpers.filtros.FiltroHelper;
import com.mibiblioteka.api.models.Roles;
import com.mibiblioteka.api.repository.rolesRepository.RolesRepository;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ObtenerRolesServices {

    private RolesRepository rolesRepository;

    public ObtenerRolesServices(RolesRepository rolesRepository) {
        this.rolesRepository = rolesRepository;
    }

    public List<Roles> obtenerRoles(Map<String, Object> filtros) {
        Query query = FiltroHelper.construirQuery(filtros, Roles.class);
        return rolesRepository.buscar(query);
    }

    public Optional<Roles> obtenerRolPorId(String id) {
        return rolesRepository.buscarPorId(id);
    }

}

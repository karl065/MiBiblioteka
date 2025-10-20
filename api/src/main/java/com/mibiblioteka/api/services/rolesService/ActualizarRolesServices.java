package com.mibiblioteka.api.services.rolesService;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mibiblioteka.api.models.Roles;
import com.mibiblioteka.api.repository.rolesRepository.RolesRepository;

@Service
public class ActualizarRolesServices {

    private RolesRepository rolesRepository;

    public ActualizarRolesServices(RolesRepository rolesRepository) {
        this.rolesRepository = rolesRepository;
    }

    public Optional<Roles> actualizarRol(String id, Roles update) {
        return rolesRepository.actualizar(id, update);
    }
}

package com.mibiblioteka.api.services.rolesService;

import org.springframework.stereotype.Service;

import com.mibiblioteka.api.models.Roles;
import com.mibiblioteka.api.repository.rolesRepository.RolesRepository;

@Service
public class CrearRolesServices {

    private RolesRepository rolesRepository;

    public CrearRolesServices(RolesRepository rolesRepository) {
        this.rolesRepository = rolesRepository;
    }

    public Roles crearRol(Roles rol) {
        return rolesRepository.crear(rol);
    }
}

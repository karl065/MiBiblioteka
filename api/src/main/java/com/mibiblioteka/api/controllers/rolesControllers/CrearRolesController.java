package com.mibiblioteka.api.controllers.rolesControllers;

import com.mibiblioteka.api.models.Roles;
import com.mibiblioteka.api.services.rolesService.CrearRolesServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roles/crear")
public class CrearRolesController {

    private final CrearRolesServices crearRolesServices;

    @Autowired
    public CrearRolesController(CrearRolesServices crearRolesServices) {
        this.crearRolesServices = crearRolesServices;
    }

    @PostMapping
    public ResponseEntity<?> crearRol(@RequestBody Roles rol) {
        try {
            Roles nuevoRol = crearRolesServices.crearRol(rol);
            return ResponseEntity.ok(nuevoRol);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

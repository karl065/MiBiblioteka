package com.mibiblioteka.api.controllers.rolesControllers;

import com.mibiblioteka.api.models.Roles;
import com.mibiblioteka.api.services.rolesService.RolesServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roles/crear")
public class CrearRolesController {

    private final RolesServices rolesService;

    @Autowired
    public CrearRolesController(RolesServices rolesService) {
        this.rolesService = rolesService;
    }

    @PostMapping
    public ResponseEntity<?> crearRol(@RequestBody Roles rol) {
        try {
            Roles nuevoRol = rolesService.crearRol(rol);
            return ResponseEntity.ok(nuevoRol);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

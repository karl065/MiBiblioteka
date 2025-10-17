package com.mibiblioteka.api.controllers.rolesControllers;

import com.mibiblioteka.api.models.Roles;
import com.mibiblioteka.api.services.rolesService.RolesServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roles/actualizar")
public class ActualizarRolesController {

    private final RolesServices rolesService;

    @Autowired
    public ActualizarRolesController(RolesServices rolesService) {
        this.rolesService = rolesService;
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarRol(@PathVariable String id, @RequestBody Roles rol) {
        try {
            Roles actualizado = rolesService.actualizarRol(id, rol);
            return ResponseEntity.ok(actualizado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

package com.mibiblioteka.api.controllers.rolesControllers;

import com.mibiblioteka.api.models.Roles;
import com.mibiblioteka.api.services.rolesService.ActualizarRolesServices;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roles/actualizar")
public class ActualizarRolesController {

    private final ActualizarRolesServices actualizarRolesServices;

    @Autowired
    public ActualizarRolesController(ActualizarRolesServices actualizarRolesServices) {
        this.actualizarRolesServices = actualizarRolesServices;
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarRol(@PathVariable String id, @RequestBody Roles rol) {
        try {

            Optional<Roles> actualizado = actualizarRolesServices.actualizarRol(id, rol);

            return ResponseEntity.ok(actualizado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

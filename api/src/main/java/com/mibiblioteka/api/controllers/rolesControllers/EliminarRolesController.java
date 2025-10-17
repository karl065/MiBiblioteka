package com.mibiblioteka.api.controllers.rolesControllers;

import com.mibiblioteka.api.services.rolesService.RolesServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roles/eliminar")
public class EliminarRolesController {

    private final RolesServices rolesService;

    @Autowired
    public EliminarRolesController(RolesServices rolesService) {
        this.rolesService = rolesService;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarRol(@PathVariable String id) {
        try {
            rolesService.eliminarRol(id);
            return ResponseEntity.ok("Rol eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

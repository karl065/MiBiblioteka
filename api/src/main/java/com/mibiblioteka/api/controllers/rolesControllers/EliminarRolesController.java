package com.mibiblioteka.api.controllers.rolesControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mibiblioteka.api.services.rolesService.EliminarRolesServices;

@RestController
@RequestMapping("/roles/eliminar")
public class EliminarRolesController {

    private final EliminarRolesServices eliminarRolesServices;

    @Autowired
    public EliminarRolesController(EliminarRolesServices eliminarRolesServices) {
        this.eliminarRolesServices = eliminarRolesServices;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarRol(@PathVariable String id) {
        try {
            boolean eliminado = eliminarRolesServices.eliminarRol(id);
            if (!eliminado) {
                return ResponseEntity.status(404).body("⚠️ No se encontró el rol con id: " + id);
            }
            return ResponseEntity.ok("✅ Rol eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

package com.mibiblioteka.api.controllers.usuariosControllers;

import com.mibiblioteka.api.services.usuariosService.EliminarUsuariosService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios/eliminar")
public class EliminarUsuariosController {

    private final EliminarUsuariosService eliminarUsuariosService;

    public EliminarUsuariosController(EliminarUsuariosService eliminarUsuariosService) {
        this.eliminarUsuariosService = eliminarUsuariosService;
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('SuperUsuario')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable String id) {
        try {

            boolean eliminado = eliminarUsuariosService.eliminarUsuario(id);

            if (!eliminado) {
                return ResponseEntity.status(404).body("⚠️ No se encontró el rol con id: " + id);
            }

            return ResponseEntity.ok("Usuario eliminado correctamente");

        } catch (Exception e) {

            e.printStackTrace();
            System.err.println("Error al eliminar al usuario: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500
        }

    }
}

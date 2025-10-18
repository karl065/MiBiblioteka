package com.mibiblioteka.api.controllers.usuariosControllers;

import com.mibiblioteka.api.services.usuariosService.EliminarUsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios/eliminar")
public class EliminarUsuariosController {

    @Autowired
    private EliminarUsuariosService eliminarUsuariosService;

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable String id) {
        try {
            eliminarUsuariosService.eliminarUsuarioServices(id);
            return ResponseEntity.noContent().build(); // 204 No Content
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error al eliminar al usuario: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500
        }

    }
}

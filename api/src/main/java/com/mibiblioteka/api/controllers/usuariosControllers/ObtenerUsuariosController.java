package com.mibiblioteka.api.controllers.usuariosControllers;

import com.mibiblioteka.api.models.Usuarios;
import com.mibiblioteka.api.services.usuariosService.ObtenerUsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios/obtener")
public class ObtenerUsuariosController {
    
     @Autowired
    private ObtenerUsuariosService obtenerUsuariosService;

    @PreAuthorize("hasRole('ADMIN') or hasRole('SuperUsuario')")
    @GetMapping
    public List<Usuarios> obtenerTodos() {
        return obtenerUsuariosService.obtenerTodos();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<Usuarios> obtenerPorId(@PathVariable String id) {
        try {
            
            return obtenerUsuariosService.obtenerPorId(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error al obtener usuarios: " + e.getMessage());
            return null;
        }
    }

}

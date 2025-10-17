package com.mibiblioteka.api.controllers.usuariosControllers;

import com.mibiblioteka.api.models.Usuarios;
import com.mibiblioteka.api.services.usuariosService.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios/obtener")
public class ObtenerUsuariosController {
    
     @Autowired
    private UsuariosService usuariosService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<Usuarios> obtenerTodos() {
        return usuariosService.obtenerTodos();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<Usuarios> obtenerPorId(@PathVariable String id) {
        return usuariosService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}

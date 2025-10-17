package com.mibiblioteka.api.controllers.usuariosControllers;

import com.mibiblioteka.api.services.usuariosService.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios/eliminar")
public class EliminarUsuariosController {
    
     @Autowired
    private UsuariosService usuariosService;

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable String id) {
        usuariosService.eliminarUsuario(id);
    }

}

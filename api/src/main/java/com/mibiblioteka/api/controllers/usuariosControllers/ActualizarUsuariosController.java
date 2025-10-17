package com.mibiblioteka.api.controllers.usuariosControllers;


import com.mibiblioteka.api.models.Usuarios;
import com.mibiblioteka.api.services.usuariosService.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios/actualizar")
public class ActualizarUsuariosController {
    
    @Autowired
    private UsuariosService usuariosService;

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public Usuarios actualizar(@PathVariable String id, @RequestBody Usuarios usuario) {
        return usuariosService.actualizarUsuario(id, usuario);
    }

}

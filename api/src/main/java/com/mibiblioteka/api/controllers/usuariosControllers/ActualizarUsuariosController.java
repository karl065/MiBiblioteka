package com.mibiblioteka.api.controllers.usuariosControllers;


import com.mibiblioteka.api.models.Usuarios;
import com.mibiblioteka.api.services.usuariosService.ActualizarUsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios/actualizar")
public class ActualizarUsuariosController {
    
    @Autowired
    private ActualizarUsuariosService actualizarUsuariosService;

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public Usuarios actualizar(@PathVariable String id, @RequestBody Usuarios usuario) {
        try {

            return actualizarUsuariosService.actualizarUsuarioService(id, usuario);
                      
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error de Login: " + e.getMessage());
            return null;
        }
    }

}

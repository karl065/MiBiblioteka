package com.mibiblioteka.api.controllers.usuariosControllers;

import com.mibiblioteka.api.models.Usuarios;
import com.mibiblioteka.api.services.usuariosService.ActualizarUsuariosService;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios/actualizar")
public class ActualizarUsuariosController {

    private ActualizarUsuariosService actualizarUsuariosService;

    public ActualizarUsuariosController(ActualizarUsuariosService actualizarUsuariosService) {
        this.actualizarUsuariosService = actualizarUsuariosService;
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable String id, @RequestBody Usuarios usuario) {
        try {
            Optional<Usuarios> usuarioActualizado = actualizarUsuariosService.actualizarUsuario(id, usuario);
            return ResponseEntity.ok(usuarioActualizado);

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error al actualizar usuario: " + e.getMessage());
            return ResponseEntity.internalServerError().body("Error al actualizar usuario: " + e.getMessage());
        }
    }

}

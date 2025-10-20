package com.mibiblioteka.api.controllers.autoresControllers;

import com.mibiblioteka.api.models.Autores;
import com.mibiblioteka.api.services.autoresService.ActualizarAutoresServices;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/autores/actualizar")
public class ActualizarAutoresController {

    private final ActualizarAutoresServices actualizarAutoresServices;

    @Autowired
    public ActualizarAutoresController(ActualizarAutoresServices actualizarAutoresServices) {
        this.actualizarAutoresServices = actualizarAutoresServices;
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarAutor(@PathVariable String id, @RequestBody Autores autor) {
        try {

            Optional<Autores> actualizado = actualizarAutoresServices.actualizarAutor(id, autor);

            return ResponseEntity.ok(actualizado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

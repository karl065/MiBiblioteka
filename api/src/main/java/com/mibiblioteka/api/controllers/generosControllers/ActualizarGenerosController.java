package com.mibiblioteka.api.controllers.generosControllers;

import com.mibiblioteka.api.models.Generos;
import com.mibiblioteka.api.services.generosService.ActualizarGenerosServices;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/generos/actualizar")
public class ActualizarGenerosController {

    private final ActualizarGenerosServices actualizarGenerosServices;

    @Autowired
    public ActualizarGenerosController(ActualizarGenerosServices actualizarGenerosServices) {
        this.actualizarGenerosServices = actualizarGenerosServices;
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarGenero(@PathVariable String id, @RequestBody Generos genero) {
        try {

            Optional<Generos> actualizado = actualizarGenerosServices.actualizarGenero(id, genero);

            return ResponseEntity.ok(actualizado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

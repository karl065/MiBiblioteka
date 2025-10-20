package com.mibiblioteka.api.controllers.librosControllers;

import com.mibiblioteka.api.models.Libros;
import com.mibiblioteka.api.services.librosService.ActualizarLibrosServices;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/libros/actualizar")
public class ActualizarLibrosController {

    private final ActualizarLibrosServices actualizarLibrosServices;

    @Autowired
    public ActualizarLibrosController(ActualizarLibrosServices actualizarLibrosServices) {
        this.actualizarLibrosServices = actualizarLibrosServices;
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarLibro(@PathVariable String id, @RequestBody Libros rol) {
        try {

            Optional<Libros> actualizado = actualizarLibrosServices.actualizarLibro(id, rol);

            return ResponseEntity.ok(actualizado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

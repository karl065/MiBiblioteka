package com.mibiblioteka.api.controllers.estadoLibroControllers;

import com.mibiblioteka.api.models.EstadoLibro;
import com.mibiblioteka.api.services.estadoLibroService.ActualizarEstadoLibroServices;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/estadoLibro/actualizar")
public class ActualizarEstadoLibroController {

    private final ActualizarEstadoLibroServices actualizarEstadoLibroServices;

    @Autowired
    public ActualizarEstadoLibroController(ActualizarEstadoLibroServices actualizarEstadoLibroServices) {
        this.actualizarEstadoLibroServices = actualizarEstadoLibroServices;
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarEstadoLibro(@PathVariable String id, @RequestBody EstadoLibro estadoLibro) {
        try {

            Optional<EstadoLibro> actualizado = actualizarEstadoLibroServices.actualizarEstadoLibro(id, estadoLibro);

            return ResponseEntity.ok(actualizado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

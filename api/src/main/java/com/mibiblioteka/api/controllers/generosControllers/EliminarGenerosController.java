package com.mibiblioteka.api.controllers.generosControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mibiblioteka.api.services.generosService.EliminarGenerosServices;

@RestController
@RequestMapping("/generos/eliminar")
public class EliminarGenerosController {

    private final EliminarGenerosServices eliminarGenerosServices;

    @Autowired
    public EliminarGenerosController(EliminarGenerosServices eliminarGenerosServices) {
        this.eliminarGenerosServices = eliminarGenerosServices;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarGenero(@PathVariable String id) {
        try {
            boolean eliminado = eliminarGenerosServices.eliminarGenero(id);
            if (!eliminado) {
                return ResponseEntity.status(404).body("⚠️ No se encontró el genero con id: " + id);
            }
            return ResponseEntity.ok("✅ Genero eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

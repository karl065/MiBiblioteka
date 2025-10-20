package com.mibiblioteka.api.controllers.autoresControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mibiblioteka.api.services.autoresService.EliminarAutoresServices;

@RestController
@RequestMapping("/autores/eliminar")
public class EliminarAutoresController {

    private final EliminarAutoresServices eliminarAutoresServices;

    @Autowired
    public EliminarAutoresController(EliminarAutoresServices eliminarAutoresServices) {
        this.eliminarAutoresServices = eliminarAutoresServices;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarAutor(@PathVariable String id) {
        try {
            boolean eliminado = eliminarAutoresServices.eliminarAutor(id);
            if (!eliminado) {
                return ResponseEntity.status(404).body("⚠️ No se encontró el autor con id: " + id);
            }
            return ResponseEntity.ok("✅ Autor eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

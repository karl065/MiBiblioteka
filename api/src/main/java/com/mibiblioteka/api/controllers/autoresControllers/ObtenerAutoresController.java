package com.mibiblioteka.api.controllers.autoresControllers;

import com.mibiblioteka.api.models.Autores;
import com.mibiblioteka.api.services.autoresService.ObtenerAutoresServices;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/autores/obtener")
public class ObtenerAutoresController {

    private final ObtenerAutoresServices autoresService;

    public ObtenerAutoresController(ObtenerAutoresServices autoresService) {
        this.autoresService = autoresService;
    }

    @GetMapping
    public List<Autores> obtenerAutores(@RequestParam Map<String, Object> filtros) {
        // Si filtros está vacío, traerá todos los autores
        return autoresService.obtenerAutores(filtros);
    }

    @GetMapping("/{id}")
    public Autores obtenerAutorPorId(@PathVariable String id) throws Exception {
        Map<String, Object> filtros = Map.of("id", id);
        List<Autores> resultado = autoresService.obtenerAutores(filtros);
        if (resultado.isEmpty()) {
            throw new Exception("Autor no encontrado con id: " + id);
        }
        return resultado.get(0);
    }

    @PostMapping("/filtrar")
    public List<Autores> filtrarAutores(@RequestBody Map<String, Object> filtros) {
        return autoresService.obtenerAutores(filtros);
    }
}

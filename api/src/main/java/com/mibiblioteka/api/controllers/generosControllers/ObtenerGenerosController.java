package com.mibiblioteka.api.controllers.generosControllers;

import com.mibiblioteka.api.models.Generos;
import com.mibiblioteka.api.services.generosService.ObtenerGenerosServices;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/generos/obtener")
public class ObtenerGenerosController {

    private final ObtenerGenerosServices generosService;

    public ObtenerGenerosController(ObtenerGenerosServices generosService) {
        this.generosService = generosService;
    }

    @GetMapping
    public List<Generos> obtenerGeneros(@RequestParam Map<String, Object> filtros) {
        // Si filtros está vacío, traerá todos los generos
        return generosService.obtenerGeneros(filtros);
    }

    @GetMapping("/{id}")
    public Generos obtenerGeneroPorId(@PathVariable String id) throws Exception {
        Map<String, Object> filtros = Map.of("id", id);
        List<Generos> resultado = generosService.obtenerGeneros(filtros);
        if (resultado.isEmpty()) {
            throw new Exception("Genero no encontrado con id: " + id);
        }
        return resultado.get(0);
    }

    @PostMapping("/filtrar")
    public List<Generos> filtrarGeneros(@RequestBody Map<String, Object> filtros) {
        return generosService.obtenerGeneros(filtros);
    }
}

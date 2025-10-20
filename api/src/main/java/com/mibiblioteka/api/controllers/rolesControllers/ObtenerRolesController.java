package com.mibiblioteka.api.controllers.rolesControllers;

import com.mibiblioteka.api.models.Roles;
import com.mibiblioteka.api.services.rolesService.ObtenerRolesServices;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/roles/obtener")
public class ObtenerRolesController {

    private final ObtenerRolesServices rolesService;

    public ObtenerRolesController(ObtenerRolesServices rolesService) {
        this.rolesService = rolesService;
    }

    @GetMapping
    public List<Roles> obtenerRoles(@RequestParam Map<String, Object> filtros) {
        // Si filtros está vacío, traerá todos los roles
        return rolesService.obtenerRoles(filtros);
    }

    @GetMapping("/{id}")
    public Roles obtenerRolPorId(@PathVariable String id) throws Exception {
        Map<String, Object> filtros = Map.of("id", id);
        List<Roles> resultado = rolesService.obtenerRoles(filtros);
        if (resultado.isEmpty()) {
            throw new Exception("Rol no encontrado con id: " + id);
        }
        return resultado.get(0);
    }

    @PostMapping("/filtrar")
    public List<Roles> filtrarRoles(@RequestBody Map<String, Object> filtros) {
        return rolesService.obtenerRoles(filtros);
    }
}

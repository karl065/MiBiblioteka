package com.mibiblioteka.api.controllers.usuariosControllers;

import com.mibiblioteka.api.models.Usuarios;
import com.mibiblioteka.api.services.usuariosService.ObtenerUsuariosService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/usuarios/obtener")
public class ObtenerUsuariosController {

    private final ObtenerUsuariosService obtenerUsuariosService;

    public ObtenerUsuariosController(ObtenerUsuariosService obtenerUsuariosService) {
        this.obtenerUsuariosService = obtenerUsuariosService;
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('SuperUsuario')")
    @GetMapping
    public List<Usuarios> obtenerUsuarios(@RequestParam Map<String, Object> filtros) {
        return obtenerUsuariosService.obtenerUsuarios(filtros);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<Usuarios> obtenerUsuarioPorId(@PathVariable String id) {
        Map<String, Object> filtros = Map.of("id", id);
        List<Usuarios> resultado = obtenerUsuariosService.obtenerUsuarios(filtros);
        if (resultado.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(resultado.get(0));
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('SuperUsuario')")
    @PostMapping("/filtrar")
    public List<Usuarios> filtrarUsuarios(@RequestBody Map<String, Object> filtros) {

        List<Usuarios> usuarios = obtenerUsuariosService.obtenerUsuarios(filtros);

        return ResponseEntity.ok(usuarios).getBody();
    }
}

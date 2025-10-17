package com.mibiblioteka.api.controllers.rolesControllers;

import com.mibiblioteka.api.models.Roles;
import com.mibiblioteka.api.services.rolesService.RolesServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles/obtener")
public class ObtenerRolesController {

    private final RolesServices rolesServices;

    @Autowired
    public ObtenerRolesController(RolesServices rolesServices) {
        this.rolesServices = rolesServices;
    }

    @GetMapping
    public List<Roles> obtenerRoles() {
        return rolesServices.obtenerRoles();
    }

    @GetMapping("/{id}")
    public Roles obtenerRolPorId(@PathVariable String id) throws Exception {
        return rolesServices.obtenerRolPorId(id)
                .orElseThrow(() -> new Exception("Rol no encontrado con id: " + id));
    }
}

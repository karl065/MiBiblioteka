package com.mibiblioteka.api.services.rolesService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.mibiblioteka.api.models.Roles;
import com.mibiblioteka.api.models.Usuarios;
import com.mibiblioteka.api.repository.usuariosRepository.UsuariosRepository;
import com.mibiblioteka.api.services.usuariosService.ActualizarUsuariosService;
import com.mibiblioteka.api.services.usuariosService.ObtenerUsuariosService;

@Service
public class EliminarRolesServices {

    private UsuariosRepository usuariosRepository;

    private ObtenerUsuariosService obtenerUsuariosServices;

    private ActualizarUsuariosService actualizarUsuariosServices;

    public EliminarRolesServices(UsuariosRepository usuariosRepository,
            ObtenerUsuariosService obtenerUsuariosServices,
            ActualizarUsuariosService actualizarUsuariosServices) {
        this.usuariosRepository = usuariosRepository;
        this.obtenerUsuariosServices = obtenerUsuariosServices;
        this.actualizarUsuariosServices = actualizarUsuariosServices;
    }

    public boolean eliminarRol(String id) {

        boolean eliminado = usuariosRepository.eliminar(id);

        if (!eliminado) {
            System.out.println("No se encontró el rol con id: " + id);
            return false;
        }

        Map<String, Object> filtros = Map.of(
                "roles.id", id);
        List<Usuarios> usuariosRelacionados = obtenerUsuariosServices.obtenerUsuarios(filtros);
        for (Usuarios usuario : usuariosRelacionados) {
            List<Roles> rolesActualizados = usuario.getRol().stream()
                    .filter(u -> !u.getId().equals(id))
                    .collect(Collectors.toList());

            Usuarios usuarioActualizado = new Usuarios();
            usuarioActualizado.setRol(rolesActualizados);

            actualizarUsuariosServices.actualizarUsuario(usuario.getId(), usuarioActualizado);
            System.out.println("Rol removido del usuario: " + usuario.getNombre());
        }
        return true;
    }

}

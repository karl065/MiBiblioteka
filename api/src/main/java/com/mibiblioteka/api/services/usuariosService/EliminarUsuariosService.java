package com.mibiblioteka.api.services.usuariosService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.mibiblioteka.api.models.Roles;
import com.mibiblioteka.api.models.Usuarios;
import com.mibiblioteka.api.repository.usuariosRepository.UsuariosRepository;
import com.mibiblioteka.api.services.rolesService.ActualizarRolesServices;
import com.mibiblioteka.api.services.rolesService.ObtenerRolesServices;

@Service
public class EliminarUsuariosService {

    private final UsuariosRepository usuariosRepository;
    private final ActualizarRolesServices actualizarRolesServices;
    private final ObtenerRolesServices obtenerRolesServices;
    private final ObtenerUsuariosService obtenerUsuariosService;

    public EliminarUsuariosService(
            UsuariosRepository usuariosRepository,
            ActualizarRolesServices actualizarRolesServices,
            ObtenerRolesServices obtenerRolesServices,
            ObtenerUsuariosService obtenerUsuariosService) {

        this.usuariosRepository = usuariosRepository;
        this.actualizarRolesServices = actualizarRolesServices;
        this.obtenerRolesServices = obtenerRolesServices;
        this.obtenerUsuariosService = obtenerUsuariosService;
    }

    public boolean eliminarUsuario(String id) {
        try {
            // 1️⃣ Buscar el usuario antes de eliminarlo
            Optional<Usuarios> usuarioOpt = obtenerUsuariosService.obtenerUsuariosPorID(id);
            if (usuarioOpt.isEmpty()) {
                System.out.println("❌ No se encontró el usuario con id: " + id);
                return false;
            }

            Usuarios usuario = usuarioOpt.get();
            System.out.println("🔍 Usuario encontrado: " + usuario.getNombre());

            // 2️⃣ Eliminar el usuario de la colección Usuarios
            boolean eliminado = usuariosRepository.eliminar(id);
            if (!eliminado) {
                System.out.println("❌ No se pudo eliminar el usuario con id: " + id);
                return false;
            }
            System.out.println("✅ Usuario eliminado correctamente");

            // 3️⃣ Obtener los roles asociados al usuario (por sus IDs en el campo rol)
            if (usuario.getRol() == null || usuario.getRol().isEmpty()) {
                System.out.println("ℹ️ El usuario no tenía roles asignados");
                return true;
            }

            // Convertir a lista de strings (IDs de roles)
            List<String> rolesIds = usuario.getRol().stream()
                    .map(Object::toString)
                    .collect(Collectors.toList());

            // Obtener los roles desde la base
            List<Roles> rolesRelacionados = rolesIds.stream()
                    .map(obtenerRolesServices::obtenerRolPorId) // necesitas agregar este método
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(Collectors.toList());

            System.out.println("🔗 Roles relacionados encontrados: " + rolesRelacionados.size());

            // 4️⃣ Quitar el usuario eliminado de cada rol
            for (Roles rol : rolesRelacionados) {
                List<Usuarios> usuariosActualizados = rol.getUsuarios().stream()
                        .filter(u -> !u.getId().equals(id))
                        .collect(Collectors.toList());

                rol.setUsuarios(usuariosActualizados);
                actualizarRolesServices.actualizarRol(rol.getId(), rol);
                System.out.println("🧹 Usuario removido del rol: " + rol.getNombre());

            }

            return true;

        } catch (Exception e) {
            System.err.println("🚨 Error al eliminar usuario: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}

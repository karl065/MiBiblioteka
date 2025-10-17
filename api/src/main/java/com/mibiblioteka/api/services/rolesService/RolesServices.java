package com.mibiblioteka.api.services.rolesService;

import com.mibiblioteka.api.models.Roles;
import com.mibiblioteka.api.models.Usuarios;
import com.mibiblioteka.api.repository.rolesRepository.RolesRepository;
import com.mibiblioteka.api.repository.usuariosRepository.UsuariosRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RolesServices {

    private final RolesRepository rolesRepository;
    private final UsuariosRepository usuariosRepository;

    @Autowired
    public RolesServices(RolesRepository rolesRepository, UsuariosRepository usuariosRepository) {
        this.rolesRepository = rolesRepository;
        this.usuariosRepository = usuariosRepository;
    }

    // Crear un nuevo rol
    public Roles crearRol(Roles rol) throws Exception {
        if (rolesRepository.existsByNombre(rol.getNombre())) {
            throw new Exception("El rol '" + rol.getNombre() + "' ya existe.");
        }
        if (rol.getUsuarios() == null) {
            rol.setUsuarios(new ArrayList<>()); // Inicializar lista de usuarios
        }
        return rolesRepository.save(rol);
    }

    // Obtener todos los roles
    public List<Roles> obtenerRoles() {
        return rolesRepository.findAll();
    }

    // Buscar rol por ID
    public Optional<Roles> obtenerRolPorId(String id) {
        return rolesRepository.findById(id);
    }

    // Buscar rol por nombre
    public Optional<Roles> obtenerRolPorNombre(String nombre) {
        return rolesRepository.findByNombre(nombre);
    }

    // Actualizar rol
    public Roles actualizarRol(String id, Roles rolActualizado) throws Exception {
        Roles rolExistente = rolesRepository.findById(id)
                .orElseThrow(() -> new Exception("Rol no encontrado con id: " + id));

        rolExistente.setNombre(rolActualizado.getNombre());
        rolExistente.setDescripcion(rolActualizado.getDescripcion());

        // Si se envían usuarios, actualizar lista
        if (rolActualizado.getUsuarios() != null) {
            rolExistente.setUsuarios(rolActualizado.getUsuarios());
        }

        return rolesRepository.save(rolExistente);
    }

    // Eliminar rol
    public void eliminarRol(String id) throws Exception {
        if (!rolesRepository.existsById(id)) {
            throw new Exception("Rol no encontrado con id: " + id);
        }
        rolesRepository.deleteById(id);
    }

    // Agregar un usuario a un rol por nombre
    public void agregarUsuarioAlRol(String nombreRol, String usuarioId) throws Exception {
        Roles rol = rolesRepository.findByNombre(nombreRol)
                .orElseThrow(() -> new Exception("Rol no encontrado: " + nombreRol));

        Usuarios usuario = usuariosRepository.findById(usuarioId)
                .orElseThrow(() -> new Exception("Usuario no encontrado: " + usuarioId));

        if (rol.getUsuarios() == null) {
            rol.setUsuarios(new ArrayList<>());
        }

        boolean existe = rol.getUsuarios().stream()
                .anyMatch(u -> u.getId().equals(usuarioId));

        if (!existe) {
            rol.getUsuarios().add(usuario);
            rolesRepository.save(rol);
        }
    }

    // Quitar un usuario de un rol
    public void quitarUsuarioDelRol(String nombreRol, String usuarioId) throws Exception {
        Roles rol = rolesRepository.findByNombre(nombreRol)
                .orElseThrow(() -> new Exception("Rol no encontrado: " + nombreRol));

        if (rol.getUsuarios() != null) {
             boolean removed = rol.getUsuarios().removeIf(u -> u.getId().equals(usuarioId));
        if (removed) {
            rolesRepository.save(rol);
        }
        }
    }
}

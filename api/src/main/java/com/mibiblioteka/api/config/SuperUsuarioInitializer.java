package com.mibiblioteka.api.config;

import com.mibiblioteka.api.models.Roles;
import com.mibiblioteka.api.models.Usuarios;
import com.mibiblioteka.api.services.rolesService.ActualizarRolesServices;
import com.mibiblioteka.api.services.rolesService.CrearRolesServices;
import com.mibiblioteka.api.services.rolesService.ObtenerRolesServices;
import com.mibiblioteka.api.services.usuariosService.CrearUsuariosService;
import com.mibiblioteka.api.services.usuariosService.ObtenerUsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class SuperUsuarioInitializer {

    private ObtenerRolesServices obtenerRolesServices;

    private CrearUsuariosService crearUsuarioService;

    private ObtenerUsuariosService obtenerUsuariosService;

    private CrearRolesServices crearRolesServices;

    private ActualizarRolesServices actualizarRolesServices;

    @Value("${superusuario.nombre}")
    private String nombre;

    @Value("${superusuario.celular}")
    private String celular;

    @Value("${superusuario.correo}")
    private String correo;

    @Value("${superusuario.password}")
    private String password;

    @Autowired
    public SuperUsuarioInitializer(ObtenerRolesServices obtenerRolesServices,
            CrearUsuariosService crearUsuarioService,
            ObtenerUsuariosService obtenerUsuariosService,
            CrearRolesServices crearRolesServices,
            ActualizarRolesServices actualizarRolesServices) {
        this.obtenerRolesServices = obtenerRolesServices;
        this.crearUsuarioService = crearUsuarioService;
        this.obtenerUsuariosService = obtenerUsuariosService;
        this.crearRolesServices = crearRolesServices;
        this.actualizarRolesServices = actualizarRolesServices;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void initSuperUsuario() {
        try {

            List<Roles> rolesExistentes = obtenerRolesServices.obtenerRoles(Map.of());
            Roles superRol;

            if (rolesExistentes.isEmpty()) {
                superRol = new Roles();
                superRol.setNombre("SuperUsuario");
                superRol.setDescripcion("Rol con todos los permisos");
                superRol = crearRolesServices.crearRol(superRol);
                System.out.println("Rol SuperUsuario creado.");
            } else {
                superRol = rolesExistentes.get(0);
                System.out.println("Rol SuperUsuario ya existe.");
            }

            List<Usuarios> usuariosAsignados = superRol.getUsuarios();
            if (usuariosAsignados != null && !usuariosAsignados.isEmpty()) {
                System.out.println("Ya existe un usuario asignado al rol SuperUsuario, no se hace nada.");
                return;
            }

            // 3. Verificar si existe algún usuario en la DB
            List<Usuarios> todosUsuarios = obtenerUsuariosService.obtenerUsuarios(Map.of());
            if (!todosUsuarios.isEmpty()) {
                System.out.println("Ya existen usuarios en la base de datos, no se crea el superusuario.");
                return;
            }

            // 4. Crear el superusuario
            Usuarios superUsuario = new Usuarios();
            superUsuario.setNombre(nombre);
            superUsuario.setCelular(celular);
            superUsuario.setCorreo(correo);
            superUsuario.setPassword(password);
            superUsuario.setRol(List.of(superRol));

            Usuarios creado = crearUsuarioService.crearUsuario(superUsuario);

            Roles rolActualizado = new Roles();
            rolActualizado.setUsuarios(List.of(creado));
            actualizarRolesServices.actualizarRol(superRol.getId(), rolActualizado);

            System.out.println("Superusuario creado exitosamente: " + creado.getCorreo());

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error al inicializar SuperUsuario: " + e.getMessage());
        }
    }
}

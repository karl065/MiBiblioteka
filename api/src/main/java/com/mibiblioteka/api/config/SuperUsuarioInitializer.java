package com.mibiblioteka.api.config;

import com.mibiblioteka.api.models.Roles;
import com.mibiblioteka.api.models.Usuarios;
import com.mibiblioteka.api.services.rolesService.RolesServices;
import com.mibiblioteka.api.services.usuariosService.CrearUsuariosService;
import com.mibiblioteka.api.services.usuariosService.ObtenerUsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SuperUsuarioInitializer {

    @Autowired
    private RolesServices rolesServices;

    @Autowired
    private CrearUsuariosService crearUsuarioService;

    @Autowired
    private ObtenerUsuariosService obtenerUsuariosService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Variables de entorno
    @Value("${superusuario.nombre}")
    private String nombre;

    @Value("${superusuario.celular}")
    private String celular;

    @Value("${superusuario.correo}")
    private String correo;

    @Value("${superusuario.password}")
    private String password;

    @EventListener(ApplicationReadyEvent.class)
    public void initSuperUsuario() {
        try {
            
            Roles superRol = rolesServices.obtenerRolPorNombre("SuperUsuario")
                    .orElseGet(() -> {
                        try {
                            Roles nuevoRol = new Roles();
                            nuevoRol.setNombre("SuperUsuario");
                            nuevoRol.setDescripcion("Rol con todos los permisos");
                            return rolesServices.crearRol(nuevoRol);
                        } catch (Exception e) {
                            throw new RuntimeException("Error creando rol SuperUsuario", e);
                        }
                    });

            
            List<Usuarios> usuariosAsignados = superRol.getUsuarios();
            if (usuariosAsignados != null && !usuariosAsignados.isEmpty()) {
                System.out.println("Ya existe un usuario asignado al rol SuperUsuario, no se hace nada.");
                return;
            }

            // 3. Verificar si existe algún usuario en la DB
            List<Usuarios> todosUsuarios = obtenerUsuariosService.obtenerTodos();
            if (!todosUsuarios.isEmpty()) {
                System.out.println("Ya existen usuarios en la base de datos, no se crea el superusuario.");
                return;
            }

            // 4. Crear el superusuario
            Usuarios superUsuario = new Usuarios();
            superUsuario.setNombre(nombre);
            superUsuario.setCelular(celular);
            superUsuario.setCorreo(correo);
            superUsuario.setPassword(passwordEncoder.encode(password));
            superUsuario.setRol(List.of(superRol));

            Usuarios creado = crearUsuarioService.crearUsuariosServices(superUsuario);

            
            rolesServices.agregarUsuarioAlRol(superRol.getNombre(), creado.getId());

            System.out.println("Superusuario creado exitosamente: " + creado.getCorreo());

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error al inicializar SuperUsuario: " + e.getMessage());
        }
    }
}


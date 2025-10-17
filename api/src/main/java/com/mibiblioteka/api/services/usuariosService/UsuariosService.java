package com.mibiblioteka.api.services.usuariosService;

import com.mibiblioteka.api.helpers.jwt.JwtUtils;
import com.mibiblioteka.api.models.Usuarios;
import com.mibiblioteka.api.repository.usuariosRepository.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuariosService {
    
    @Autowired
    private UsuariosRepository usuariosRepository;

    @Autowired
    private JwtUtils jwtUtils;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // CRUD
    public Usuarios crearUsuario(Usuarios usuario) {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return usuariosRepository.save(usuario);
    }

    public List<Usuarios> obtenerTodos() {
        return usuariosRepository.findAll();
    }

    public Optional<Usuarios> obtenerPorId(String id) {
        return usuariosRepository.findById(id);
    }

    public Usuarios actualizarUsuario(String id, Usuarios usuario) {
        return usuariosRepository.findById(id).map(u -> {
            u.setNombre(usuario.getNombre());
            if (usuario.getPassword() != null && !usuario.getPassword().isEmpty()) {
                u.setPassword(passwordEncoder.encode(usuario.getPassword()));
            }
            u.setRol(usuario.getRol());
            return usuariosRepository.save(u);
        }).orElse(null);
    }

    public void eliminarUsuario(String id) {
        usuariosRepository.deleteById(id);
    }

    // Login
    public String login(String correo, String password) {
    Optional<Usuarios> usuarioOpt = usuariosRepository.findByCorreo(correo);
    if (usuarioOpt.isPresent()) {
        Usuarios usuario = usuarioOpt.get();
        if (passwordEncoder.matches(password, usuario.getPassword())) {
            // Convertir el rol a lista de strings
            List<String> roles = usuario.getRol() == null ? List.of() :
                    usuario.getRol().stream()
                            .map(rol -> rol.getNombre())
                            .toList();
            return jwtUtils.generateToken(usuario.getCorreo(), roles);
        }
    }
    return null;
}
}

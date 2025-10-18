package com.mibiblioteka.api.services.usuariosService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mibiblioteka.api.helpers.jwt.JwtUtils;
import com.mibiblioteka.api.models.Usuarios;
import com.mibiblioteka.api.repository.usuariosRepository.UsuariosRepository;

import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    private UsuariosRepository usuariosRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private JwtUtils jwtUtils;
            
    public String LoginServices(String correo, String password) {
        try {
            System.out.println("🟢 Intentando login para correo: " + correo);

            // Buscar usuario por correo
            Optional<Usuarios> usuarioOpt = usuariosRepository.findByCorreo(correo);
            System.out.println("📬 Usuario encontrado: " + usuarioOpt.isPresent());

            if (usuarioOpt.isEmpty()) {
                System.out.println("❌ No existe un usuario con ese correo.");
                return null;
            }

            Usuarios usuario = usuarioOpt.get(); 

            System.out.println("🔍 Verificando contraseña para usuario: " + usuario.getCorreo() + " y password: " + usuario.getPassword());

            // Verificar contraseña
            boolean passwordMatch = passwordEncoder.matches(password, usuario.getPassword());
            System.out.println("🔐 Contraseña coincide: " + passwordMatch);

            if (!passwordMatch) {
                System.out.println("❌ Contraseña incorrecta.");
                return null;
            }

            // Generar token si todo está correcto
            List<String> roles = usuario.getRol() == null ? List.of() :
                    usuario.getRol().stream().map(r -> r.getNombre()).toList();

            String token = jwtUtils.generateToken(usuario.getCorreo(), roles);
            System.out.println("✅ Token generado correctamente para: " + usuario.getCorreo());

            return token;

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("⚠️ Error en LoginServices: " + e.getMessage());
            return null;
        }
    }
}

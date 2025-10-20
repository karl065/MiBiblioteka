package com.mibiblioteka.api.services.usuariosService;

import com.mibiblioteka.api.helpers.filtros.FiltroHelper;
import com.mibiblioteka.api.helpers.jwt.JwtUtils;
import com.mibiblioteka.api.models.Usuarios;
import com.mibiblioteka.api.repository.usuariosRepository.UsuariosRepository;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class LoginService {

    private final UsuariosRepository usuariosRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    public LoginService(
            UsuariosRepository usuariosRepository,
            PasswordEncoder passwordEncoder,
            JwtUtils jwtUtils
    ) {
        this.usuariosRepository = usuariosRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
    }

    /**
     * Valida las credenciales del usuario y genera el token JWT.
     */
    public String LoginServices(String correo, String password) {
        try {
            // Construir filtro dinámico
            Map<String, Object> filtros = Map.of("correo", correo);

            Query query = FiltroHelper.construirQuery(filtros, Usuarios.class);
            List<Usuarios> resultado = usuariosRepository.buscar(query);

            if (resultado.isEmpty()) {
                System.out.println("❌ No existe un usuario con ese correo.");
                return null;
            }

            Usuarios usuario = resultado.get(0);

            // Validar contraseña
            boolean passwordMatch = passwordEncoder.matches(password.trim(), usuario.getPassword().trim());
            if (!passwordMatch) {
                System.out.println("❌ Contraseña incorrecta.");
                return null;
            }

            // Obtener roles del usuario
            List<String> roles = usuario.getRol() == null
                    ? List.of()
                    : usuario.getRol().stream()
                            .map(r -> r.getNombre())
                            .toList();

            // Generar token
            String token = jwtUtils.generateToken(usuario.getCorreo(), roles);

            System.out.println("✅ Login exitoso para " + usuario.getCorreo());
            return token;

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("⚠️ Error en LoginService: " + e.getMessage());
            return null;
        }
    }
}

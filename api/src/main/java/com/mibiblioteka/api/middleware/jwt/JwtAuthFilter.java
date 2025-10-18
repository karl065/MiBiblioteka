package com.mibiblioteka.api.middleware.jwt;

import com.mibiblioteka.api.helpers.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;

    // 🔹 Rutas públicas que no deben requerir token
    private static final List<String> PUBLIC_ENDPOINTS = List.of(
        "/auth/login",
        "/auth/register",
        "/public"
    );

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        String path = request.getRequestURI();

        // 🔸 1. Saltar validación JWT si es una ruta pública
        if (PUBLIC_ENDPOINTS.stream().anyMatch(path::startsWith)) {
            filterChain.doFilter(request, response);
            return;
        }

        // 🔸 2. Obtener header Authorization
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);

            try {
                // 🔸 3. Validar token
                if (jwtUtils.validateToken(token)) {
                    // Extraer datos del token
                    String correo = jwtUtils.getCorreoFromToken(token);
                    List<String> roles = jwtUtils.getRolesFromToken(token);

                    // Crear lista de autoridades
                    List<SimpleGrantedAuthority> authorities = roles.stream()
                            .map(SimpleGrantedAuthority::new)
                            .collect(Collectors.toList());

                    // Crear autenticación
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(correo, null, authorities);

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                } else {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.getWriter().write("Token inválido o expirado");
                    return;
                }
            } catch (Exception e) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Error al validar el token: " + e.getMessage());
                return;
            }

        } else {
            // 🔸 4. Si no hay token y no es ruta pública, denegar
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Token no proporcionado");
            return;
        }

        // 🔸 5. Continuar con el filtro si todo es correcto
        filterChain.doFilter(request, response);
    }
}

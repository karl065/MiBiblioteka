package com.mibiblioteka.api.helpers.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import java.security.Key;
import java.util.Date;
import java.util.List;

@Component
public class JwtUtils {

    private Key key;

    @Value("${jwt.secret}")
    private String secret; // Leer de .env

    @Value("${jwt.expiration}")
    private long expirationTimeInMillis;

    @PostConstruct
    public void init() {
        if (secret == null || secret.isEmpty()) {
            throw new IllegalStateException("❌ SECRET_KEY no definida en variables de entorno. " +
                    "Por favor agrega SECRET_KEY en tu .env con al menos 32 caracteres.");
        }

        if (secret.getBytes().length < 32) {
            throw new IllegalStateException("❌ SECRET_KEY demasiado corta. " +
                    "Se requieren al menos 32 bytes para HS256. " +
                    "Actualmente tu clave tiene " + secret.getBytes().length + " bytes.");
        }

        key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    // Generar token con correo y roles
    public String generateToken(String subject, List<String> roles) {
        return Jwts.builder()
                .setSubject(subject)
                .claim("roles", roles)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTimeInMillis))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
        
    }

    // Validar token
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    // Obtener username desde token
    public String getCorreoFromToken(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    // Obtener roles desde token
    @SuppressWarnings("unchecked")
    public List<String> getRolesFromToken(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token).getBody();
        return (List<String>) claims.get("roles");
    }
}

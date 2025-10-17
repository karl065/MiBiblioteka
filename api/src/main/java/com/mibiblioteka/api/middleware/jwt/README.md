# JwtAuthFilter

`JwtAuthFilter` es un filtro de Spring Security que valida los **tokens JWT** en
cada request a rutas protegidas.

## Ubicación

```bash
com.mibiblioteka.api.middleware.jwt.JwtAuthFilter
```

## Funcionalidades

- Intercepta todas las requests protegidas.
- Lee el header `Authorization` con token Bearer.
- Valida el token usando `JwtUtils`.
- Bloquea requests si:
  - El token no está presente
  - El token es inválido o ha expirado
- Setea el contexto de seguridad (`SecurityContextHolder`) con:
  - Username
  - Roles convertidos en `SimpleGrantedAuthority`
- Permite proteger rutas con Spring Security (`@PreAuthorize`, etc.)

## Uso

```java
@Autowired
private JwtAuthFilter jwtAuthFilter;

// El filtro se registra automáticamente en Spring Boot si está anotado con @Component
// Protege rutas configuradas en tu SecurityConfig
```

## [**Atrás**](../README.md)

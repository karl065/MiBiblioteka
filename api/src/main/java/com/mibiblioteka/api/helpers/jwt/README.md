# JwtUtils

`JwtUtils` es un helper que proporciona funcionalidades para **crear y validar
tokens JWT** en la aplicación. Se integra con Spring Boot y permite manejar
autenticación basada en tokens.

## Ubicación

`com.mibiblioteka.api.helpers.jwt.JwtUtils`

## Funcionalidades

- Generar token JWT con:
  - `username`
  - `roles` (lista de roles del usuario)
  - Fecha de expiración configurable
- Validar token JWT
- Obtener `username` desde el token
- Obtener `roles` desde el token

## Uso

```java
@Autowired
private JwtUtils jwtUtils;

// Generar token
String token = jwtUtils.generateToken("usuario1", List.of("ADMIN"));

// Validar token
boolean valido = jwtUtils.validateToken(token);

// Obtener información
String username = jwtUtils.getUsernameFromToken(token);
List<String> roles = jwtUtils.getRolesFromToken(token);
```

## [**Atrás**](../README.md)

# Middleware

El módulo **`middleware`** contiene filtros y componentes que se ejecutan
**antes o durante las requests** para controlar el flujo de la aplicación, como
autenticación, autorización y otras validaciones globales.

---

## Submódulos disponibles

| Middleware        | Descripción                                                                                                                         | Link al README                                  |
| ----------------- | ----------------------------------------------------------------------------------------------------------------------------------- | ----------------------------------------------- |
| **JwtAuthFilter** | Filtro de Spring Security que valida tokens JWT, bloquea requests sin token y setea el contexto de seguridad para rutas protegidas. | [**Ver README JwtAuthFilter**](./jwt/README.md) |

---

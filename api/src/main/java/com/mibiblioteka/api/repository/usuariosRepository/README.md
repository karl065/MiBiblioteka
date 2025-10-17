# UsuariosRepository

Repositorio de MongoDB para la colección `usuarios`.

## Funcionalidades

- Operaciones CRUD básicas mediante `MongoRepository`.
- Buscar usuario por correo.
- Verificar existencia de correo.

## Métodos principales

```java
Optional<Usuarios> findByCorreo(String correo);
boolean existsByCorreo(String correo);
```

## Uso

```java
Optional<Usuarios> usuario = usuariosRepository.findByCorreo("correo@ejemplo.com");
boolean existe = usuariosRepository.existsByCorreo("correo@ejemplo.com");
```

## Dependencias

- MongoRepository de Spring Data MongoDB.

- Modelo Usuarios.

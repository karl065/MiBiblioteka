# 📦 Módulo: Modelos (Entidades)

Este módulo define la estructura de datos principal del sistema
**MiBiblioteka**.  
Cada modelo representa una colección en **MongoDB** y está diseñado para ser
flexible, escalable y analítico.

---

## 🧱 Modelos principales

| Entidad         | Descripción                                                                                                         |
| --------------- | ------------------------------------------------------------------------------------------------------------------- |
| **Libros**      | Contiene la información del libro, su estado, historial de préstamos, lectores y métricas.                          |
| **Autores**     | Representa al autor del libro (nombre, nacionalidad).                                                               |
| **Generos**     | Clasificación literaria del libro (novela, poesía, etc.).                                                           |
| **EstadoLibro** | Define los estados configurables de un libro (Almacenado, Prestado, Devuelto, etc.).                                |
| **Usuarios**    | Representa a los lectores o administradores del sistema. Contiene campos para autenticación y control de préstamos. |
| **Roles**       | Define los permisos de acceso del usuario (ADMIN, LECTOR, BIBLIOTECARIO, etc.).                                     |

---

## 🔁 Relaciones entre modelos

- `Libros` → `Autores`, `Generos`, `EstadoLibro`, `Usuarios`
- `Usuarios` → `Roles`, `Libros` (prestados, leídos)
- `Roles` → _(sin dependencias directas)_
- `EstadoLibro` → _(sin dependencias directas)_

---

## 📚 Estructura y propósito de cada modelo

### 📘 **Libros**

Representa la unidad principal del sistema.  
Cada libro puede tener un estado, un historial de lectores, y múltiples fechas
de préstamo y devolución.

| Campo                                     | Tipo             | Descripción                                         |
| ----------------------------------------- | ---------------- | --------------------------------------------------- |
| `id`                                      | String           | Identificador único (mapeado con `_id` de MongoDB). |
| `titulo`                                  | String           | Nombre del libro.                                   |
| `autor`                                   | Autor            | Referencia al autor.                                |
| `genero`                                  | Genero           | Referencia al género literario.                     |
| `estado`                                  | EstadoLibro      | Estado actual del libro.                            |
| `usuarioPrestamo`                         | Usuario          | Usuario que actualmente tiene el libro.             |
| `lectores[]`                              | List\<Usuario>   | Historial de usuarios que lo leyeron.               |
| `fechaPrestamo` / `fechaDevolucion`       | LocalDate        | Fechas del préstamo actual.                         |
| `fechasPrestamo[]` / `fechasDevolucion[]` | List\<LocalDate> | Historial de fechas.                                |
| `vecesPrestado`                           | int              | Número de veces que el libro fue prestado.          |

---

### ✍️ **Autores**

Define los datos básicos del autor.

| Campo          | Tipo   | Descripción                    |
| -------------- | ------ | ------------------------------ |
| `id`           | String | Identificador único.           |
| `nombre`       | String | Nombre del autor.              |
| `nacionalidad` | String | País o nacionalidad del autor. |

---

### 🏷️ **Generos**

Representa la clasificación literaria de los libros.

| Campo    | Tipo   | Descripción                                         |
| -------- | ------ | --------------------------------------------------- |
| `id`     | String | Identificador único.                                |
| `nombre` | String | Nombre del género (Ej: Fantasía, Historia, Poesía). |

---

### 📊 **EstadoLibro**

Define los estados dinámicos de los libros.  
Permite agregar nuevos estados sin modificar el código.

| Campo         | Tipo   | Descripción                                                   |
| ------------- | ------ | ------------------------------------------------------------- |
| `id`          | String | Identificador único.                                          |
| `nombre`      | String | Nombre del estado (Ej: "Prestado", "Devuelto", "Extraviado"). |
| `descripcion` | String | Descripción breve del estado.                                 |

---

### 👤 **Usuarios**

Representa a los lectores o administradores del sistema.  
Incluye credenciales seguras, roles, y control de préstamos.

| Campo               | Tipo         | Descripción                                   |
| ------------------- | ------------ | --------------------------------------------- |
| `id`                | String       | Identificador único.                          |
| `nombre`            | String       | Nombre completo del usuario.                  |
| `correo`            | String       | Correo electrónico (único).                   |
| `password`          | String       | Contraseña encriptada con BCrypt.             |
| `celular`           | String       | Número telefónico del usuario.                |
| `rol`               | Rol          | Rol o permiso asignado.                       |
| `activo`            | boolean      | Indica si la cuenta está habilitada.          |
| `librosPrestados[]` | List\<Libro> | Libros que tiene actualmente en préstamo.     |
| `librosLeidos[]`    | List\<Libro> | Libros que el usuario ya ha leído o devuelto. |

🔒 **Seguridad:**  
Las contraseñas se encriptan usando `BCryptPasswordEncoder` (Spring Security).  
Nunca deben almacenarse en texto plano.

---

### 🧩 **Roles**

Define los diferentes niveles de acceso o permisos dentro del sistema.

| Campo         | Tipo   | Descripción                             |
| ------------- | ------ | --------------------------------------- |
| `id`          | String | Identificador único.                    |
| `nombre`      | String | Nombre del rol (Ej: "ADMIN", "LECTOR"). |
| `descripcion` | String | Descripción del rol.                    |

---

## 📈 Características clave

- **Escalable:** Los estados de los libros y los roles se pueden ampliar
  dinámicamente.
- **Analítico:** Permite generar estadísticas como libros más prestados o
  usuarios más activos.
- **Histórico:** Cada libro guarda fechas y usuarios relacionados con sus
  préstamos.
- **Seguro:** Contraseñas cifradas y estructura preparada para autenticación
  JWT.
- **Relacional:** Uso de `@DBRef` para conectar entidades sin duplicar datos.

---

## 🧮 Ejemplo de relaciones visuales

```bash
Usuario
├── rol: Rol
├── librosPrestados[] → Libro
└── librosLeidos[] → Libro

Libro
├── autor: Autor
├── genero: Genero
├── estado: EstadoLibro
├── usuarioPrestamo: Usuario
└── lectores[] → Usuario
```

---

## 🗂️ Colecciones MongoDB esperadas

- libros
- autores
- generos
- estados_libros
- usuarios
- roles

---

## 🧠 Notas adicionales

- Todos los modelos usan anotaciones `@Document`, `@Id`, `@DBRef` y validaciones
  `@NotBlank` / `@Email`.
- Los IDs son generados automáticamente por MongoDB (`_id`) y mapeados a `id` en
  las clases.
- Las relaciones y validaciones están optimizadas para uso con Spring Data
  MongoDB y API REST.

---

📘 **Autor:** Carlos Castellanos

💼 **Proyecto:** MiBiblioteka

📦 **Instancia:** Backend (Módulo Modelos)

---

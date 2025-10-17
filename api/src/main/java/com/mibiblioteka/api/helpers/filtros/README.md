# 🧪 FiltroHelper

- El módulo FiltroHelper proporciona utilidades para construir consultas
  dinámicas de MongoDB a partir de filtros complejos. Permite manejar distintos
  tipos de datos, rangos y combinaciones lógicas sin que el controlador necesite
  lógica adicional.

## 📍 Ubicación

```bash
package com.mibiblioteka.api.helpers.filtros
```

## 🎮 Funcionalidad

- Construye un objeto Query de Spring Data MongoDB a partir de un conjunto de
  filtros.

- Soporta filtros de tipo:

  - String → búsqueda insensible a mayúsculas/minúsculas con regex.

  - Number → valores individuales o rangos (min, max).

  - Boolean → valor directo.

  - Date → valor individual o rangos (inicio, fin).

- Permite rangos parciales, por ejemplo:

  - Número: { "min": 18 } → desde 18 en adelante.

  - Fecha: { "fin": "2025-12-31" } → hasta esa fecha.

- Soporta combinaciones lógicas AND/OR anidadas usando claves "AND" y "OR".

---

## 🧬 Estructura de filtros

- Filtros simples

```bash
{
  "nombre": "Carlos",
  "edad": 25,
  "activo": true,
  "fechaRegistro": "2025-01-01"
}
```

- Rangos de números o fechas

```bash
{
  "edad": { "min": 18, "max": 30 },
  "fechaRegistro": { "inicio": "2025-01-01", "fin": "2025-12-31" }
}
```

- Puedes enviar solo min o max para filtrar rangos parciales.

- Puedes enviar solo inicio o fin para fechas parciales.

---

- Combinaciones AND/OR

```bash
{
  "AND": [
    { "nombre": "Carlos" },
    { "edad": { "min": 18, "max": 30 } },
    { "OR": [
        { "activo": true },
        { "rol": "admin" }
    ]}
  ]
}
```

- Esto se interpreta como:

```bash
(nombre = "Carlos" AND edad entre 18-30 AND (activo = true OR rol = "admin"))

```

---

## 🎆 Uso en controlador

```bash
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private MongoTemplate mongoTemplate;

    @PostMapping("/buscar")
    public List<Usuario> buscarUsuarios(@RequestBody Map<String, Object> filtros) {
        Query query = FiltroHelper.construirQuery(filtros);
        return mongoTemplate.find(query, Usuario.class);
    }
}
```

- Solo envías el objeto de filtros en el cuerpo de la petición.

- El helper construye automáticamente la query lista para MongoDB.

---

## 🧱 Ejemplo completo de uso

```bash

Map<String, Object> filtros = Map.of(
    "AND", new Map[]{
        Map.of("nombre", "Carlos"),
        Map.of("edad", Map.of("min", 18)),               // solo min
        Map.of("fechaRegistro", Map.of("fin", "2025-12-31")), // solo fin
        Map.of("OR", new Map[]{
            Map.of("activo", true),
            Map.of("rol", "admin")
        })
    }
);

Query query = FiltroHelper.construirQuery(filtros);
List<Usuario> resultados = mongoTemplate.find(query, Usuario.class);
```

---

## 🩺 Beneficios

- Filtrado flexible y dinámico con un solo helper.

- Compatible con múltiples tipos de datos y rangos parciales.

- Combinaciones lógicas AND/OR recursivas.

- Permite centralizar la lógica de filtrado y mantener los controladores
  limpios.

## [**Atrás**](../README.md)

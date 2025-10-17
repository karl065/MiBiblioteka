package com.mibiblioteka.api.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Document(collection = "estados_libros")
public class EstadoLibro {
    
     @Id
    private String id;

    @NotBlank(message = "El nombre del estado es obligatorio")
    private String nombre; 
    // Ejemplo: "Almacenado", "Prestado", "Devuelto", "Extraviado", "Reservado", etc.

    private String descripcion;
}

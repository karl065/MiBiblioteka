package com.mibiblioteka.api.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Document(collection = "roles")
public class Roles {
    
     @Id
    private String id;

    @NotBlank(message = "El nombre del rol es obligatorio")
    private String nombre; 
    // Ejemplo: "ADMIN", "LECTOR", "BIBLIOTECARIO"

    private String descripcion;
}

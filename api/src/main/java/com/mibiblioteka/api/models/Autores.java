package com.mibiblioteka.api.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Document(collection = "autores")
public class Autores {
    
     @Id
    private String id;

    @NotBlank(message = "El nombre del autor es obligatorio")
    private String nombre;

    private String nacionalidad;
}

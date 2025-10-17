package com.mibiblioteka.api.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Document(collection = "generos")
public class Generos {
    
     @Id
    private String id;

    @NotBlank(message = "El nombre del género es obligatorio")
    private String nombre;
}

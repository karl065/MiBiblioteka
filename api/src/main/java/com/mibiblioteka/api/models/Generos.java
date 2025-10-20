package com.mibiblioteka.api.models;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")

@Data
@Document(collection = "generos")
public class Generos {

    @Id
    private String id;

    @NotBlank(message = "El nombre del género es obligatorio")
    private String nombre;

    @DBRef
    private List<Libros> libros = new ArrayList<>();

    @Override
    public String toString() {
        return "Generos{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", librosCount=" + (libros != null ? libros.size() : 0) +
                '}';
    }

}

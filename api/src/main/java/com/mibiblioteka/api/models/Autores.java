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
@Document(collection = "autores")
public class Autores {

    @Id
    private String id;

    @NotBlank(message = "El nombre del autor es obligatorio")
    private String nombre;

    private String nacionalidad;

    @DBRef
    private List<Libros> libros = new ArrayList<>();

    @Override
    public String toString() {
        return "Roles{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", nacionalidad='" + nacionalidad + '\'' +
                ", librosCount=" + (libros != null ? libros.size() : 0) +
                '}';
    }

}

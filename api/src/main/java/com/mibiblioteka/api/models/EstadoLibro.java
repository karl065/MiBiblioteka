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
@Document(collection = "estados_libros")
public class EstadoLibro {

    @Id
    private String id;

    @NotBlank(message = "El nombre del estado es obligatorio")
    private String nombre;
    // Ejemplo: "Almacenado", "Prestado", "Devuelto", "Extraviado", "Reservado",
    // etc.

    private String descripcion;

    @DBRef
    private List<Libros> libros = new ArrayList<>();

    @Override
    public String toString() {
        return "EstadoLibro{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", librosCount=" + (libros != null ? libros.size() : 0) +
                '}';
    }
}

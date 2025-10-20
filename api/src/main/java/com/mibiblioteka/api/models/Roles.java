package com.mibiblioteka.api.models;

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
@Document(collection = "roles")
public class Roles {

    @Id
    private String id;

    @NotBlank(message = "El nombre del rol es obligatorio")
    private String nombre;
    // Ejemplo: "ADMIN", "LECTOR", "BIBLIOTECARIO"

    private String descripcion;

    @DBRef
    private List<Usuarios> usuarios;

    @Override
    public String toString() {
        return "Roles{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", usuariosCount=" + (usuarios != null ? usuarios.size() : 0) +
                '}';
    }
}

package com.mibiblioteka.api.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "usuarios")
public class Usuarios {
    
    @Id
    private String id;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @Email(message = "El correo debe ser válido")
    private String correo;

    @NotBlank(message = "La contraseña es obligatoria")
    private String password; // 🔒 campo de contraseña encriptada

    private String celular;

    private String direccion;

    @DBRef
    private List<Roles> rol; // Referencia a roles asignados al usuario

    private boolean activo = true;

    // Libros que el usuario tiene actualmente en préstamo
    @DBRef
    private List<Libros> librosPrestados = new ArrayList<>();

    // Libros que el usuario ya ha leído o devuelto
    @DBRef
    private List<Libros> librosLeidos = new ArrayList<>();
}

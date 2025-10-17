package com.mibiblioteka.api.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "libros")
public class Libros {
    
     @Id
    private String id;

    @NotBlank(message = "El título es obligatorio")
    private String titulo;

    @DBRef
    private Autores autor;

    @DBRef
    private Generos genero;

    @DBRef
    private EstadoLibro estado;

    // Usuario que actualmente tiene el libro
    @DBRef
    private Usuarios usuarioPrestamo;

    // Todos los usuarios que alguna vez lo tuvieron
    @DBRef
    private List<Usuarios> lectores = new ArrayList<>();

    // Fechas actuales del préstamo
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;

    // Historial de fechas de préstamos y devoluciones
    private List<LocalDate> fechasPrestamo = new ArrayList<>();
    private List<LocalDate> fechasDevolucion = new ArrayList<>();

    // Veces que este libro ha sido prestado
    private int vecesPrestado = 0;
}

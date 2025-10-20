package com.mibiblioteka.api.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")

@Data
@Document(collection = "libros")
public class Libros {

    @Id
    private String id;

    @NotBlank(message = "El título es obligatorio")
    private String titulo;

    private String sinopsis;

    private String portadaUrl;

    @DBRef
    private Autores autor;

    @DBRef
    private List<Generos> generos = new ArrayList<>();

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

    @Override
    public String toString() {
        return "Libros{" +
                "id='" + id + '\'' +
                ", titulo='" + titulo + '\'' +
                ", sinopsis='" + sinopsis + '\'' +
                ", portadaUrl='" + portadaUrl + '\'' +
                ", vecesPrestado='" + vecesPrestado + '\'' +
                ", autor='" + autor + '\'' +
                ", estado='" + estado + '\'' +
                ", usuarioPrestamo='" + usuarioPrestamo + '\'' +
                ", fechaPrestamo='" + fechaPrestamo + '\'' +
                ", fechaDevolucion='" + fechaDevolucion + '\'' +
                ", lectoresCount=" + (lectores != null ? lectores.size() : 0) +
                ", fechasPrestamoCount=" + (fechasPrestamo != null ? fechasPrestamo.size() : 0) +
                ", fechasDevolucionCount=" + (fechasDevolucion != null ? fechasDevolucion.size() : 0) +
                ", generosCount=" + (generos != null ? generos.size() : 0) +
                '}';
    }
}

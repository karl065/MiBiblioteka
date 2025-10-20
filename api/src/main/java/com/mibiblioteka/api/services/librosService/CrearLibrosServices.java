package com.mibiblioteka.api.services.librosService;

import java.util.List;
import org.springframework.stereotype.Service;

import com.mibiblioteka.api.models.Autores;
import com.mibiblioteka.api.models.EstadoLibro;
import com.mibiblioteka.api.models.Generos;
import com.mibiblioteka.api.models.Libros;
import com.mibiblioteka.api.repository.librosRepository.LibrosRepository;
import com.mibiblioteka.api.services.autoresService.ActualizarAutoresServices;
import com.mibiblioteka.api.services.estadoLibroService.ActualizarEstadoLibroServices;
import com.mibiblioteka.api.services.generosService.ActualizarGenerosServices;

@Service
public class CrearLibrosServices {

    private final LibrosRepository librosRepository;

    private final ActualizarAutoresServices actualizarAutoresServices;

    private final ActualizarGenerosServices actualizarGenerosServices;
    private final ActualizarEstadoLibroServices actualizarEstadoLibroServices;

    public CrearLibrosServices(LibrosRepository librosRepository,
            ActualizarAutoresServices actualizarAutoresServices,
            ActualizarGenerosServices actualizarGenerosServices,
            ActualizarEstadoLibroServices actualizarEstadoLibroServices) {
        this.librosRepository = librosRepository;
        this.actualizarAutoresServices = actualizarAutoresServices;
        this.actualizarGenerosServices = actualizarGenerosServices;
        this.actualizarEstadoLibroServices = actualizarEstadoLibroServices;
    }

    public Libros crearLibro(Libros libro) {
        try {

            Libros libroCreado = librosRepository.crear(libro);

            String libroId = libroCreado.getId();
            libroCreado.setId(libroId);

            for (Object autorObj : (Iterable<?>) libro.getAutor()) {
                if (autorObj instanceof Autores autor) {
                    Autores autorActualizado = new Autores();
                    autorActualizado.setLibros(List.of(libroCreado));
                    actualizarAutoresServices.actualizarAutor(autor.getId(), autorActualizado);
                }

            }

            if (libro.getGeneros() != null && !libro.getGeneros().isEmpty()) {
                for (Generos genero : libro.getGeneros()) {
                    Generos generoActualizado = new Generos();
                    generoActualizado.setLibros(List.of(libroCreado));
                    actualizarGenerosServices.actualizarGenero(genero.getId(), generoActualizado);
                }
            }

            if (libro.getEstado() != null) {
                EstadoLibro estado = libro.getEstado();
                EstadoLibro estadoActualizado = new EstadoLibro();
                estadoActualizado.setLibros(List.of(libroCreado));
                actualizarEstadoLibroServices.actualizarEstadoLibro(estado.getId(), estadoActualizado);
            }

            return libroCreado;

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error al crear un libro: " + e.getMessage());
            return null;
        }
    }
}

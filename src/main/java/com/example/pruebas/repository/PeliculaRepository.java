package com.example.pruebas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.pruebas.dto.PeliculasActores;
import com.example.pruebas.model.Pelicula;

public interface PeliculaRepository extends JpaRepository<Pelicula, Long> {
    @Query("select p from Pelicula p where p.anyo >= 2000")
    List<Pelicula> getPeliculasModernas();

    @Query("select p from Pelicula p where p.titulo = :busqueda")
    List<Pelicula> getPorTitulo(@Param("busqueda") String busqueda);

    List<Pelicula> findByTituloContaining(String busqueda);
    // findByTituloStartingWith
    // findByAnyoGreaterThan
    // findByAnyoBetween
    // findByDuracionIsNotNull

    @Query("select avg(p.duracion) from Pelicula p")
    double getMediaDuracion();

    @Query("select p.titulo as titulo, size(p.actores) as numero from Pelicula p")
    List<PeliculasActores> getNumActores();
}

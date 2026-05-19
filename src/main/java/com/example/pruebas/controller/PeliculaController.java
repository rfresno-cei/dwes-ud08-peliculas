package com.example.pruebas.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.pruebas.dto.PeliculasActores;
import com.example.pruebas.model.Pelicula;
import com.example.pruebas.service.PeliculaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/peliculas")
public class PeliculaController {
    private final PeliculaService peliculaService;

    public PeliculaController(PeliculaService peliculaService) {
        this.peliculaService = peliculaService;
    }

    @GetMapping
    public List<Pelicula> obtenerPeliculas() {
        return peliculaService.obtenerTodas();
    }

    @PostMapping
    public ResponseEntity<Pelicula> crearPelicula(@Valid @RequestBody Pelicula p) {
        return new ResponseEntity<>(peliculaService.crear(p), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pelicula> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(peliculaService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pelicula> actualizar(@PathVariable Long id, @Valid @RequestBody Pelicula p) {
        return ResponseEntity.ok(peliculaService.actualizar(p, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrar(@PathVariable Long id) {
        peliculaService.borrar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{peliculaId}/actores/{actorId}")
    public ResponseEntity<Pelicula> asignarActor(@PathVariable Long peliculaId, @PathVariable Long actorId) {
        return ResponseEntity.ok(peliculaService.asignarActor(peliculaId, actorId));
    }

    @DeleteMapping("/{peliculaId}/actores/{actorId}")
    public ResponseEntity<Pelicula> desasignarActor(@PathVariable Long peliculaId, @PathVariable Long actorId) {
        return ResponseEntity.ok(peliculaService.desasignarActor(peliculaId, actorId));
    }

    @GetMapping("/hola")
    public List<Pelicula> getModernas() {
        return peliculaService.getModernas();
    }

    @GetMapping("/adios")
    public List<Pelicula> getPalabraExacta(@RequestParam String palabra) {
        return peliculaService.getPalabraExacta(palabra);
    }

    @GetMapping("/chao")
    public List<Pelicula> getContiene(@RequestParam String busqueda) {
        return peliculaService.getContiene(busqueda);
    }

    @GetMapping("/media")
    public double getMediaDuracion() {
        return peliculaService.getMediaDuracion();
    }

    @GetMapping("/numactores")
    public List<PeliculasActores> getNumActores() {
        return peliculaService.getNumActores();
    }
}

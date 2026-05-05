package com.example.pruebas.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pruebas.model.Pelicula;
import com.example.pruebas.service.PeliculaService;

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
    public ResponseEntity<Pelicula> crearPelicula(@RequestBody Pelicula p) {
        peliculaService.crear(p);
        return new ResponseEntity<>(p, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pelicula> obtenerPorId(@PathVariable Long id) {
        return peliculaService.obtenerPorId(id)
        .map(p -> ResponseEntity.ok(p))
        .orElseGet(() -> ResponseEntity.notFound().build());
    }
}

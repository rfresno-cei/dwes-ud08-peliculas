package com.example.pruebas.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.pruebas.model.Pelicula;

@Service
public class PeliculaService {
    private List<Pelicula> repository = new ArrayList<>();

    public List<Pelicula> obtenerTodas() {
        return repository;
    }

    public Pelicula crear(Pelicula p) {
        repository.add(p);
        return p;
    }

    public Optional<Pelicula> obtenerPorId(Long id) {
        return repository.stream().filter(p -> p.getId().equals(id)).findFirst();
    }
}

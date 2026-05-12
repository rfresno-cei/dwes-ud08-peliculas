package com.example.pruebas.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.pruebas.exception.ResourceNotFoundException;
import com.example.pruebas.model.Actor;
import com.example.pruebas.repository.ActorRepository;
import com.example.pruebas.repository.PeliculaRepository;

@Service
public class ActorService {
    private final ActorRepository actorRepository;
    private final PeliculaRepository peliculaRepository;

    public ActorService(ActorRepository actorRepository, PeliculaRepository peliculaRepository) {
        this.actorRepository = actorRepository;
        this.peliculaRepository = peliculaRepository;
    }

    public List<Actor> obtenerTodas() {
        return actorRepository.findAll();
    }

    public Actor crear(Actor p) {
        p.setPeliculas(new ArrayList<>());
        return actorRepository.save(p);
    }

    public Actor buscarPorId(Long id) {
        return actorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("El actor no existe"));
    }

    public Actor actualizar(Actor p, Long id) {
        Actor encontrada = buscarPorId(id);
        encontrada.setNombre(p.getNombre());
        encontrada.setDni(p.getDni());
        encontrada.setEdad(p.getEdad());
        return actorRepository.save(encontrada);
    }

    public void borrar(Long id) {
        Actor encontrada = buscarPorId(id);
        actorRepository.delete(encontrada);
    }
}

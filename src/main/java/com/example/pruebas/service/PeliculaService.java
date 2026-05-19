package com.example.pruebas.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.pruebas.dto.PeliculasActores;
import com.example.pruebas.exception.ResourceNotFoundException;
import com.example.pruebas.model.Actor;
import com.example.pruebas.model.Pelicula;
import com.example.pruebas.repository.ActorRepository;
import com.example.pruebas.repository.PeliculaRepository;

@Service
public class PeliculaService {
    private final PeliculaRepository peliculaRepository;
    private final ActorRepository actorRepository;

    public PeliculaService(PeliculaRepository peliculaRepository, ActorRepository actorRepository) {
        this.peliculaRepository = peliculaRepository;
        this.actorRepository = actorRepository;
    }

    public List<Pelicula> obtenerTodas() {
        return peliculaRepository.findAll();
    }

    public Pelicula crear(Pelicula p) {
        p.setActores(new ArrayList<>());
        return peliculaRepository.save(p);
    }

    public Pelicula buscarPorId(Long id) {
        return peliculaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("La película no existe"));
    }

    public Pelicula actualizar(Pelicula p, Long id) {
        Pelicula encontrada = buscarPorId(id);
        encontrada.setTitulo(p.getTitulo());
        encontrada.setDirector(p.getDirector());
        encontrada.setDuracion(p.getDuracion());
        encontrada.setAnyo(p.getAnyo());
        return peliculaRepository.save(encontrada);
    }

    public void borrar(Long id) {
        Pelicula encontrada = buscarPorId(id);
        peliculaRepository.delete(encontrada);
    }

    public Pelicula asignarActor(Long peliculaId, Long actorId) {
        Pelicula p = buscarPorId(peliculaId);
        Actor a = actorRepository.findById(actorId)
        .orElseThrow(() -> new ResourceNotFoundException("El actor no existe"));
        p.getActores().add(a);
        return peliculaRepository.save(p);
    }

    public Pelicula desasignarActor(Long peliculaId, Long actorId) {
        Pelicula p = buscarPorId(peliculaId);
        Actor a = actorRepository.findById(actorId)
        .orElseThrow(() -> new ResourceNotFoundException("El actor no existe"));
        p.getActores().remove(a);
        return peliculaRepository.save(p);
    }

    public List<Pelicula> getModernas() {
        return peliculaRepository.getPeliculasModernas();
    }

    public List<Pelicula> getPalabraExacta(String busqueda) {
        return peliculaRepository.getPorTitulo(busqueda);
    }

    public List<Pelicula> getContiene(String busqueda) {
        return peliculaRepository.findByTituloContaining(busqueda);
    }

    public double getMediaDuracion() {
        return peliculaRepository.getMediaDuracion();
    }

    public List<PeliculasActores> getNumActores() {
        return peliculaRepository.getNumActores();
    }
}

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
import org.springframework.web.bind.annotation.RestController;

import com.example.pruebas.model.Actor;
import com.example.pruebas.service.ActorService;

@RestController
@RequestMapping("/api/actores")
public class ActorController {
    private final ActorService actorService;

    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @GetMapping
    public List<Actor> obtenerActors() {
        return actorService.obtenerTodas();
    }

    @PostMapping
    public ResponseEntity<Actor> crearActor(@RequestBody Actor p) {
        return new ResponseEntity<>(actorService.crear(p), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Actor> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(actorService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Actor> actualizar(@PathVariable Long id, @RequestBody Actor p) {
        return ResponseEntity.ok(actorService.actualizar(p, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrar(@PathVariable Long id) {
        actorService.borrar(id);
        return ResponseEntity.noContent().build();
    }
}

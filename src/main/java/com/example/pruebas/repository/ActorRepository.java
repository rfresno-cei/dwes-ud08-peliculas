package com.example.pruebas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.pruebas.model.Actor;

public interface ActorRepository extends JpaRepository<Actor, Long> {
    
}

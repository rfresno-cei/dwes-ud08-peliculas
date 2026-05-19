package com.example.pruebas.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Pelicula {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "El título no puede ser vacío")
    @Size(min = 2, max = 100, message = "El título debe tener entre 2 y 100 letras")
    private String titulo;
    private String director;
    @Min(1910)
    private int anyo;
    @NotNull
    private Integer duracion;
    @ManyToMany
    private List<Actor> actores;
}

package com.example.pruebas.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Pelicula {
    private Long id;
    private String titulo;
    private String director;
    private int anyo;
    private int duracion;
}

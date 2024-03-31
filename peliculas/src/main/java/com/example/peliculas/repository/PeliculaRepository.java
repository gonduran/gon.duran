package com.example.peliculas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.peliculas.model.Pelicula;

public interface PeliculaRepository extends JpaRepository<Pelicula, Long>{
    
}

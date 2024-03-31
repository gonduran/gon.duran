package com.example.peliculas.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.peliculas.model.Pelicula;
import com.example.peliculas.service.PeliculaService;
import com.example.peliculas.exception.PeliculaNotFoundException;

import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/peliculas")
public class PeliculaController {
    @Autowired
    private PeliculaService peliculaService;

    @GetMapping
    public List<Pelicula> getAllPeliculas(){
        System.out.println("Se ejecuta getAllPeliculas.");
        return peliculaService.getAllPeliculas();
    }
        
    @GetMapping("/{id}")
    public ResponseEntity<Pelicula> getPeliculaById(@PathVariable Long id) {
        System.out.println("Se ejecuta getPeliculaById: " + id);
        Pelicula pelicula = peliculaService.getPeliculaById(id)
            .orElseThrow(() -> new PeliculaNotFoundException("Pelicula no encontrada con ID: " + id));
        return ResponseEntity.ok(pelicula);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body("Error en el servidor: " + e.getMessage());
    }

    @ExceptionHandler(PeliculaNotFoundException.class)
    public ResponseEntity<String> handlePeliculaNotFoundException(PeliculaNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(e.getMessage());
    }

}

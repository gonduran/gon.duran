package com.example.peliculas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import com.example.peliculas.model.Pelicula;
import com.example.peliculas.service.PeliculaService;
import com.example.peliculas.exception.PeliculaNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/peliculas")
public class PeliculaController {
    private static final Logger log = LoggerFactory.getLogger(PeliculaController.class);

    @Autowired
    private PeliculaService peliculaService;

    @GetMapping
    public CollectionModel<EntityModel<Pelicula>> getAllPeliculas() {
        List<Pelicula> peliculas = peliculaService.getAllPeliculas();
        log.info("GET /peliculas");
        log.info("Se ejecuta getAllPeliculas");
        List<EntityModel<Pelicula>> peliculasResources = peliculas.stream()
            .map( pelicula -> EntityModel.of(pelicula,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getPeliculaById(pelicula.getId())).withSelfRel()
            ))
            .collect(Collectors.toList());

        WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllPeliculas());
        CollectionModel<EntityModel<Pelicula>> resources = CollectionModel.of(peliculasResources, linkTo.withRel("peliculas"));

        return resources;
    }

    @GetMapping("/{id}")
    public EntityModel<Pelicula> getPeliculaById(@PathVariable Long id) {
        Optional<Pelicula> pelicula = peliculaService.getPeliculaById(id);
        log.info("GET /peliculas/{id}");
        log.info("Se ejecuta getPeliculaById: {}", id);
        if (pelicula.isPresent()) {
            return EntityModel.of(pelicula.get(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getPeliculaById(id)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllPeliculas()).withRel("all-peliculas"));
        } else {
            throw new PeliculaNotFoundException("Pelicula no encontrada con ID: " + id);
        }
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

    @PostMapping
    public EntityModel<Pelicula> createPelicula(@Validated @RequestBody Pelicula pelicula) {
        log.info("POST /peliculas");
        log.info("Se ejecuta createPelicula");
        Pelicula createPelicula = peliculaService.createPelicula(pelicula);
        return EntityModel.of(createPelicula,
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getPeliculaById(createPelicula.getId())).withSelfRel(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllPeliculas()).withRel("all-peliculas"));
    }
    
    @PutMapping("/{id}")
    public EntityModel<Pelicula> updatePelicula(@PathVariable Long id, @RequestBody Pelicula pelicula) {
        log.info("PUT /peliculas/{id}");
        log.info("Se ejecuta updatePelicula: {}", id);
        Pelicula updatedPelicula = peliculaService.updatePelicula(id, pelicula);
        return EntityModel.of(updatedPelicula,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getPeliculaById(id)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllPeliculas()).withRel("all-peliculas"));

    }

    @DeleteMapping("/{id}")
    public void deletePelicula(@PathVariable Long id){
        log.info("DELETE /peliculas/{id}");
        log.info("Se ejecuta deletePelicula: {}", id);
        peliculaService.deletePelicula(id);
    }
}

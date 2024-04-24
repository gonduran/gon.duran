package com.example.peliculas.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.peliculas.model.Pelicula;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PeliculaRepositoryTest {
    @Autowired
    private PeliculaRepository peliculaRepository;

    @Test
    public void guardarPeliculaTest() {
        Pelicula pelicula = new Pelicula();
        pelicula.setTitulo("Bob Marley: la leyenda");
        pelicula.setAnno(2024);
        pelicula.setDirector("Reinaldo Marcus Green");
        pelicula.setGenero("Biografía, Drama, Histórica");
        pelicula.setSinopsis("La historia de como el ícono del reggae, Bob Marley, se sobrepuso ante la adversidad y el viaje detrás de su música revolucionaria.");

        Pelicula resultado = peliculaRepository.save(pelicula);

        assertNotNull(resultado.getId());
        assertEquals("Bob Marley: la leyenda", resultado.getTitulo());
    }
}

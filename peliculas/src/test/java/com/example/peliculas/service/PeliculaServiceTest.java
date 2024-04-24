package com.example.peliculas.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import java.util.List;
import java.util.Optional;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.example.peliculas.model.Pelicula;
import com.example.peliculas.repository.PeliculaRepository;

@ExtendWith(MockitoExtension.class)
public class PeliculaServiceTest {
    @InjectMocks
    private PeliculaServiceImpl peliculaService;

    @Mock
    private PeliculaRepository peliculaRepositoryMock;

    @Test
    public void getAllPeliculasTest() {
        Pelicula pelicula1 = new Pelicula();
        pelicula1.setTitulo("Bob Marley: la leyenda");
        pelicula1.setAnno(2024);
        pelicula1.setDirector("Reinaldo Marcus Green");
        pelicula1.setGenero("Biografía, Drama, Histórica");
        pelicula1.setSinopsis("La historia de como el ícono del reggae, Bob Marley, se sobrepuso ante la adversidad y el viaje detrás de su música revolucionaria.");
        pelicula1.setId(1L);

        Pelicula pelicula2 = new Pelicula();
        pelicula2.setTitulo("Bad Boys: Ride or Die");
        pelicula2.setAnno(2024);
        pelicula2.setDirector("Adil El Arbi");
        pelicula2.setGenero("Acción, Comedia");
        pelicula2.setSinopsis("Tras escuchar falsas acusaciones sobre su excapitán y mentor Mike y Marcus deciden investigar el asunto incluso volverse los más buscados de ser necesarios.");
        pelicula2.setId(2L);

        List<Pelicula> peliculas = Arrays.asList(pelicula1, pelicula2);

        when(peliculaRepositoryMock.findAll()).thenReturn(peliculas);

        List<Pelicula> resultado = peliculaService.getAllPeliculas();

        assertEquals(2, resultado.size());
        assertEquals("Bob Marley: la leyenda", resultado.get(0).getTitulo());
        assertEquals("Bad Boys: Ride or Die", resultado.get(1).getTitulo());
    }

    @Test
    public void getPeliculaByIdTest() {
        Pelicula pelicula = new Pelicula();
        pelicula.setTitulo("Bob Marley: la leyenda");
        pelicula.setAnno(2024);
        pelicula.setDirector("Reinaldo Marcus Green");
        pelicula.setGenero("Biografía, Drama, Histórica");
        pelicula.setSinopsis("La historia de como el ícono del reggae, Bob Marley, se sobrepuso ante la adversidad y el viaje detrás de su música revolucionaria.");
        pelicula.setId(1L);

        when(peliculaRepositoryMock.findById(1L)).thenReturn(Optional.of(pelicula));

        Optional<Pelicula> resultado = peliculaService.getPeliculaById(1L);

        assertTrue(resultado.isPresent());
        assertEquals("Bob Marley: la leyenda", resultado.get().getTitulo());
    }

    @Test
    public void createPeliculaTest() {

        Pelicula pelicula = new Pelicula();
        pelicula.setTitulo("Bob Marley: la leyenda");
        pelicula.setAnno(2024);
        pelicula.setDirector("Reinaldo Marcus Green");
        pelicula.setGenero("Biografía, Drama, Histórica");
        pelicula.setSinopsis("La historia de como el ícono del reggae, Bob Marley, se sobrepuso ante la adversidad y el viaje detrás de su música revolucionaria.");

        when(peliculaRepositoryMock.save(any())).thenReturn(pelicula);

        Pelicula resultado = peliculaService.createPelicula(pelicula);

        assertEquals("Bob Marley: la leyenda", resultado.getTitulo());
    }

    @Test
    public void updatePeliculaTest_Exists() {
        Pelicula pelicula = new Pelicula();
        pelicula.setTitulo("Bob Marley: la leyenda");
        pelicula.setAnno(2024);
        pelicula.setDirector("Reinaldo Marcus Green");
        pelicula.setGenero("Biografía, Drama, Histórica");
        pelicula.setSinopsis("La historia de como el ícono del reggae, Bob Marley, se sobrepuso ante la adversidad y el viaje detrás de su música revolucionaria.");
        pelicula.setId(1L);
        when(peliculaRepositoryMock.existsById(1L)).thenReturn(true);
        when(peliculaRepositoryMock.save(pelicula)).thenReturn(pelicula);

        Pelicula resultado = peliculaService.updatePelicula(1L, pelicula);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Bob Marley: la leyenda", resultado.getTitulo());
    }

    @Test
    public void updatePeliculaTest_NotExists() {
        Pelicula pelicula = new Pelicula();
        pelicula.setTitulo("Bob Marley: la leyenda");
        pelicula.setAnno(2024);
        pelicula.setDirector("Reinaldo Marcus Green");
        pelicula.setGenero("Biografía, Drama, Histórica");
        pelicula.setSinopsis("La historia de como el ícono del reggae, Bob Marley, se sobrepuso ante la adversidad y el viaje detrás de su música revolucionaria.");
        pelicula.setId(2L);
        when(peliculaRepositoryMock.existsById(2L)).thenReturn(false);

        Pelicula resultado = peliculaService.updatePelicula(2L, pelicula);

        assertNull(resultado);
    }
}

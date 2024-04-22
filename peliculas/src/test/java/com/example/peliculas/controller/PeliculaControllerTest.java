package com.example.peliculas.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import java.util.List;
import java.util.Optional;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.example.peliculas.model.Pelicula;
import com.example.peliculas.service.PeliculaService;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(PeliculaController.class)
public class PeliculaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PeliculaService peliculaServiceMock;

    @Test
    public void getAllPeliculasTest() throws Exception {
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

        when(peliculaServiceMock.getAllPeliculas()).thenReturn(peliculas);

        mockMvc.perform(MockMvcRequestBuilders.get("/peliculas"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void getPeliculaByIdTest() throws Exception {
        Pelicula pelicula = new Pelicula();
        pelicula.setTitulo("Bob Marley: la leyenda");
        pelicula.setAnno(2024);
        pelicula.setDirector("Reinaldo Marcus Green");
        pelicula.setGenero("Biografía, Drama, Histórica");
        pelicula.setSinopsis("La historia de como el ícono del reggae, Bob Marley, se sobrepuso ante la adversidad y el viaje detrás de su música revolucionaria.");
        pelicula.setId(1L);

        when(peliculaServiceMock.getPeliculaById(1L)).thenReturn(Optional.of(pelicula));

        Optional<Pelicula> resultado = peliculaServiceMock.getPeliculaById(1L);

        assertTrue(resultado.isPresent());
        assertEquals("Bob Marley: la leyenda", resultado.get().getTitulo());

        mockMvc.perform(MockMvcRequestBuilders.get("/peliculas/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
/*
    @Test
    public void createPeliculaTest() throws Exception {

        Pelicula pelicula = new Pelicula();
        pelicula.setTitulo("Bob Marley: la leyenda");
        pelicula.setAnno(2024);
        pelicula.setDirector("Reinaldo Marcus Green");
        pelicula.setGenero("Biografía, Drama, Histórica");
        pelicula.setSinopsis("La historia de como el ícono del reggae, Bob Marley, se sobrepuso ante la adversidad y el viaje detrás de su música revolucionaria.");

        when(peliculaServiceMock.createPelicula(pelicula));

        Pelicula resultado = peliculaServiceMock.createPelicula(pelicula);

        assertEquals("Bob Marley: la leyenda", resultado.getTitulo());
        mockMvc.perform(MockMvcRequestBuilders.post("/peliculas"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void updatePeliculaTest() throws Exception {
        Pelicula pelicula = new Pelicula();
        pelicula.setTitulo("Bob Marley: la leyenda");
        pelicula.setAnno(2024);
        pelicula.setDirector("Reinaldo Marcus Green");
        pelicula.setGenero("Biografía, Drama, Histórica");
        pelicula.setSinopsis("La historia de como el ícono del reggae, Bob Marley, se sobrepuso ante la adversidad y el viaje detrás de su música revolucionaria.");
        pelicula.setId(1L);
        when(peliculaServiceMock.updatePelicula(1L, pelicula));

        Pelicula resultado = peliculaServiceMock.updatePelicula(1L, pelicula);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Bob Marley: la leyenda", resultado.getTitulo());
        mockMvc.perform(MockMvcRequestBuilders.put("/peliculas/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
*/
}

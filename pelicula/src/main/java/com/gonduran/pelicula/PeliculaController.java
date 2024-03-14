package com.gonduran.pelicula;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PeliculaController {
    private List<Pelicula> peliculas = new ArrayList<>();

    public PeliculaController(){
        //Inicializa la lista de peliculas con algunos datos
        //peliculas.add(new Pelicula(1, "", 0, "", "", ""));
        peliculas.add(new Pelicula(1, "The Lord of the Rings: The Fellowship of the Ring", 2001, "Peter Jackson", "Aventuras, Fantasía épica", "En la Tierra Media, el Señor Oscuro Sauron busca el Anillo Único, que ha caído en manos del hobbit Frodo Bolsón. La Compañía del Anillo emprende un peligroso viaje para destruir el anillo en el Monte del Destino en Mordor"));
        peliculas.add(new Pelicula(2, "The Lord of the Rings: The Two Towers", 0, "Peter Jackson", "Aventuras, Fantasía épica", "Tras la disolución de la Compañía del Anillo, Frodo y Sam se dirigen hacia Mordor para destruir el Anillo Único. Mientras tanto, Aragorn, Legolas y Gimli intentan rescatar a los medianos Merry y Pippin, secuestrados por los orcos de Mordor"));
        peliculas.add(new Pelicula(3, "The Lord of the Rings: The Return of the King", 0, "Peter Jackson", "Aventuras, Fantasía épica", "Las fuerzas de Saruman han sido destruidas, y su fortaleza está sitiada. Frodo y Sam continúan su viaje hacia Mordor para destruir el Anillo Único. Mientras tanto, Aragorn, Legolas y Gimli intentan rescatar a los medianos Merry y Pippin. La Tierra Media se enfrenta a su destino final en una épica batalla contra las fuerzas del mal"));
        peliculas.add(new Pelicula(4, "Oppenheimer", 2023, "Christopher Nolan", "Histórica, Drama", "Ambiciosa película que se adentra en la historia del padre de la bomba atómica, J. Robert Oppenheimer"));
        peliculas.add(new Pelicula(5, "Todo a la vez en todas partes", 2022, "Daniel Kwan, Daniel Scheinert", "Fantasía, Ciencia ficción", "Una película fascinante y diferente que explora los multiversos en un contexto de superhéroes"));
        peliculas.add(new Pelicula(6, "CODA. Los sonidos del silencio", 2021, "Sian Heder", "Drama, Música", "Remake estadounidense de una película exitosa rodada en otro idioma, sigue la historia de una familia sorda y su hija con talento musical"));
        peliculas.add(new Pelicula(7, "Nomadland", 2020, "Chloé Zhao", "Drama, Aventuras", "Una película hermosa y auténtica que sigue a una mujer en su viaje por el oeste de Estados Unidos tras la Gran Recesión"));
        peliculas.add(new Pelicula(8, "Parásitos", 2019, "Bong Joon-ho", "Drama, Comedia negra, Thriller", "La familia Kim, malvive en un semisótano y se infiltra en la vida de una familia adinerada, desencadenando una serie de eventos inesperados"));
        peliculas.add(new Pelicula(9, "Green Book", 2019, "Peter Farrelly", "Drama, Comedia, Biografía", "La historia de la amistad entre un pianista afroamericano y su chofer italoamericano durante una gira por el sur de Estados Unidos en los años 60"));
        peliculas.add(new Pelicula(10, "La forma del agua", 2017, "Guillermo del Toro", "Fantasía, Romance, Drama", "Durante la Guerra Fría, una mujer muda se enamora de una criatura acuática en un laboratorio secreto del gobierno"));
        peliculas.add(new Pelicula(11, "Moonlight", 2016, "Barry Jenkins", "Drama", "La vida de un joven afroamericano en tres etapas cruciales: infancia, adolescencia y adultez, mientras lucha con su identidad y sexualidad"));
        peliculas.add(new Pelicula(12, "Spotlight", 2015, "Tom McCarthy", "Drama, Periodismo", "Un grupo de periodistas del Boston Globe investiga y expone los abusos sexuales cometidos por sacerdotes católicos en la ciudad"));
        peliculas.add(new Pelicula(13, "Birdman", 2014, "Alejandro González Iñárritu", "Comedia, Drama", "Un actor famoso por interpretar a un superhéroe lucha por recuperar su carrera en Broadway mientras enfrenta su ego y su pasado")); 
    }
    @GetMapping("/peliculas")
    public List<Pelicula> getPeliculas() {
        return peliculas;
    }

    @GetMapping("/peliculas/{id}")
    public Pelicula getPeliculaById(@PathVariable int id){
        for (Pelicula pelicula : peliculas) {
            if (pelicula.getId() == id){
                return pelicula;
            }
        }
        return null;
    }    
}

package com.example.peliculas.exception;

public class PeliculaNotFoundException extends RuntimeException {
    public PeliculaNotFoundException(String message) {
        super(message);
    }
}

package com.example.usuarios.exception;

public class UsuarioBadRequestException  extends RuntimeException {
    public UsuarioBadRequestException(String message) {
        super(message);
    }
}

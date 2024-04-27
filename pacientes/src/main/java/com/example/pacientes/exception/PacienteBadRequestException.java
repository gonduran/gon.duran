package com.example.pacientes.exception;

public class PacienteBadRequestException extends RuntimeException {
    public PacienteBadRequestException(String message) {
        super(message);
    }
}

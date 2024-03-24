package com.example.usuarios;

public class Privilegio {

    private int idPrivilegio;
    private String descripcion;

    public Privilegio(int idPrivilegio, String descripcion) {
        this.idPrivilegio = idPrivilegio;
        this.descripcion = descripcion;
    }

    // Getters y setters

    public int getIdPrivilegio() {
        return idPrivilegio;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
package com.example.usuarios;

public class Rol {

    private int idRol;
    private String descripcion;

    public Rol(int idRol, String descripcion) {
        this.idRol = idRol;
        this.descripcion = descripcion;
    }

    // Getters y setters

    public int getIdRol() {
        return idRol;
    }

    public String getDescripcion() {
        return descripcion;
    }
}

package com.example.usuarios;

import java.util.List;

public class Rol {

    private int idRol;
    private String descripcion;
    private List<Privilegio> privilegios;

    public Rol(int idRol, String descripcion, List<Privilegio> privilegios) {
        this.idRol = idRol;
        this.descripcion = descripcion;
        this.privilegios = privilegios;
    }

    // Getters y setters

    public int getIdRol() {
        return idRol;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public List<Privilegio> getPrivilegios() {
        return privilegios;
    }


}

package com.example.usuarios;

import java.util.List;

public class Usuario {

    private int idUsuario;
    private String usuario;
    private String password;
    private String email;
    private boolean habilitado;
    private boolean tokenExpirado;
    private List<DatoPersona> datospersonales;
    private List<Direccion> direcciones;
    private List<Rol> roles;
    private List<Privilegio> privilegios;

    public Usuario(int idUsuario, String usuario, String password, String email, boolean habilitado, boolean tokenExpirado,
                    List<DatoPersona> datospersonales, List<Direccion> direcciones, List<Rol> roles, List<Privilegio> privilegios) {
        this.idUsuario = idUsuario;
        this.usuario = usuario;
        this.password = password;
        this.email = email;
        this.habilitado = habilitado;
        this.tokenExpirado = tokenExpirado;
        this.datospersonales = datospersonales;
        this.direcciones = direcciones;
        this.roles = roles;
        this.privilegios = privilegios;
    }

    // Getters y setters

    public int getIdUsuario() {
        return idUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public boolean getHabilitado() {
        return habilitado;
    }

    public boolean getTokenExpirado() {
        return tokenExpirado;
    }

    public List<DatoPersona> getDatospersonales() {
        return datospersonales;
    }

    public List<Direccion> getDirecciones() {
        return direcciones;
    }

    public List<Rol> getRoles() {
        return roles;
    }

    public List<Privilegio> getPrivilegios() {
        return privilegios;
    }
}

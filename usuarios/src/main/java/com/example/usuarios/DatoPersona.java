package com.example.usuarios;

import java.util.List;

public class DatoPersona {

    private int idPersona;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String rut;
    private String telefono;
    private String email;
    private List<Direccion> direcciones;

    public DatoPersona(int idPersona, String nombre, String apellidoPaterno, String apellidoMaterno, 
                    String rut,  String telefono, String email, List<Direccion> direcciones) {
        this.idPersona = idPersona;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.rut = rut;
        this.telefono = telefono;
        this.email = email;
        this.direcciones = direcciones;
    }

    // Getters y setters

    public int getIdPersona() {
        return idPersona;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public String getRut() {
        return rut;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getEmail() {
        return email;
    }

    public List<Direccion> getDirecciones() {
        return direcciones;
    }

}

package com.example.usuarios.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "DatoPersona")
public class DatoPersona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPersona")
    private Long id;

    @NotBlank(message = "No puede ingresar un nombre vacio")
    @NotNull(message = "Nombre obligatorio")
    @Column(name= "nombre")
    private String nombre;

    @NotBlank(message = "No puede ingresar un apellido paterno vacio")
    @NotNull(message = "Apellido paterno obligatorio")
    @Column(name= "apellidoPaterno")
    private String apellidoPaterno;

    @NotBlank(message = "No puede ingresar un apellido materno vacio")
    @NotNull(message = "Apellido materno obligatorio")
    @Column(name= "apellidoMaterno")
    private String apellidoMaterno;

    @NotBlank(message = "No puede ingresar un rut vacio")
    @NotNull(message = "Rut obligatorio")
    @Column(name= "rut")
    private String rut;

    @NotBlank(message = "No puede ingresar un telefono vacio")
    @NotNull(message = "Telefono obligatorio")
    @Column(name= "telefono")
    private String telefono;

    @NotBlank(message = "No puede ingresar un email vacio")
    @NotNull(message = "Email obligatorio")
    @Column(name= "email")
    private String email;

    // Getters y setters

    public Long getId() {
        return id;
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

    public void getIdPersona(Long id) {
        this.id = id;
    }

    public void getNombre(String nombre) {
        this.nombre = nombre;
    }

    public void getApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public void getApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public void getRut(String rut) {
        this.rut = rut;
    }

    public void getTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

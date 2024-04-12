package com.example.usuarios.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "Usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUsuario")
    private Long idUsuario;

    @NotBlank(message = "No puede ingresar un usuario vacio")
    @NotNull(message = "Usuario obligatorio")
    @Column(name= "usuario")
    private String usuario;

    @NotBlank(message = "No puede ingresar un password vacia")
    @NotNull(message = "Password obligatorio")
    @Column(name= "password")
    private String password;

    @NotBlank(message = "No puede ingresar un email vacio")
    @NotNull(message = "Email obligatorio")
    @Column(name= "email")
    private String email;

    @NotNull(message = "Habilitado obligatorio")
    @Column(name= "habilitado")
    private boolean habilitado;

    // Getters y setters

    public Long getIdUsuario() {
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

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

}

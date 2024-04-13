package com.example.usuarios.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;

import java.util.List;

@Entity
@Table(name = "Usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUsuario")
    private Long id;

    @NotBlank(message = "No puede ingresar un usuario vacio")
    @NotNull(message = "Usuario obligatorio")
    @Column(name= "usuario")
    private String usuario;

    @NotBlank(message = "No puede ingresar un password vacia")
    @NotNull(message = "Password obligatorio")
    @Column(name= "password")
    private String password;

    @NotNull(message = "Habilitado obligatorio")
    @Column(name= "habilitado")
    private boolean habilitado;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idPersona", referencedColumnName = "idPersona")
    private DatoPersona datospersona;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Direccion> direcciones;

    @ManyToMany
    @JoinTable(name = "usuario_rol",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "rol_id"))
    private List<RolUsuario> roles;

    // Getters y setters

    public Long getId() {
        return id;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getPassword() {
        return password;
    }

    public boolean getHabilitado() {
        return habilitado;
    }

    public DatoPersona getDatoPersona() {
        return datospersona;
    }

    public List<Direccion> getDireccion() {
        return direcciones;
    }

    public List<RolUsuario> getRolUsuario() {
        return roles;
    }

    public void setIdUsuario(Long id) {
        this.id = id;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

    public void setDatoPersona(DatoPersona datospersona) {
        this.datospersona = datospersona;
    }

    public void setDireccion(List<Direccion> direcciones) {
        this.direcciones = direcciones;
    }

    public void setRolUsuario(List<RolUsuario> roles) {
        this.roles = roles;
    }
}

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
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Direccion> direcciones;

    @JoinTable(
        name = "rel_usuario_rol",
        joinColumns = @JoinColumn(name = "fk_usuario", nullable = false),
        inverseJoinColumns = @JoinColumn(name="fk_rol", nullable = false)
    )
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Rol> roles;
   
    public void addRol(Rol rol){
        if(this.roles == null){
            this.roles = new ArrayList<>();
        }
        
        this.roles.add(rol);
    }

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

    public List<Rol> getRolUsuario() {
        return roles;
    }

    public void setId(Long id) {
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

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDirecciones(List<Direccion> direcciones) {
        this.direcciones = direcciones;
    }

    public void setRolUsuario(List<Rol> roles) {
        this.roles = roles;
    }
}

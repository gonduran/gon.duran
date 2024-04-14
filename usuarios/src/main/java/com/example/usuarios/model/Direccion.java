package com.example.usuarios.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "Direccion")
public class Direccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDireccion")
    private Long id;

    @NotBlank(message = "No puede ingresar calle vacia")
    @NotNull(message = "Calle obligatorio")
    @Column(name = "calle")
    private String calle;

    @NotBlank(message = "No puede ingresar numero vacio")
    @NotNull(message = "Numero obligatorio")
    @Column(name = "numero")
    private String numero;

    @NotBlank(message = "No puede ingresar comuna vacio")
    @NotNull(message = "Comuna obligatorio")
    @Column(name = "comuna")
    private String comuna;

    @NotBlank(message = "No puede ingresar ciudad vacio")
    @NotNull(message = "Ciudad obligatorio")
    @Column(name = "ciudad")
    private String ciudad;

    @NotBlank(message = "No puede ingresar region vacio")
    @NotNull(message = "Región obligatorio")
    @Column(name = "region")
    private String region;

    @NotBlank(message = "No puede ingresar tipo dirección vacio")
    @NotNull(message = "Tipo dirección obligatorio")
    @Column(name = "tipoDireccion")
    private String tipoDireccion;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    @JsonBackReference
    private Usuario usuario;

    // Getters y setters

    public Long getId() {
        return id;
    }

    public String getCalle() {
        return calle;
    }

    public String getNumero() {
        return numero;
    }

    public String getComuna() {
        return comuna;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getRegion() {
        return region;
    }

    public String getTipoDireccion() {
        return tipoDireccion;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setComuna(String comuna) {
        this.comuna = comuna;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setTipoDireccion(String tipoDireccion) {
        this.tipoDireccion = tipoDireccion;
    }
}

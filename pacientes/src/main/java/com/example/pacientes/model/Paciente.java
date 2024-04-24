package com.example.pacientes.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "Paciente")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPaciente")
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

    @NotBlank(message = "No puede ingresar direccion vacia")
    @NotNull(message = "Dirección obligatorio")
    @Column(name = "calle")
    private String direccion;

    @NotBlank(message = "No puede ingresar un telefono vacio")
    @NotNull(message = "Telefono obligatorio")
    @Column(name= "telefono")
    private String telefono;

    @NotBlank(message = "No puede ingresar un email vacio")
    @NotNull(message = "Email obligatorio")
    @Column(name= "email")
    private String email;

    @NotBlank(message = "No puede ingresar fecha nacimiento vacio")
    @NotNull(message = "Fecha nacimiento obligatorio")
    @Column(name = "fechaNacimiento")
    private String fechaNacimiento;

    @Column(name = "tipoSangre")
    private String tipoSangre;

    @Column(name = "contactoEmerg")
    private String contactoEmerg;

    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Consulta> consultas;

    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<HistorialMedico> historialMedico;

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

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getEmail() {
        return email;
    }

    public String getTipoSangre() {
        return tipoSangre;
    }

    public String getContactoEmerg() {
        return contactoEmerg;
    }

    public List<Consulta> getConsultas() {
        return consultas;
    }

    public List<HistorialMedico> getHistorialMedico() {
        return historialMedico;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTipoSangre(String tipoSangre) {
        this.tipoSangre = tipoSangre;
    }

    public void setContactoEmerg(String contactoEmerg) {
        this.contactoEmerg = contactoEmerg;
    }

    public void setConsultas(List<Consulta> consultas) {
        this.consultas = consultas;
    }

    public void setHistorialMedico(List<HistorialMedico> historialMedico) {
        this.historialMedico = historialMedico;
    }

}

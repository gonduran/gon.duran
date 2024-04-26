package com.example.pacientes.model;

import org.springframework.hateoas.RepresentationModel;

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
@Table(name = "HistorialMedico")
public class HistorialMedico extends RepresentationModel<HistorialMedico> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idHistorialMedico")
    private Long id;

    @NotBlank(message = "No puede ingresar nombre medico vacio")
    @NotNull(message = "Nombre medico obligatorio")
    @Column(name = "nombreMedico")
    private String nombreMedico;

    @NotBlank(message = "No puede ingresar fecha historial medico vacia")
    @NotNull(message = "Fecha historial medico obligatorio")
    @Column(name = "fechaHistorialMedico")
    private String fechaHistorialMedico;

    @Column(name = "antecedentes")
    private String antecedentes;

    @Column(name = "estudios")
    private String estudios;

    @Column(name = "internaciones")
    private String internaciones;

    @Column(name = "operaciones")
    private String operaciones;

    @Column(name = "tratamientos")
    private String tratamientos;

    @Column(name = "medicamentos")
    private String medicamentos;

    @Column(name = "examenes")
    private String examenes;

    @ManyToOne
    @JoinColumn(name = "paciente_id")
    @JsonBackReference
    private Paciente paciente;

   // Getters y setters

    public Long getId() {
        return id;
    }

    public String getNombreMedico() {
        return nombreMedico;
    }

    public String getFechaHistorialMedico() {
        return fechaHistorialMedico;
    }

    public String getAntecedentes() {
        return antecedentes;
    }

    public String getEstudios() {
        return estudios;
    }

    public String getInternaciones() {
        return internaciones;
    }

    public String getOperaciones() {
        return operaciones;
    }

    public String getTratamientos() {
        return tratamientos;
    }

    public String getMedicamentos() {
        return medicamentos;
    }

    public String getExamenes() {
        return examenes;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombreMedico(String nombreMedico) {
        this.nombreMedico = nombreMedico;
    }

    public void setFechaHistorialMedico(String fechaHistorialMedico) {
        this.fechaHistorialMedico = fechaHistorialMedico;
    }

    public void setAntecedentes(String antecedentes) {
        this.antecedentes = antecedentes;
    }

    public void setEstudios(String estudios) {
        this.estudios = estudios;
    }

    public void setInternaciones(String internaciones) {
        this.internaciones = internaciones;
    }

    public void setOperaciones(String operaciones) {
        this.operaciones = operaciones;
    }

    public void setTratamientos(String tratamientos) {
        this.tratamientos = tratamientos;
    }

    public void setMedicamentos(String medicamentos) {
        this.medicamentos = medicamentos;
    }

    public void setExamenes(String examenes) {
        this.examenes = examenes;
    }

}

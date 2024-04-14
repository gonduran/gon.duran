package com.example.pacientes.model;

import java.util.Date;

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
@Table(name = "Consulta")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idConsulta")
    private Long id;

    @NotBlank(message = "No puede ingresar nombre medico vacio")
    @NotNull(message = "Nombre medico obligatorio")
    @Column(name = "nombreMedico")
    private String nombreMedico;

    @NotBlank(message = "No puede ingresar motivo vacio")
    @NotNull(message = "Motivo obligatorio")
    @Column(name = "motivo")
    private String motivo;

    @NotBlank(message = "No puede ingresar diagnostico vacio")
    @NotNull(message = "Diagnostico obligatorio")
    @Column(name = "diagnostico")
    private String diagnostico;

    @NotBlank(message = "No puede ingresar tratamiento vacio")
    @NotNull(message = "Tratamiento obligatorio")
    @Column(name = "tratamiento")
    private String tratamiento;

    @NotBlank(message = "No puede ingresar fecha consulta vacio")
    @NotNull(message = "Fecha Consulta obligatorio")
    @Column(name = "fechaConsulta")
    private Date fechaConsulta;

    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    // Getters y setters

    public Long getId() {
        return id;
    }

    public String getNombreMedico() {
        return nombreMedico;
    }

    public String getMotivo() {
        return motivo;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public Date getFechaConsulta() {
        return fechaConsulta;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombreMedico(String nombreMedico) {
        this.nombreMedico = nombreMedico;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }

    public void setFechaConsulta(Date fechaConsulta) {
        this.fechaConsulta = fechaConsulta;
    }

}

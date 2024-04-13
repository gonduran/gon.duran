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
@Table(name = "HistorialMedico")
public class HistorialMedico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idHistorialMedico")
    private Long id;

    @Column(name = "alergias")
    private String alergias;

    @Column(name = "enfermedadesPrevias")
    private String enfermedadesPrevias;

    @Column(name = "procedimientos")
    private String procedimientos;

    @Column(name = "tipoSangre")
    private String tipoSangre;

    @NotBlank(message = "No puede ingresar fecha historial medico vacia")
    @NotNull(message = "Fecha Historial Medico obligatorio")
    @Column(name = "fechaHistorialMedico")
    private Date fechaHistorialMedico;

    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

   // Getters y setters

    public Long getId() {
        return id;
    }

    public String getAlergias() {
        return alergias;
    }

    public String getEnfermedadesPrevias() {
        return enfermedadesPrevias;
    }

    public String getProcedimientos() {
        return procedimientos;
    }

    public String getTipoSangre() {
        return tipoSangre;
    }

    public Date getFechaHistorialMedico() {
        return fechaHistorialMedico;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAlergias(String alergias) {
        this.alergias = alergias;
    }

    public void setEnfermedadesPrevias(String enfermedadesPrevias) {
        this.enfermedadesPrevias = enfermedadesPrevias;
    }

    public void setProcedimientos(String procedimientos) {
        this.procedimientos = procedimientos;
    }

    public void setTipoSangre(String tipoSangre) {
        this.tipoSangre = tipoSangre;
    }

    public void setFechaHistorialMedico(Date fechaHistorialMedico) {
        this.fechaHistorialMedico = fechaHistorialMedico;
    }

}

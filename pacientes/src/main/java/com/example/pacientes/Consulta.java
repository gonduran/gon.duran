package com.example.pacientes;

public class Consulta {

    private int idConsulta;
    private String motivo;
    private String diagnostico;
    private String tratamiento;
    private String fechaConsulta;

    public Consulta(int idConsulta, String motivo, String diagnostico, String tratamiento, String fechaConsulta) {
        this.idConsulta = idConsulta;
        this.motivo = motivo;
        this.diagnostico = diagnostico;
        this.tratamiento = tratamiento;
        this.fechaConsulta = fechaConsulta;
    }

    // Getters y setters

    public int getIdConsulta() {
        return idConsulta;
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

    public String getFechaConsulta() {
        return fechaConsulta;
    }

}

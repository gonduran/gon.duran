package com.example.pacientes;

public class HistorialMedico {

    private int idHistorialMedico;
    private String alergias;
    private String enfermedadesPrevias;
    private String procedimientos;
    private String tipoSangre;
    private String fechaHistorialMedico;

    public HistorialMedico(int idHistorialMedico, String alergias, String enfermedadesPrevias, String procedimientos, 
                           String tipoSangre, String fechaHistorialMedico) {
        this.idHistorialMedico = idHistorialMedico;
        this.alergias = alergias;
        this.enfermedadesPrevias = enfermedadesPrevias;
        this.procedimientos = procedimientos;
        this.tipoSangre = tipoSangre;
        this.fechaHistorialMedico = fechaHistorialMedico;
    }

   // Getters y setters

    public int getIdHistorialMedico() {
        return idHistorialMedico;
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

    public String getFechaHistorialMedico() {
        return fechaHistorialMedico;
    }

}

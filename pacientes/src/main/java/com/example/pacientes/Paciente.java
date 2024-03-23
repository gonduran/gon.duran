package com.example.pacientes;

import java.util.List;

public class Paciente {

    private int idPaciente;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String rut;
    private String direccion;
    private String telefono;
    private String email;
    private String fechaNacimiento;
    private List<Consulta> consultas;
    private List<HistorialMedico> historialMedico;

    public Paciente(int idPaciente, String nombre, String apellidoPaterno, String apellidoMaterno, String rut, 
                    String direccion, String telefono, String email, String fechaNacimiento,
                    List<Consulta> consultas, List<HistorialMedico> historialMedico) {
        this.idPaciente = idPaciente;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.rut = rut;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.fechaNacimiento = fechaNacimiento;
        this.consultas = consultas;
        this.historialMedico = historialMedico;
    }

    // Getters y setters

    public int getIdPaciente() {
        return idPaciente;
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

    public List<Consulta> getConsultas() {
        return consultas;
    }

    public List<HistorialMedico> getHistorialMedico() {
        return historialMedico;
    }

}

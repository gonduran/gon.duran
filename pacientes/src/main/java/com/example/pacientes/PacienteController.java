package com.example.pacientes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PacienteController {
    private List<Paciente> pacientes = new ArrayList<>();

    public PacienteController() {
        // Inicializar la lista con datos

        pacientes.add(new Paciente(1, "Josefa", "Gonzalez", "Ovando", 
                                    "1-9", "Pasaje El Verano 1169 Maipu", "9111111111", "josefa@correo.cl", "18/08/2007",
                                    Arrays.asList(new Consulta(1,"motivo","diagnostico","tratamiento","23/03/2022"), 
                                                    new Consulta(2,"motivo","diagnostico","tratamiento", "18/08/2023")),
                                    Arrays.asList(new HistorialMedico(1,"alergias","enfermedadesPrevias",
                                                                    "procedimientos","tipoSangre", "18/08/2007"), 
                                                    new HistorialMedico(2,"alergias","enfermedadesPrevias",
                                                                    "procedimientos","tipoSangre", "23/08/2023"))));
        pacientes.add(new Paciente(2, "Patricia", "Inostroza", "Adasme", 
                                    "2-7", "Pasaje El Verano 1169 Maipu", "9111111111", "josefa@correo.cl", "18/08/2007",
                                    Arrays.asList(new Consulta(1,"motivo","diagnostico","tratamiento","23/03/2022"), 
                                                    new Consulta(2,"motivo","diagnostico","tratamiento", "18/08/2023")),
                                    Arrays.asList(new HistorialMedico(1,"alergias","enfermedadesPrevias",
                                                                    "procedimientos","tipoSangre", "18/08/2007"), 
                                                    new HistorialMedico(2,"alergias","enfermedadesPrevias",
                                                                    "procedimientos","tipoSangre", "23/08/2023"))));
        pacientes.add(new Paciente(3, "Raul", "Vargas", "Soto", 
                                    "3-5", "Pasaje El Verano 1169 Maipu", "9111111111", "josefa@correo.cl", "18/08/2007",
                                    Arrays.asList(new Consulta(1,"motivo","diagnostico","tratamiento","23/03/2022"), 
                                                    new Consulta(2,"motivo","diagnostico","tratamiento", "18/08/2023")),
                                    Arrays.asList(new HistorialMedico(1,"alergias","enfermedadesPrevias",
                                                                    "procedimientos","tipoSangre", "18/08/2007"), 
                                                    new HistorialMedico(2,"alergias","enfermedadesPrevias",
                                                                    "procedimientos","tipoSangre", "23/08/2023"))));
        pacientes.add(new Paciente(4, "Eugenio", "Soto", "Vargas", 
                                    "4-3", "Pasaje El Verano 1169 Maipu", "9111111111", "josefa@correo.cl", "18/08/2007",
                                    Arrays.asList(new Consulta(1,"motivo","diagnostico","tratamiento","23/03/2022"), 
                                                    new Consulta(2,"motivo","diagnostico","tratamiento", "18/08/2023")),
                                    Arrays.asList(new HistorialMedico(1,"alergias","enfermedadesPrevias",
                                                                    "procedimientos","tipoSangre", "18/08/2007"), 
                                                    new HistorialMedico(2,"alergias","enfermedadesPrevias",
                                                                    "procedimientos","tipoSangre", "23/08/2023"))));
        pacientes.add(new Paciente(5, "Javier", "Padilla", "Vilches", 
                                    "5-1", "Pasaje El Verano 1169 Maipu", "9111111111", "josefa@correo.cl", "18/08/2007",
                                    Arrays.asList(new Consulta(1,"motivo","diagnostico","tratamiento","23/03/2022"), 
                                                    new Consulta(2,"motivo","diagnostico","tratamiento", "18/08/2023")),
                                    Arrays.asList(new HistorialMedico(1,"alergias","enfermedadesPrevias",
                                                                    "procedimientos","tipoSangre", "18/08/2007"), 
                                                    new HistorialMedico(2,"alergias","enfermedadesPrevias",
                                                                    "procedimientos","tipoSangre", "23/08/2023"))));
    }
    
    @GetMapping("/pacientes")
    public List<Paciente> getPacientes() {
        return pacientes;
    }
    
    @GetMapping("/pacientes/{id}")
    public Paciente getPacienteById(@PathVariable int id) {
        for (Paciente paciente : pacientes) {
            if (paciente.getIdPaciente() == id) {
                return paciente;
            }
        }
        return null;
    }

    @GetMapping(path = "/pacientes/{idPaciente}/consultas")
	public List<Consulta> listarConsultas(@PathVariable("idPaciente") int idPaciente) {

		for (Paciente paciente : pacientes) {

			if (paciente.getIdPaciente() == idPaciente) {

				List<Consulta> consultas = paciente.getConsultas();
                return consultas;
			}
		}
		return null;
	}

    @GetMapping(path = "/pacientes/{idPaciente}/historialmedico")
	public List<HistorialMedico> listarHistorialMedico(@PathVariable("idPaciente") int idPaciente) {

		for (Paciente paciente : pacientes) {

			if (paciente.getIdPaciente() == idPaciente) {

				List<HistorialMedico> historialMedico = paciente.getHistorialMedico();
                return historialMedico;
			}
		}
		return null;
	}

}

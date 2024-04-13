package com.example.pacientes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.pacientes.model.Paciente;
import com.example.pacientes.service.PacienteService;

import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.validation.constraints.*;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    private static final Logger log = LoggerFactory.getLogger(PacienteController.class);

    @Autowired
    private PacienteService pacienteService;

    //devuelve la informacion de todos los usuarios
    @GetMapping
    public List<Paciente> getPacientes() {
        log.info("GET /pacientes");
        log.info("Devuelve la informacion de todos los pacientes");
        return pacienteService.getAllPacientes();
    }
    
    //devuelve la informacion de un paciente especifico
    @GetMapping("/{idPaciente}")
    public ResponseEntity<Object> getPacienteById(@PathVariable("idPaciente") Long idPaciente) {
        log.info("GET /{idPaciente}");
        Optional<Paciente> paciente = pacienteService.getPacienteById(idPaciente);
        if (paciente.isEmpty()) {
            log.error("No se encontró el paciente con ID {}", idPaciente);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("No se encontró el paciente con ID " + idPaciente));
        }
        log.info("Se encontró el paciente con ID {}", idPaciente);
        return ResponseEntity.ok(paciente);
    }

    @PostMapping
    public ResponseEntity<Object> createUsuario(@Validated @RequestBody Paciente paciente) {
        Paciente createdPaciente = pacienteService.createPaciente(paciente);
        if (createdPaciente == null) {
            log.error("Error al crear el paciente {}", paciente);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("Error al crear el paciente"));
        }
        return ResponseEntity.ok(createdPaciente);
    }
    
    @PutMapping("/{id}")
    public Paciente updatePaciente(@PathVariable Long idPaciente, @RequestBody Paciente paciente) {
        return pacienteService.updatePaciente(idPaciente, paciente);
    }

    @DeleteMapping("/{id}")
    public void deletePaciente(@PathVariable Long idPaciente){
        pacienteService.deletePaciente(idPaciente);
    }

    static class ErrorResponse {
        private final String message;
    
        public ErrorResponse(String message) {
            this.message = message;
        }
    
        public String getMessage() {
            return message;
        }
    }
/*
    //devuelve la informacion de las consultas de un paciente especifico
    @GetMapping(path = "/pacientes/{idPaciente}/consultas")
	public List<Consulta> listarConsultas(@PathVariable("idPaciente") int idPaciente) {

		for (Paciente paciente : pacientes) {
			if (paciente.getIdPaciente() == idPaciente) {
				List<Consulta> consultas = paciente.getConsultas();
                System.out.println("Devuelve la informacion de las consultas del paciente " + idPaciente);
                return consultas;
			}
		}
        System.out.println("No encontro información de las consultas del paciente " + idPaciente);
		return null;
	}

    //devuelve la informacion del historial medico de un paciente especifico
    @GetMapping(path = "/pacientes/{idPaciente}/historialmedico")
	public List<HistorialMedico> listarHistorialMedico(@PathVariable("idPaciente") int idPaciente) {

		for (Paciente paciente : pacientes) {
			if (paciente.getIdPaciente() == idPaciente) {
				List<HistorialMedico> historialMedico = paciente.getHistorialMedico();
                System.out.println("Devuelve la informacion del historial medico del paciente " + idPaciente);
                return historialMedico;
			}
		}
        System.out.println("No encontro información del historial medico del paciente " + idPaciente);
		return null;
	}
*/
}

package com.example.pacientes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.pacientes.model.Consulta;
import com.example.pacientes.model.HistorialMedico;
import com.example.pacientes.model.Paciente;
import com.example.pacientes.service.ConsultaService;
import com.example.pacientes.service.HistorialMedicoService;
import com.example.pacientes.service.PacienteService;

import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    private static final Logger log = LoggerFactory.getLogger(PacienteController.class);

    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private ConsultaService consultaService;
    @Autowired
    private HistorialMedicoService historialMedicoService;

    //devuelve la informacion de todos los pacientes
    @GetMapping
    public List<Paciente> getPacientes() {
        log.info("GET /pacientes");
        log.info("Devuelve la informacion de todos los pacientes");
        return pacienteService.getAllPacientes();
    }

    //devuelve la informacion de todos las consultas
    @GetMapping("/consulta")
    public List<Consulta> getConsultas() {
        log.info("GET /pacientes/consulta");
        log.info("Devuelve la informacion de todas las consulta");
        return consultaService.getAllConsultas();
    }

    //devuelve la informacion de todo el historial medico
    @GetMapping("/historialmedico")
    public List<HistorialMedico> getHistorialesMedicos() {
        log.info("GET /pacientes/historialmedico");
        log.info("Devuelve la informacion de todo el historial medico");
        return historialMedicoService.getAllHistorialMedicos();
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

    //devuelve la informacion de un paciente especifico
    @GetMapping("/consulta/{idConsulta}")
    public ResponseEntity<Object> getConsultaById(@PathVariable("idConsulta") Long idConsulta) {
        log.info("GET /consulta/{idConsulta}");
        Optional<Consulta> consulta = consultaService.getConsultaById(idConsulta);
        if (consulta.isEmpty()) {
            log.error("No se encontró la consulta con ID {}", idConsulta);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("No se encontró la consulta con ID " + idConsulta));
        }
        log.info("Se encontró la consulta con ID {}", idConsulta);
        return ResponseEntity.ok(consulta);
    }

    //devuelve la informacion de un paciente especifico
    @GetMapping("/historialmedico/{idHistorialMedico}")
    public ResponseEntity<Object> getHistorialMedicoById(@PathVariable("idHistorialMedico") Long idHistorialMedico) {
        log.info("GET /historialmedico/{idHistorialMedico}");
        Optional<HistorialMedico> historialMedico = historialMedicoService.getHistorialMedicoById(idHistorialMedico);
        if (historialMedico.isEmpty()) {
            log.error("No se encontró el historial medico con ID {}", idHistorialMedico);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("No se encontró el historial medico con ID " + idHistorialMedico));
        }
        log.info("Se encontró el historial medico con ID {}", idHistorialMedico);
        return ResponseEntity.ok(historialMedico);
    }

    @PostMapping
    public ResponseEntity<Object> createUsuario(@Validated @RequestBody Paciente paciente) {
        log.info("POST /pacientes");
        Paciente createPaciente = pacienteService.createPaciente(paciente);
        if (createPaciente == null) {
            log.error("Error al crear el paciente {}", paciente);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("Error al crear el paciente"));
        }
        return ResponseEntity.ok(createPaciente);
    }
    
    @PostMapping("/consulta")
    public ResponseEntity<Object> createConsulta(@Validated @RequestBody Consulta consulta) {
        log.info("POST /pacientes/consulta");
        Consulta createConsulta = consultaService.createConsulta(consulta);
        if (createConsulta == null) {
            log.error("Error al crear la consulta {}", consulta);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("Error al crear la consulta"));
        }
        return ResponseEntity.ok(createConsulta);
    }
    
    @PostMapping("/historialmedico")
    public ResponseEntity<Object> createHistorialMedico(@Validated @RequestBody HistorialMedico historialMedico) {
        log.info("POST /pacientes/historialmedico");
        HistorialMedico createHistorialMedico = historialMedicoService.createHistorialMedico(historialMedico);
        if (createHistorialMedico == null) {
            log.error("Error al crear el historial medico {}", historialMedico);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("Error al crear el historial medico"));
        }
        return ResponseEntity.ok(createHistorialMedico);
    }
    
    @PutMapping("/{idPaciente}")
    public ResponseEntity<Object> updatePaciente(@PathVariable("idPaciente") Long idPaciente, @RequestBody Paciente paciente) {
        log.info("PUT /pacientes/{idPaciente}");
        Optional<Paciente> pacienteFind = pacienteService.getPacienteById(idPaciente);
        if (pacienteFind.isEmpty()) {
            log.error("No se encontró el paciente con ID {}", idPaciente);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("No se encontró el paciente con ID " + idPaciente));
        }
        log.info("Se encontró y actualizo el paciente con ID {}", idPaciente);
        return ResponseEntity.ok(pacienteService.updatePaciente(idPaciente, paciente));
    }

    @PutMapping("/consulta/{idConsulta}")
    public ResponseEntity<Object> updateConsulta(@PathVariable("idConsulta") Long idConsulta, @RequestBody Consulta consulta) {
        log.info("PUT /pacientes/consulta/{idConsulta}");
        Optional<Consulta> consultaFind = consultaService.getConsultaById(idConsulta);
        if (consultaFind.isEmpty()) {
            log.error("No se encontró la consulta con ID {}", idConsulta);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("No se encontró la consulta con ID " + idConsulta));
        }
        log.info("Se encontró y actualizo la consulta con ID {}", idConsulta);
        return ResponseEntity.ok(consultaService.updateConsulta(idConsulta, consulta));
    }

    @PutMapping("/historialmedico/{idHistorialMedico}")
    public ResponseEntity<Object> updateHistorialMedico(@PathVariable("idHistorialMedico") Long idHistorialMedico, @RequestBody HistorialMedico historialMedico) {
        log.info("PUT /pacientes/historialmedico/{idHistorialMedico}");
        Optional<HistorialMedico> historialMedicoFind = historialMedicoService.getHistorialMedicoById(idHistorialMedico);
        if (historialMedicoFind.isEmpty()) {
            log.error("No se encontró el historial medico con ID {}", idHistorialMedico);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("No se encontró el historial medico con ID " + idHistorialMedico));
        }
        log.info("Se encontró y actualizo el historial medico con ID {}", idHistorialMedico);
        return ResponseEntity.ok(historialMedicoService.updateHistorialMedico(idHistorialMedico, historialMedico));
    }

    @DeleteMapping("/{idPaciente}")
    public ResponseEntity<Object> deletePaciente(@PathVariable("idPaciente") Long idPaciente){
        log.info("DELETE /pacientes/{idPaciente}");
        Optional<Paciente> usuarioFind = pacienteService.getPacienteById(idPaciente);
        if (usuarioFind.isEmpty()) {
            log.error("No se encontró el paciente con ID {}", idPaciente);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("No se encontró el paciente con ID " + idPaciente));
        }
        log.info("Se encontró y elimino el paciente con ID {}", idPaciente);
        pacienteService.deletePaciente(idPaciente);
        return ResponseEntity.status(HttpStatus.OK).body(new ErrorResponse("Se encontró y elimino el paciente con ID " + idPaciente));
    }

    @DeleteMapping("/consulta/{idConsulta}")
    public ResponseEntity<Object> deleteConsulta(@PathVariable("idConsulta") Long idConsulta){
        log.info("DELETE /pacientes/consulta/{idUsuario}");
        Optional<Consulta> consultaFind = consultaService.getConsultaById(idConsulta);
        if (consultaFind.isEmpty()) {
            log.error("No se encontró la consulta con ID {}", idConsulta);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("No se encontró la consulta con ID " + idConsulta));
        }
        log.info("Se encontró y elimino la consulta con ID {}", idConsulta);
        consultaService.deleteConsulta(idConsulta);
        return ResponseEntity.status(HttpStatus.OK).body(new ErrorResponse("Se encontró y elimino la consulta con ID " + idConsulta));
    }

    @DeleteMapping("/historialmedico/{idHistorialMedico}")
    public ResponseEntity<Object> deleteHistorialMedico(@PathVariable("idHistorialMedico") Long idHistorialMedico){
        log.info("DELETE /pacientes/historialmedico/{idHistorialMedico}");
        Optional<HistorialMedico> historialMedicoFind = historialMedicoService.getHistorialMedicoById(idHistorialMedico);
        if (historialMedicoFind.isEmpty()) {
            log.error("No se encontró el historial medico con ID {}", idHistorialMedico);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("No se encontró el historial medico con ID " + idHistorialMedico));
        }
        log.info("Se encontró y elimino el historial medico con ID {}", idHistorialMedico);
        historialMedicoService.deleteHistorialMedico(idHistorialMedico);
        return ResponseEntity.status(HttpStatus.OK).body(new ErrorResponse("Se encontró y elimino el historial medico con ID " + idHistorialMedico));
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

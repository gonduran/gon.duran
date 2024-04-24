package com.example.pacientes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import com.example.pacientes.exception.PacienteBadRequestException;
import com.example.pacientes.exception.PacienteNotFoundException;
import com.example.pacientes.model.Consulta;
import com.example.pacientes.model.HistorialMedico;
import com.example.pacientes.model.Paciente;
import com.example.pacientes.service.ConsultaService;
import com.example.pacientes.service.HistorialMedicoService;
import com.example.pacientes.service.PacienteService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
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
    public CollectionModel<EntityModel<Paciente>> getAllPacientes() {
        List<Paciente> pacientes = pacienteService.getAllPacientes();
        log.info("GET /pacientes");
        log.info("Devuelve la informacion de todos los pacientes");
        List<EntityModel<Paciente>> pacientesResources = pacientes.stream()
            .map( pelicula -> EntityModel.of(pelicula,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getPacienteById(pelicula.getId())).withSelfRel()
            ))
            .collect(Collectors.toList());

        WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllPacientes());
        CollectionModel<EntityModel<Paciente>> resources = CollectionModel.of(pacientesResources, linkTo.withRel("pacientes"));

        return resources;
    }

    //devuelve la informacion de todos las consultas
    @GetMapping("/consulta")
    public CollectionModel<EntityModel<Consulta>> getAllConsultas() {
        List<Consulta> consultas = consultaService.getAllConsultas();
        log.info("GET /pacientes/consulta");
        log.info("Devuelve la informacion de todas las consulta");
        List<EntityModel<Consulta>> consultasResources = consultas.stream()
            .map( consulta -> EntityModel.of(consulta,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getConsultaById(consulta.getId())).withSelfRel()
            ))
            .collect(Collectors.toList());

        WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllConsultas());
        CollectionModel<EntityModel<Consulta>> resources = CollectionModel.of(consultasResources, linkTo.withRel("consultas"));

        return resources;
    }

    //devuelve la informacion de todo el historial medico
    @GetMapping("/historialmedico")
    public CollectionModel<EntityModel<HistorialMedico>> getAllHistorialMedicos() {
        List<HistorialMedico> historialMedicos = historialMedicoService.getAllHistorialMedicos();
        log.info("GET /pacientes/historialmedico");
        log.info("Devuelve la informacion de todo el historial medico");
        List<EntityModel<HistorialMedico>> historialMedicosResources = historialMedicos.stream()
            .map( historialMedico -> EntityModel.of(historialMedico,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getHistorialMedicoById(historialMedico.getId())).withSelfRel()
            ))
            .collect(Collectors.toList());

        WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllHistorialMedicos());
        CollectionModel<EntityModel<HistorialMedico>> resources = CollectionModel.of(historialMedicosResources, linkTo.withRel("historialmedicos"));

        return resources;
    }

    //devuelve la informacion de un paciente especifico
    @GetMapping("/{idPaciente}")
    public EntityModel<Paciente> getPacienteById(@PathVariable("idPaciente") Long idPaciente) {
        Optional<Paciente> paciente = pacienteService.getPacienteById(idPaciente);
        log.info("GET /{idPaciente}");
        log.info("Se ejecuta getPacienteById: {}", idPaciente);
        if (paciente.isPresent()) {
            log.info("Se encontró el paciente con ID {}", idPaciente);
            return EntityModel.of(paciente.get(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getPacienteById(idPaciente)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllPacientes()).withRel("all-pacientes"));
        } else {
            log.error("No se encontró el paciente con ID {}", idPaciente);
            throw new PacienteNotFoundException("No se encontró el paciente con ID: " + idPaciente);
        }
    }

    //devuelve la informacion de una consulta especifica
    @GetMapping("/consulta/{idConsulta}")
    public EntityModel<Consulta> getConsultaById(@PathVariable("idConsulta") Long idConsulta) {
        Optional<Consulta> consulta = consultaService.getConsultaById(idConsulta);
        log.info("GET /consulta/{idConsulta}");
        log.info("Se ejecuta getConsultaById: {}", idConsulta);
        if (consulta.isPresent()) {
            log.info("Se encontró la consulta con ID {}", idConsulta);
            return EntityModel.of(consulta.get(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getConsultaById(idConsulta)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllConsultas()).withRel("all-consultas"));
        } else {
            log.error("No se encontró la consulta con ID {}", idConsulta);
            throw new PacienteNotFoundException("No se encontró la consulta con ID: " + idConsulta);
        }
    }

    //devuelve la informacion de un historial medico especifico
    @GetMapping("/historialmedico/{idHistorialMedico}")
    public EntityModel<HistorialMedico> getHistorialMedicoById(@PathVariable("idHistorialMedico") Long idHistorialMedico) {
        Optional<HistorialMedico> historialMedico = historialMedicoService.getHistorialMedicoById(idHistorialMedico);
        log.info("GET /historialmedico/{idHistorialMedico}");
        log.info("Se ejecuta getHistorialMedicoById: {}", idHistorialMedico);
        if (historialMedico.isPresent()) {
            log.info("Se encontró el historial medico con ID {}", idHistorialMedico);
            return EntityModel.of(historialMedico.get(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getHistorialMedicoById(idHistorialMedico)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllHistorialMedicos()).withRel("all-historialmedicos"));
        } else {
            log.error("No se encontró el historial medico con ID {}", idHistorialMedico);
            throw new PacienteNotFoundException("No se encontró el historial medico con ID: " + idHistorialMedico);
        }
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body("Error en el servidor: " + e.getMessage());
    }

    @ExceptionHandler(PacienteNotFoundException.class)
    public ResponseEntity<String> handlePacienteNotFoundException(PacienteNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(e.getMessage());
    }

    @ExceptionHandler(PacienteBadRequestException.class)
    public ResponseEntity<String> handlePacienteBadRequestException(PacienteBadRequestException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(e.getMessage());
    }

    @PostMapping
    public EntityModel<Paciente> createPaciente(@Validated @RequestBody Paciente paciente) {
        log.info("POST /pacientes");
        log.info("Se ejecuta createPaciente");
        Paciente createPaciente = pacienteService.createPaciente(paciente);
        if (createPaciente == null) {
            log.error("Error al crear el paciente {}", paciente);
            throw new PacienteBadRequestException("Error al crear el paciente");
        }
        return EntityModel.of(createPaciente,
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getPacienteById(createPaciente.getId())).withSelfRel(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllPacientes()).withRel("all-pacientes"));
    }
    
    @PostMapping("/consulta")
    public EntityModel<Consulta> createConsulta(@Validated @RequestBody Consulta consulta) {
        log.info("POST /pacientes/consulta");
        log.info("Se ejecuta createConsulta");
        Consulta createConsulta = consultaService.createConsulta(consulta);
        if (createConsulta == null) {
            log.error("Error al crear la consulta {}", consulta);
            throw new PacienteBadRequestException("Error al crear la consulta");
        }
        return EntityModel.of(createConsulta,
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getConsultaById(createConsulta.getId())).withSelfRel(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllConsultas()).withRel("all-consultas"));
    }
    
    @PostMapping("/historialmedico")
    public EntityModel<HistorialMedico> createHistorialMedico(@Validated @RequestBody HistorialMedico historialMedico) {
        log.info("POST /pacientes/historialmedico");
        log.info("Se ejecuta createHistorialMedico");
        HistorialMedico createHistorialMedico = historialMedicoService.createHistorialMedico(historialMedico);
        if (createHistorialMedico == null) {
            log.error("Error al crear el historial medico {}", historialMedico);
            throw new PacienteBadRequestException("Error al crear el historial medico");
        }
        return EntityModel.of(createHistorialMedico,
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getConsultaById(createHistorialMedico.getId())).withSelfRel(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllConsultas()).withRel("all-historialmedicos"));
    }
    
    @PutMapping("/{idPaciente}")
    public EntityModel<Paciente> updatePaciente(@PathVariable("idPaciente") Long idPaciente, @RequestBody Paciente paciente) {
        log.info("PUT /pacientes/{idPaciente}");
        log.info("Se ejecuta updatePaciente: {}", idPaciente);
        Optional<Paciente> pacienteFind = pacienteService.getPacienteById(idPaciente);
        if (pacienteFind.isEmpty()) {
            log.error("No se encontró el paciente con ID {}", idPaciente);
            throw new PacienteNotFoundException("No se encontró el paciente con ID: " + idPaciente);
        }
        log.info("Se encontró y actualizo el paciente con ID {}", idPaciente);
        Paciente updatePaciente = pacienteService.updatePaciente(idPaciente, paciente);
        return EntityModel.of(updatePaciente,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getPacienteById(idPaciente)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllPacientes()).withRel("all-pacientes"));

    }

    @PutMapping("/consulta/{idConsulta}")
    public EntityModel<Consulta> updateConsulta(@PathVariable("idConsulta") Long idConsulta, @RequestBody Consulta consulta) {
        log.info("PUT /pacientes/consulta/{idConsulta}");
        log.info("Se ejecuta updateConsulta: {}", idConsulta);
        Optional<Consulta> consultaFind = consultaService.getConsultaById(idConsulta);
        if (consultaFind.isEmpty()) {
            log.error("No se encontró la consulta con ID {}", idConsulta);
            throw new PacienteNotFoundException("No se encontró la consulta con ID: " + idConsulta);
        }
        log.info("Se encontró y actualizo la consulta con ID {}", idConsulta);
        Consulta updateConsulta = consultaService.updateConsulta(idConsulta, consulta);
        return EntityModel.of(updateConsulta,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getConsultaById(idConsulta)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllConsultas()).withRel("all-consultas"));

    }

    @PutMapping("/historialmedico/{idHistorialMedico}")
    public EntityModel<HistorialMedico> updateHistorialMedico(@PathVariable("idHistorialMedico") Long idHistorialMedico, @RequestBody HistorialMedico historialMedico) {
        log.info("PUT /pacientes/historialmedico/{idHistorialMedico}");
        log.info("Se ejecuta updateHistorialMedico: {}", idHistorialMedico);
        Optional<HistorialMedico> historialMedicoFind = historialMedicoService.getHistorialMedicoById(idHistorialMedico);
        if (historialMedicoFind.isEmpty()) {
            log.error("No se encontró el historial medico con ID {}", idHistorialMedico);
            throw new PacienteNotFoundException("No se encontró el historial medico con ID: " + idHistorialMedico);
        }
        log.info("Se encontró y actualizo el historial medico con ID {}", idHistorialMedico);
        HistorialMedico updateHistorialMedico = historialMedicoService.updateHistorialMedico(idHistorialMedico, historialMedico);
        return EntityModel.of(updateHistorialMedico,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getHistorialMedicoById(idHistorialMedico)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllHistorialMedicos()).withRel("all-historialmedicos"));

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

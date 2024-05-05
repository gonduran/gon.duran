package com.example.pacientes.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import java.util.List;
import java.util.Optional;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.example.pacientes.model.Paciente;
import com.example.pacientes.model.Consulta;
import com.example.pacientes.model.HistorialMedico;
import com.example.pacientes.service.ConsultaService;
import com.example.pacientes.service.HistorialMedicoService;
import com.example.pacientes.service.PacienteService;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(PacienteController.class)
public class PacienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PacienteService pacienteServiceMock;
    @MockBean
    private ConsultaService consultaServiceMock;
    @MockBean
    private HistorialMedicoService historialMedicoServiceMock;

    @Test
    public void getAllPacientesTest() throws Exception {
        Paciente paciente1 = new Paciente();
        paciente1.setNombre("Gonzalo");
        paciente1.setApellidoPaterno("Duran");
        paciente1.setApellidoMaterno("Adasme");
        paciente1.setRut("12959664-3");
        paciente1.setDireccion("Araucaria 8417 La Florida");
        paciente1.setTelefono("+56977992993");
        paciente1.setEmail("gadurana@gmail.com");
        paciente1.setFechaNacimiento("21-11-1976");
        paciente1.setContactoEmerg(null);
        paciente1.setTipoSangre(null);
        paciente1.setId(1L);

        Paciente paciente2 = new Paciente();
        paciente2.setNombre("Josefa");
        paciente2.setApellidoPaterno("Cartagena");
        paciente2.setApellidoMaterno("Bobadilla");
        paciente2.setRut("20165862-4");
        paciente2.setDireccion("Calle Thiare 1195 Maipu");
        paciente2.setTelefono("+56993112428");
        paciente2.setEmail("jcartagenac@gmail.com");
        paciente2.setFechaNacimiento("05-01-1999");
        paciente2.setContactoEmerg(null);
        paciente2.setTipoSangre(null);
        paciente2.setId(2L);

        List<Paciente> pacientes = Arrays.asList(paciente1, paciente2);

        when(pacienteServiceMock.getAllPacientes()).thenReturn(pacientes);

        mockMvc.perform(MockMvcRequestBuilders.get("/pacientes"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void getPacienteByIdTest() throws Exception {
        Paciente paciente = new Paciente();
        paciente.setNombre("Gonzalo");
        paciente.setApellidoPaterno("Duran");
        paciente.setApellidoMaterno("Adasme");
        paciente.setRut("12959664-3");
        paciente.setDireccion("Araucaria 8417 La Florida");
        paciente.setTelefono("+56977992993");
        paciente.setEmail("gadurana@gmail.com");
        paciente.setFechaNacimiento("21-11-1976");
        paciente.setContactoEmerg(null);
        paciente.setTipoSangre(null);
        paciente.setId(1L);

        when(pacienteServiceMock.getPacienteById(1L)).thenReturn(Optional.of(paciente));

        Optional<Paciente> resultado = pacienteServiceMock.getPacienteById(1L);

        assertTrue(resultado.isPresent());
        assertEquals("Gonzalo", resultado.get().getNombre());

        mockMvc.perform(MockMvcRequestBuilders.get("/pacientes/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void getAllConsultasTest() throws Exception {
        Consulta consulta1 = new Consulta();
        consulta1.setNombreMedico("Dr Pascal");
        consulta1.setMotivo("Tos");
        consulta1.setDiagnostico("Resfriado");
        consulta1.setTratamiento("Antibiotico");
        consulta1.setFechaConsulta("12-04-2024");
        consulta1.setId(1L);

        Consulta consulta2 = new Consulta();
        consulta2.setNombreMedico("Dra Vilches");
        consulta2.setMotivo("Dolor");
        consulta2.setDiagnostico("Apendisitis");
        consulta2.setTratamiento("Operación");
        consulta2.setFechaConsulta("01-04-2010");
        consulta2.setId(2L);
    
        List<Consulta> consultas = Arrays.asList(consulta1, consulta2);

        when(consultaServiceMock.getAllConsultas()).thenReturn(consultas);

        mockMvc.perform(MockMvcRequestBuilders.get("/pacientes/consulta"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void getConsultaByIdTest() throws Exception {
        Consulta consulta = new Consulta();
        consulta.setNombreMedico("Dra Vilches");
        consulta.setMotivo("Dolor");
        consulta.setDiagnostico("Apendisitis");
        consulta.setTratamiento("Operación");
        consulta.setFechaConsulta("01-04-2010");
        consulta.setId(1L);

        when(consultaServiceMock.getConsultaById(1L)).thenReturn(Optional.of(consulta));

        Optional<Consulta> resultado = consultaServiceMock.getConsultaById(1L);

        assertTrue(resultado.isPresent());
        assertEquals("Dra Vilches", resultado.get().getNombreMedico());

        mockMvc.perform(MockMvcRequestBuilders.get("/pacientes/consulta/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void getAllHistorialMedicosTest() throws Exception {
        HistorialMedico historialMedico1 = new HistorialMedico();
        historialMedico1.setNombreMedico("Dra Vilches");
        historialMedico1.setFechaHistorialMedico("01-04-2010");
        historialMedico1.setAntecedentes("Rinitis Alergica");
        historialMedico1.setEstudios(null);
        historialMedico1.setInternaciones(null);
        historialMedico1.setOperaciones(null);
        historialMedico1.setTratamientos(null);
        historialMedico1.setMedicamentos("Rigotax");
        historialMedico1.setExamenes(null);
        historialMedico1.setId(1L);

        HistorialMedico historialMedico2 = new HistorialMedico();
        historialMedico2.setNombreMedico("Dra Vilches");
        historialMedico2.setFechaHistorialMedico("01-04-2010");
        historialMedico2.setAntecedentes("Rinitis Alergica");
        historialMedico2.setEstudios(null);
        historialMedico2.setInternaciones(null);
        historialMedico2.setOperaciones(null);
        historialMedico2.setTratamientos(null);
        historialMedico2.setMedicamentos("Rigotax");
        historialMedico2.setExamenes(null);
        historialMedico2.setId(2L);
    
        List<HistorialMedico> historialesMedicos = Arrays.asList(historialMedico1, historialMedico2);

        when(historialMedicoServiceMock.getAllHistorialMedicos()).thenReturn(historialesMedicos);

        mockMvc.perform(MockMvcRequestBuilders.get("/pacientes/historialmedico"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void getHistorialMedicoByIdTest() throws Exception {
        HistorialMedico historialMedico = new HistorialMedico();
        historialMedico.setNombreMedico("Dra Vilches");
        historialMedico.setFechaHistorialMedico("01-04-2010");
        historialMedico.setAntecedentes("Rinitis Alergica");
        historialMedico.setEstudios(null);
        historialMedico.setInternaciones(null);
        historialMedico.setOperaciones(null);
        historialMedico.setTratamientos(null);
        historialMedico.setMedicamentos("Rigotax");
        historialMedico.setExamenes(null);
        historialMedico.setId(1L);

        when(historialMedicoServiceMock.getHistorialMedicoById(1L)).thenReturn(Optional.of(historialMedico));

        Optional<HistorialMedico> resultado = historialMedicoServiceMock.getHistorialMedicoById(1L);

        assertTrue(resultado.isPresent());
        assertEquals("Dra Vilches", resultado.get().getNombreMedico());

        mockMvc.perform(MockMvcRequestBuilders.get("/pacientes/historialmedico/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }



}

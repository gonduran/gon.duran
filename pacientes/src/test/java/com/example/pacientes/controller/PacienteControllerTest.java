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
}

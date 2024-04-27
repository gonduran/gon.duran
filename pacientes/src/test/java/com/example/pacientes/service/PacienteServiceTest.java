package com.example.pacientes.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import java.util.List;
import java.util.Optional;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.example.pacientes.model.Paciente;
import com.example.pacientes.repository.PacienteRepository;

@ExtendWith(MockitoExtension.class)
public class PacienteServiceTest {
    @InjectMocks
    private PacienteServiceImpl pacienteService;

    @Mock
    private PacienteRepository pacienteRepositoryMock;

    @Test
    public void getAllPacientesTest() {
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

        when(pacienteRepositoryMock.findAll()).thenReturn(pacientes);

        List<Paciente> resultado = pacienteService.getAllPacientes();

        assertEquals(2, resultado.size());
        assertEquals("Gonzalo", resultado.get(0).getNombre());
        assertEquals("Josefa", resultado.get(1).getNombre());
    }

    @Test
    public void getPacienteByIdTest() {
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

        when(pacienteRepositoryMock.findById(1L)).thenReturn(Optional.of(paciente));

        Optional<Paciente> resultado = pacienteService.getPacienteById(1L);

        assertTrue(resultado.isPresent());
        assertEquals("Gonzalo", resultado.get().getNombre());
    }

    @Test
    public void createPacienteTest() {

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

        when(pacienteRepositoryMock.save(any())).thenReturn(paciente);

        Paciente resultado = pacienteService.createPaciente(paciente);

        assertEquals("Gonzalo", resultado.getNombre());
    }

    @Test
    public void updatePacienteTest_Exists() {
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
        when(pacienteRepositoryMock.existsById(1L)).thenReturn(true);
        when(pacienteRepositoryMock.save(paciente)).thenReturn(paciente);

        Paciente resultado = pacienteService.updatePaciente(1L, paciente);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Gonzalo", resultado.getNombre());
    }

    @Test
    public void updatePacienteTest_NotExists() {
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
        paciente.setId(2L);
        when(pacienteRepositoryMock.existsById(2L)).thenReturn(false);

        Paciente resultado = pacienteService.updatePaciente(2L, paciente);

        assertNull(resultado);
    }
}

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
import com.example.pacientes.model.Consulta;
import com.example.pacientes.repository.ConsultaRepository;

@ExtendWith(MockitoExtension.class)
public class ConsultaServiceTest {
    @InjectMocks
    private ConsultaServiceImpl consultaService;

    @Mock
    private ConsultaRepository consultaRepositoryMock;

    @Test
    public void getAllConsultasTest() {
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

        when(consultaRepositoryMock.findAll()).thenReturn(consultas);

        List<Consulta> resultado = consultaService.getAllConsultas();

        assertEquals(2, resultado.size());
        assertEquals("Gonzalo", resultado.get(0).getNombreMedico());
        assertEquals("Josefa", resultado.get(1).getNombreMedico());
    }

    @Test
    public void getConsultaByIdTest() {
        Consulta consulta = new Consulta();
        consulta.setNombreMedico("Dr Pascal");
        consulta.setMotivo("Tos");
        consulta.setDiagnostico("Resfriado");
        consulta.setTratamiento("Antibiotico");
        consulta.setFechaConsulta("12-04-2024");
        consulta.setId(1L);

        when(consultaRepositoryMock.findById(1L)).thenReturn(Optional.of(consulta));

        Optional<Consulta> resultado = consultaService.getConsultaById(1L);

        assertTrue(resultado.isPresent());
        assertEquals("Gonzalo", resultado.get().getNombreMedico());
    }

    @Test
    public void createConsultaTest() {

        Consulta consulta = new Consulta();
        consulta.setNombreMedico("Dr Pascal");
        consulta.setMotivo("Tos");
        consulta.setDiagnostico("Resfriado");
        consulta.setTratamiento("Antibiotico");
        consulta.setFechaConsulta("12-04-2024");

        when(consultaRepositoryMock.save(any())).thenReturn(consulta);

        Consulta resultado = consultaService.createConsulta(consulta);

        assertEquals("Gonzalo", resultado.getNombreMedico());
    }

    @Test
    public void updateConsultaTest_Exists() {
        Consulta consulta = new Consulta();
        consulta.setNombreMedico("Dr Pascal");
        consulta.setMotivo("Tos");
        consulta.setDiagnostico("Resfriado");
        consulta.setTratamiento("Antibiotico");
        consulta.setFechaConsulta("12-04-2024");
        consulta.setId(1L);
        when(consultaRepositoryMock.existsById(1L)).thenReturn(true);
        when(consultaRepositoryMock.save(consulta)).thenReturn(consulta);

        Consulta resultado = consultaService.updateConsulta(1L, consulta);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Gonzalo", resultado.getNombreMedico());
    }

    @Test
    public void updateConsultaTest_NotExists() {
        Consulta consulta = new Consulta();
        consulta.setNombreMedico("Dr Pascal");
        consulta.setMotivo("Tos");
        consulta.setDiagnostico("Resfriado");
        consulta.setTratamiento("Antibiotico");
        consulta.setFechaConsulta("12-04-2024");
        consulta.setId(2L);
        when(consultaRepositoryMock.existsById(2L)).thenReturn(false);

        Consulta resultado = consultaService.updateConsulta(2L, consulta);

        assertNull(resultado);
    }
}

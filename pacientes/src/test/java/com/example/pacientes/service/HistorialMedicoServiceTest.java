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
import com.example.pacientes.model.HistorialMedico;
import com.example.pacientes.repository.HistorialMedicoRepository;

@ExtendWith(MockitoExtension.class)
public class HistorialMedicoServiceTest {
    @InjectMocks
    private HistorialMedicoServiceImpl historialMedicoService;

    @Mock
    private HistorialMedicoRepository historialMedicoRepositoryMock;

    @Test
    public void getAllHistorialMedicosTest() {
        HistorialMedico historialMedico1 = new HistorialMedico();
        historialMedico1.setNombreMedico("Dr Pascal");
        historialMedico1.setFechaHistorialMedico("12-04-2024");
        historialMedico1.setAntecedentes(null);
        historialMedico1.setEstudios(null);
        historialMedico1.setInternaciones(null);
        historialMedico1.setOperaciones("Apendisitis");
        historialMedico1.setTratamientos(null);
        historialMedico1.setMedicamentos(null);
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

        List<HistorialMedico> historialMedicos = Arrays.asList(historialMedico1, historialMedico2);

        when(historialMedicoRepositoryMock.findAll()).thenReturn(historialMedicos);

        List<HistorialMedico> resultado = historialMedicoService.getAllHistorialMedicos();

        assertEquals(2, resultado.size());
        assertEquals("Gonzalo", resultado.get(0).getNombreMedico());
        assertEquals("Josefa", resultado.get(1).getNombreMedico());
    }

    @Test
    public void getHistorialMedicoByIdTest() {
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

        when(historialMedicoRepositoryMock.findById(1L)).thenReturn(Optional.of(historialMedico));

        Optional<HistorialMedico> resultado = historialMedicoService.getHistorialMedicoById(1L);

        assertTrue(resultado.isPresent());
        assertEquals("Gonzalo", resultado.get().getNombreMedico());
    }

    @Test
    public void createHistorialMedicoTest() {

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

        when(historialMedicoRepositoryMock.save(any())).thenReturn(historialMedico);

        HistorialMedico resultado = historialMedicoService.createHistorialMedico(historialMedico);

        assertEquals("Gonzalo", resultado.getNombreMedico());
    }

    @Test
    public void updateHistorialMedicoTest_Exists() {
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
        when(historialMedicoRepositoryMock.existsById(1L)).thenReturn(true);
        when(historialMedicoRepositoryMock.save(historialMedico)).thenReturn(historialMedico);

        HistorialMedico resultado = historialMedicoService.updateHistorialMedico(1L, historialMedico);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Gonzalo", resultado.getNombreMedico());
    }

    @Test
    public void updateHistorialMedicoTest_NotExists() {
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
        historialMedico.setId(2L);
        when(historialMedicoRepositoryMock.existsById(2L)).thenReturn(false);

        HistorialMedico resultado = historialMedicoService.updateHistorialMedico(2L, historialMedico);

        assertNull(resultado);
    }
}

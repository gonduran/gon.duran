package com.example.usuarios.service;

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
import com.example.usuarios.model.Direccion;
import com.example.usuarios.repository.DireccionRepository;

@ExtendWith(MockitoExtension.class)
public class DireccionServiceTest {
    @InjectMocks
    private DireccionServiceImpl direccionService;

    @Mock
    private DireccionRepository direccionRepositoryMock;

    @Test
    public void getAllDireccionesTest() {
        Direccion direccion1 = new Direccion();
        direccion1.setNombreMedico("Dr Pascal");
        direccion1.setMotivo("Tos");
        direccion1.setDiagnostico("Resfriado");
        direccion1.setTratamiento("Antibiotico");
        direccion1.setFechaConsulta("12-04-2024");
        direccion1.setId(1L);

        Direccion direccion2 = new Direccion();
        direccion2.setNombreMedico("Dra Vilches");
        direccion2.setMotivo("Dolor");
        direccion2.setDiagnostico("Apendisitis");
        direccion2.setTratamiento("Operación");
        direccion2.setFechaConsulta("01-04-2010");
        direccion2.setId(2L);

        List<Direccion> direcciones = Arrays.asList(direccion1, direccion2);

        when(direccionRepositoryMock.findAll()).thenReturn(direcciones);

        List<Direccion> resultado = direccionService.getAllDirecciones();

        assertEquals(2, resultado.size());
        assertEquals("Gonzalo", resultado.get(0).getNombreMedico());
        assertEquals("Josefa", resultado.get(1).getNombreMedico());
    }

    @Test
    public void getDireccionByIdTest() {
        Direccion direccion = new Direccion();
        direccion.setNombreMedico("Dr Pascal");
        direccion.setMotivo("Tos");
        direccion.setDiagnostico("Resfriado");
        direccion.setTratamiento("Antibiotico");
        direccion.setFechaConsulta("12-04-2024");
        direccion.setId(1L);

        when(direccionRepositoryMock.findById(1L)).thenReturn(Optional.of(direccion));

        Optional<Direccion> resultado = direccionService.getDireccionById(1L);

        assertTrue(resultado.isPresent());
        assertEquals("Gonzalo", resultado.get().getNombreMedico());
    }

    @Test
    public void createDireccionTest() {

        Direccion direccion = new Direccion();
        direccion.setNombreMedico("Dr Pascal");
        direccion.setMotivo("Tos");
        direccion.setDiagnostico("Resfriado");
        direccion.setTratamiento("Antibiotico");
        direccion.setFechaConsulta("12-04-2024");

        when(direccionRepositoryMock.save(any())).thenReturn(direccion);

        Direccion resultado = direccionService.createDireccion(direccion);

        assertEquals("Gonzalo", resultado.getNombreMedico());
    }

    @Test
    public void updateDireccionTest_Exists() {
        Direccion direccion = new Direccion();
        direccion.setNombreMedico("Dr Pascal");
        direccion.setMotivo("Tos");
        direccion.setDiagnostico("Resfriado");
        direccion.setTratamiento("Antibiotico");
        direccion.setFechaConsulta("12-04-2024");
        direccion.setId(1L);
        when(direccionRepositoryMock.existsById(1L)).thenReturn(true);
        when(direccionRepositoryMock.save(direccion)).thenReturn(direccion);

        Direccion resultado = direccionService.updateDireccion(1L, direccion);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Gonzalo", resultado.getNombreMedico());
    }

    @Test
    public void updateDireccionTest_NotExists() {
        Direccion direccion = new Direccion();
        direccion.setNombreMedico("Dr Pascal");
        direccion.setMotivo("Tos");
        direccion.setDiagnostico("Resfriado");
        direccion.setTratamiento("Antibiotico");
        direccion.setFechaConsulta("12-04-2024");
        direccion.setId(2L);
        when(direccionRepositoryMock.existsById(2L)).thenReturn(false);

        Direccion resultado = direccionService.updateDireccion(2L, direccion);

        assertNull(resultado);
    }
}

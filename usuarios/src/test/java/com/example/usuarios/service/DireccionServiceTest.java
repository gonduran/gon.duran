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
        direccion1.setCalle("Jardin Alto");
        direccion1.setNumero("8417");
        direccion1.setComuna("La Florida");
        direccion1.setCiudad("Santiago");
        direccion1.setRegion("Metropolitana");
        direccion1.setTipoDireccion("Particular");
        direccion1.setId(1L);

        Direccion direccion2 = new Direccion();
        direccion2.setCalle("Catedral");
        direccion2.setNumero("1401");
        direccion2.setComuna("Santiago");
        direccion2.setCiudad("Santiago");
        direccion2.setRegion("Metropolitana");
        direccion2.setTipoDireccion("Comercial");
        direccion2.setId(2L);

        List<Direccion> direcciones = Arrays.asList(direccion1, direccion2);

        when(direccionRepositoryMock.findAll()).thenReturn(direcciones);

        List<Direccion> resultado = direccionService.getAllDirecciones();

        assertEquals(2, resultado.size());
        assertEquals("Particular", resultado.get(0).getTipoDireccion());
        assertEquals("Comercial", resultado.get(1).getTipoDireccion());
    }

    @Test
    public void getDireccionByIdTest() {
        Direccion direccion = new Direccion();
        direccion.setCalle("Jardin Alto");
        direccion.setNumero("8417");
        direccion.setComuna("La Florida");
        direccion.setCiudad("Santiago");
        direccion.setRegion("Metropolitana");
        direccion.setTipoDireccion("Particular");
        direccion.setId(1L);

        when(direccionRepositoryMock.findById(1L)).thenReturn(Optional.of(direccion));

        Optional<Direccion> resultado = direccionService.getDireccionById(1L);

        assertTrue(resultado.isPresent());
        assertEquals("Particular", resultado.get().getTipoDireccion());
    }

    @Test
    public void createDireccionTest() {

        Direccion direccion = new Direccion();
        direccion.setCalle("Jardin Alto");
        direccion.setNumero("8417");
        direccion.setComuna("La Florida");
        direccion.setCiudad("Santiago");
        direccion.setRegion("Metropolitana");
        direccion.setTipoDireccion("Particular");

        when(direccionRepositoryMock.save(any())).thenReturn(direccion);

        Direccion resultado = direccionService.createDireccion(direccion);

        assertEquals("Particular", resultado.getTipoDireccion());
    }

    @Test
    public void updateDireccionTest_Exists() {
        Direccion direccion = new Direccion();
        direccion.setCalle("Jardin Alto");
        direccion.setNumero("8417");
        direccion.setComuna("La Florida");
        direccion.setCiudad("Santiago");
        direccion.setRegion("Metropolitana");
        direccion.setTipoDireccion("Particular");
        direccion.setId(1L);

        when(direccionRepositoryMock.existsById(1L)).thenReturn(true);
        when(direccionRepositoryMock.save(direccion)).thenReturn(direccion);

        Direccion resultado = direccionService.updateDireccion(1L, direccion);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Particular", resultado.getTipoDireccion());
    }

    @Test
    public void updateDireccionTest_NotExists() {
        Direccion direccion = new Direccion();
        direccion.setCalle("Jardin Alto");
        direccion.setNumero("8417");
        direccion.setComuna("La Florida");
        direccion.setCiudad("Santiago");
        direccion.setRegion("Metropolitana");
        direccion.setTipoDireccion("Particular");
        direccion.setId(2L);

        when(direccionRepositoryMock.existsById(2L)).thenReturn(false);

        Direccion resultado = direccionService.updateDireccion(2L, direccion);

        assertNull(resultado);
    }
}

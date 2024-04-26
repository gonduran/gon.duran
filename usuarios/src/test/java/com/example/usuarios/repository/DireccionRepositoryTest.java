package com.example.usuarios.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.usuarios.model.Direccion;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DireccionRepositoryTest {
    @Autowired
    private DireccionRepository direccionRepository;

    @Test
    public void guardarDireccionTest() {
        Direccion direccion = new Direccion();
        direccion.setCalle("Jardin Alto");
        direccion.setNumero("8417");
        direccion.setComuna("La Florida");
        direccion.setCiudad("Santiago");
        direccion.setRegion("Metropolitana");
        direccion.setTipoDireccion("Particular");

        Direccion resultado = direccionRepository.save(direccion);

        assertNotNull(resultado.getId());
        assertEquals("Jardin Alto", resultado.getCalle());
        assertEquals("Particular", resultado.getTipoDireccion());
    }
}

package com.example.pacientes.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.pacientes.model.Consulta;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ConsultaRepositoryTest {
    @Autowired
    private ConsultaRepository pacienteRepository;

    @Test
    public void guardarPacienteTest() {
        Consulta consulta = new Consulta();
        consulta.setNombreMedico("Dr Pascal");
        consulta.setMotivo("Tos");
        consulta.setDiagnostico("Resfriado");
        consulta.setTratamiento("Antibiotico");
        consulta.setFechaConsulta("12-04-2024");


        Consulta resultado = pacienteRepository.save(consulta);

        assertNotNull(resultado.getId());
        assertEquals("Dr Pascal", resultado.getNombreMedico());
    }
}

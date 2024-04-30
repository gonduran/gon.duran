package com.example.pacientes.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.example.pacientes.model.Consulta;
import com.example.pacientes.service.ConsultaService;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ConsultaRepositoryTest {
    @Autowired
    private ConsultaRepository consultaRepository;

    @MockBean
    private ConsultaService consultaService;

    @Test
    public void guardarConsultaTest() {
        Consulta consulta = new Consulta();
        consulta.setNombreMedico("Dr Pascal");
        consulta.setMotivo("Tos");
        consulta.setDiagnostico("Resfriado");
        consulta.setTratamiento("Antibiotico");
        consulta.setFechaConsulta("12-04-2024");

        Consulta resultado = consultaRepository.save(consulta);

        assertNotNull(resultado.getId());
        assertEquals("Dr Pascal", resultado.getNombreMedico());
    }

    @Test
    public void obtenerConsultaPorIdTest() {
        Consulta consulta = new Consulta();
        consulta.setNombreMedico("Dr Pascal");
        consulta.setMotivo("Tos");
        consulta.setDiagnostico("Resfriado");
        consulta.setTratamiento("Antibiotico");
        consulta.setFechaConsulta("12-04-2024");
        consulta.setId(1L);

        Consulta resultado = consultaRepository.findById(1L).get();

        assertNotNull(resultado.getId());
        assertEquals(consulta.getId(), resultado.getId());
        assertEquals(consulta.getNombreMedico(), resultado.getNombreMedico());
    }

}

package com.example.pacientes.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import com.example.pacientes.model.HistorialMedico;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class HistorialMedicoRepositoryTest {
    @Autowired
    private HistorialMedicoRepository historialMedicoRepository;

    @Test
    public void guardarHistorialMedicoTest() {
        HistorialMedico historialMedico = new HistorialMedico();
        historialMedico.setNombreMedico("Dra Cartagena");
        historialMedico.setFechaHistorialMedico("01-04-2010");
        historialMedico.setAntecedentes("Rinitis Alergica");
        historialMedico.setEstudios(null);
        historialMedico.setInternaciones(null);
        historialMedico.setOperaciones(null);
        historialMedico.setTratamientos(null);
        historialMedico.setMedicamentos("Rigotax");
        historialMedico.setExamenes(null);

        HistorialMedico resultado = historialMedicoRepository.save(historialMedico);

        assertNotNull(resultado.getId());
        assertEquals("Dra Cartagena", resultado.getNombreMedico());
    }

    @Test
    public void obtenerHistorialMedicoPorIdTest() {
        HistorialMedico historialMedico = new HistorialMedico();
        historialMedico.setNombreMedico("Dra Cartagena");
        historialMedico.setFechaHistorialMedico("01-04-2010");
        historialMedico.setAntecedentes("Rinitis Alergica");
        historialMedico.setEstudios(null);
        historialMedico.setInternaciones(null);
        historialMedico.setOperaciones(null);
        historialMedico.setTratamientos(null);
        historialMedico.setMedicamentos("Rigotax");
        historialMedico.setExamenes(null);
        historialMedico.setId(1L);

        HistorialMedico resultado = historialMedicoRepository.findById(1L).get();

        assertNotNull(resultado.getId());
        assertEquals(historialMedico.getId(), resultado.getId());
        assertEquals(historialMedico.getNombreMedico(), resultado.getNombreMedico());
    }

}

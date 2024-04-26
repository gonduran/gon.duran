package com.example.pacientes.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.pacientes.model.Paciente;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PacienteRepositoryTest {
    @Autowired
    private PacienteRepository pacienteRepository;

    @Test
    public void guardarPacienteTest() {
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

        Paciente resultado = pacienteRepository.save(paciente);

        assertNotNull(resultado.getId());
        assertEquals("Gonzalo", resultado.getNombre());
    }
}

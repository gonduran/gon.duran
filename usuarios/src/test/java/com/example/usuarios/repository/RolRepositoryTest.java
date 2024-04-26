package com.example.usuarios.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.usuarios.model.Rol;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RolRepositoryTest {
    @Autowired
    private RolRepository rolRepository;

    @Test
    public void guardarRolUsuarioTest() {
        Rol rol = new Rol();
        rol.setDescripcion("Administrador");

        Rol resultado = rolRepository.save(rol);

        assertNotNull(resultado.getId());
        assertEquals("Administrador", resultado.getDescripcion());
    }
}

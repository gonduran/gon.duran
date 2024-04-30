package com.example.usuarios.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.usuarios.model.Usuario;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UsuarioRepositoryTest {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    public void guardarUsuarioTest() {
        Usuario usuario = new Usuario();
        usuario.setUsuario("gdurana");
        usuario.setPassword("password123");
        usuario.setHabilitado(true);
        usuario.setNombre("Gonzalo");
        usuario.setApellidoPaterno("Duran");
        usuario.setApellidoMaterno("Adasme");
        usuario.setRut("12959664-3");
        usuario.setTelefono("+56977992993");
        usuario.setEmail("gadurana@gmail.com");

        Usuario resultado = usuarioRepository.save(usuario);

        assertNotNull(resultado.getId());
        assertEquals("Gonzalo", resultado.getNombre());
        assertEquals("gdurana", resultado.getUsuario());
    }

    @Test
    public void obtenerUsuarioPorIdTest() {
        Usuario usuario = new Usuario();
        usuario.setUsuario("gdurana");
        usuario.setPassword("password123");
        usuario.setHabilitado(true);
        usuario.setNombre("Gonzalo");
        usuario.setApellidoPaterno("Duran");
        usuario.setApellidoMaterno("Adasme");
        usuario.setRut("12959664-3");
        usuario.setTelefono("+56977992993");
        usuario.setEmail("gadurana@gmail.com");
        usuario.setId(1L);

        Usuario resultado = usuarioRepository.findById(1L).get();

        assertNotNull(resultado.getId());
        assertEquals(usuario.getId(), resultado.getId());
        assertEquals(usuario.getNombre(), resultado.getNombre());
    }

}

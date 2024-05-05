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
import com.example.usuarios.model.Usuario;
import com.example.usuarios.repository.UsuarioRepository;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {
    @InjectMocks
    private UsuarioServiceImpl usuarioService;

    @Mock
    private UsuarioRepository usuarioRepositoryMock;

    @Test
    public void getAllUsuariosTest() {
        Usuario usuario1 = new Usuario();
        usuario1.setUsuario("gdurana");
        usuario1.setPassword("password123");
        usuario1.setHabilitado(true);
        usuario1.setNombre("Gonzalo");
        usuario1.setApellidoPaterno("Duran");
        usuario1.setApellidoMaterno("Adasme");
        usuario1.setRut("12959664-3");
        usuario1.setTelefono("+56977992993");
        usuario1.setEmail("gadurana@gmail.com");
        usuario1.setId(1L);

        Usuario usuario2 = new Usuario();
        usuario2.setUsuario("jcartagenab");
        usuario2.setPassword("password123");
        usuario2.setHabilitado(true);
        usuario2.setNombre("Josefa");
        usuario2.setApellidoPaterno("Cartagena");
        usuario2.setApellidoMaterno("Bobadilla");
        usuario2.setRut("20165862-4");
        usuario2.setTelefono("+56993112428");
        usuario2.setEmail("jcartagenab@gmail.com");
        usuario2.setId(2L);

        List<Usuario> usuarios = Arrays.asList(usuario1, usuario2);

        when(usuarioRepositoryMock.findAll()).thenReturn(usuarios);

        List<Usuario> resultado = usuarioService.getAllUsuarios();

        assertEquals(2, resultado.size());
        assertEquals("Gonzalo", resultado.get(0).getNombre());
        assertEquals("Josefa", resultado.get(1).getNombre());
    }

    @Test
    public void getUsuarioByIdTest() {
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

        when(usuarioRepositoryMock.findById(1L)).thenReturn(Optional.of(usuario));

        Optional<Usuario> resultado = usuarioService.getUsuarioById(1L);

        assertTrue(resultado.isPresent());
        assertEquals("Gonzalo", resultado.get().getNombre());
    }

    @Test
    public void createUsuarioTest() {

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

        when(usuarioRepositoryMock.save(any())).thenReturn(usuario);

        Usuario resultado = usuarioService.createUsuario(usuario);

        assertEquals("Gonzalo", resultado.getNombre());
    }

    @Test
    public void updateUsuarioTest_Exists() {
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

        when(usuarioRepositoryMock.existsById(1L)).thenReturn(true);
        when(usuarioRepositoryMock.save(usuario)).thenReturn(usuario);

        Usuario resultado = usuarioService.updateUsuario(1L, usuario);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Gonzalo", resultado.getNombre());
    }

    @Test
    public void updatePacienteTest_NotExists() {
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
        usuario.setId(2L);

        when(usuarioRepositoryMock.existsById(2L)).thenReturn(false);

        Usuario resultado = usuarioService.updateUsuario(2L, usuario);

        assertNull(resultado);
    }

    @Test
    public void validarUsuarioTest() {
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

        when(usuarioRepositoryMock.findByUsuario("gdurana")).thenReturn((usuario));

        boolean resultado = usuarioService.validarUsuario(usuario.getUsuario(), usuario.getPassword());

        assertEquals(true, resultado);
    }

}

package com.example.usuarios.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import java.util.List;
import java.util.Optional;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.example.usuarios.model.Usuario;
import com.example.usuarios.service.UsuarioService;
import com.example.usuarios.service.DireccionService;
import com.example.usuarios.service.RolService;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(UsuarioController.class)
public class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService usuarioServiceMock;
    @MockBean
    private DireccionService direccionServiceMock;
    @MockBean
    private RolService rolUsuarioServiceMock;

    @Test
    public void getAllUsuariosTest() throws Exception {
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

        when(usuarioServiceMock.getAllUsuarios()).thenReturn(usuarios);

        mockMvc.perform(MockMvcRequestBuilders.get("/usuarios"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void getUsuarioByIdTest() throws Exception {
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

        when(usuarioServiceMock.getUsuarioById(1L)).thenReturn(Optional.of(usuario));

        Optional<Usuario> resultado = usuarioServiceMock.getUsuarioById(1L);

        assertTrue(resultado.isPresent());
        assertEquals("Gonzalo", resultado.get().getNombre());

        mockMvc.perform(MockMvcRequestBuilders.get("/usuarios/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}

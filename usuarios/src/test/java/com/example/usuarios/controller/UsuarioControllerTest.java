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
import com.example.usuarios.model.Direccion;
import com.example.usuarios.model.Rol;
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

    @Test
    public void getAllDireccionesTest() throws Exception {
        Direccion direccion1 = new Direccion();
        direccion1.setCalle("Pasaje Araucaria");
        direccion1.setNumero("8417");
        direccion1.setComuna("La Florida");
        direccion1.setCiudad("Santiago");
        direccion1.setRegion("Metropolitana");
        direccion1.setTipoDireccion("Particular");
        direccion1.setId(1L);

        Direccion direccion2 = new Direccion();
        direccion2.setCalle("Catadral");
        direccion2.setNumero("1441");
        direccion2.setComuna("Santiago");
        direccion2.setCiudad("Santiago");
        direccion2.setRegion("Metropolitana");
        direccion2.setTipoDireccion("Comercial");
        direccion2.setId(2L);

        List<Direccion> direcciones = Arrays.asList(direccion1, direccion2);

        when(direccionServiceMock.getAllDirecciones()).thenReturn(direcciones);

        mockMvc.perform(MockMvcRequestBuilders.get("/usuarios/direccion"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void getDireccionByIdTest() throws Exception {
        Direccion direccion = new Direccion();
        direccion.setCalle("Pasaje Araucaria");
        direccion.setNumero("8417");
        direccion.setComuna("La Florida");
        direccion.setCiudad("Santiago");
        direccion.setRegion("Metropolitana");
        direccion.setTipoDireccion("Particular");
        direccion.setId(1L);

        when(direccionServiceMock.getDireccionById(1L)).thenReturn(Optional.of(direccion));

        Optional<Direccion> resultado = direccionServiceMock.getDireccionById(1L);

        assertTrue(resultado.isPresent());
        assertEquals("Pasaje Araucaria", resultado.get().getCalle());

        mockMvc.perform(MockMvcRequestBuilders.get("/usuarios/direccion/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void getAllRolUsuariosTest() throws Exception {
        Rol rol1 = new Rol();
        rol1.setDescripcion("Administrador");
        rol1.setId(1L);

        Rol rol2 = new Rol();
        rol1.setDescripcion("Administrador");
        rol2.setId(2L);

        List<Rol> roles = Arrays.asList(rol1, rol2);

        when(rolUsuarioServiceMock.getAllRolUsuarios()).thenReturn(roles);

        mockMvc.perform(MockMvcRequestBuilders.get("/usuarios/rolusuario"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void getRolUsuarioByIdTest() throws Exception {
        Rol rol = new Rol();
        rol.setDescripcion("Administrador");
        rol.setId(1L);

        when(rolUsuarioServiceMock.getRolUsuarioById(1L)).thenReturn(Optional.of(rol));

        Optional<Rol> resultado = rolUsuarioServiceMock.getRolUsuarioById(1L);

        assertTrue(resultado.isPresent());
        assertEquals("Administrador", resultado.get().getDescripcion());

        mockMvc.perform(MockMvcRequestBuilders.get("/usuarios/rolusuario/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
